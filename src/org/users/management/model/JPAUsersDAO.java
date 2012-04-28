package org.users.management.model;

import java.util.LinkedList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.users.management.constants.ErrorMessages;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import java.util.logging.Logger;

/**
 * JPA Data accses object class
 * 
 * @author Mikalai_Kulikou
 * 
 */
public final class JPAUsersDAO implements IUsersDAO {
	private static final String USER = "user";
	private static final Logger logger = Logger.getLogger(JPAUsersDAO.class
			.getName());
	public static final String JPQL_LOAD_USERS = "SELECT from User order by date desc";
	public static final String JPQL_DELETE_ARRAY = "DELETE FROM User where uid in (:users_id_list)";
	public static final String JPQL_ARRAY_PARAM = "users_id_list";

	private final EntityManagerFactory entityManagerFactory = EMF.get();

	@SuppressWarnings("unchecked")
	public LinkedList<User> getList() {
		EntityManager entityManager = null;
		LinkedList<User> usersList = new LinkedList<User>();
		EntityTransaction transaction = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			Query query = entityManager.createQuery(JPQL_LOAD_USERS);
			usersList.addAll(query.getResultList());
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
				e.printStackTrace();
				logger.warning(ErrorMessages.CANNOT_LOAD_USERS);
			}
		} finally {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
		if (usersList != null && usersList.size() == 0) {
			return null;
		}
		return usersList;
	}

	public Key save(User userMessage) {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		Key parentKey = KeyFactory.createKey(User.class.getSimpleName(), USER);
		Key key = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();

			transaction.begin();
			if (userMessage.getKey() == null) {
				key = KeyFactory.createKey(parentKey,
						User.class.getSimpleName(), userMessage.getUid());
				userMessage.setKey(key);
				entityManager.persist(userMessage);
			} else {
				User userToUpdate = entityManager.find(User.class,
						userMessage.getKey());
				userToUpdate.setUid(userMessage.getUid());
				userToUpdate.setName(userMessage.getName());
				userToUpdate.setDate(userMessage.getDate());
				userToUpdate.setJsonData(userMessage.getJsonData());
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
				e.printStackTrace();
				logger.warning(ErrorMessages.CANNOT_SAVE_USER);
			}
		} finally {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return key;
	}

	public void remove(Long[] usersIdArray) {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();

			LinkedList<Long> usersIdList = new LinkedList<Long>();
			for (Long userId : usersIdArray) {
				usersIdList.add(userId);
			}
			Query query = entityManager.createQuery(JPQL_DELETE_ARRAY)
					.setParameter(JPQL_ARRAY_PARAM, usersIdList);

			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
				e.printStackTrace();
				logger.warning(ErrorMessages.CANNOT_DELETE_USERS);
			}
		} finally {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}
}
