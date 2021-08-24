package com.aeClub.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListener implements ServletContextListener {
	private static final Logger log = LoggerFactory.getLogger(ApplicationListener.class);
	
//	@Value("${application.production}")
//	private boolean production;
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//sce.getServletContext().setAttribute("production", production);
		log.info("-------------------Application A@E started-------------------------");
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("////////////////////Application destroyed//////////////////////////////");
	}

}
