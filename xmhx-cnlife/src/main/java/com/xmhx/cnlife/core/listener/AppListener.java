package com.xmhx.cnlife.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * web.xml装载
 */
public class AppListener implements ServletContextListener {
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent context) {
    	System.setProperty("root", context.getServletContext().getRealPath("/"));
    	System.setProperty("log_dir", context.getServletContext().getRealPath(""));
    }

    public void contextDestroyed(ServletContextEvent arg) {}
}
