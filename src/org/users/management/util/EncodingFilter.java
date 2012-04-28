package org.users.management.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Encodes charset traffic
 * 
 * @author Dzmitry_Stsiapanau
 * 
 */
public class EncodingFilter implements Filter {

	private static final String UTF_8 = "UTF-8";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String reqEncoding = request.getCharacterEncoding();
		String defEncoding = UTF_8;

		if (!defEncoding.equals(reqEncoding)) {
			request.setCharacterEncoding(defEncoding);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}
