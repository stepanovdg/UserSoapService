package org.users.management.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Generates Entity Manager Factory
 * 
 * @author Google
 *
 */
public final class EMF {
    private static final EntityManagerFactory emfInstance =
        Persistence.createEntityManagerFactory("transactions-optional");

    private EMF() {}

    public static EntityManagerFactory get() {
        return emfInstance;
    }
}