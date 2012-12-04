package org.netvogue.server;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class Main {

  public static void main(final String args[]) throws Exception {

    int MAX_THREADS = 100;

    String webPort = System.getenv("PORT");
    if (webPort == null || webPort.isEmpty()) {
      webPort = "8080";
    }

    
    Server server = new Server(Integer.valueOf(webPort));


    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    server.setHandler(context);
    
    FilterHolder filterHolder = new FilterHolder();
    filterHolder.setName("springSecurityFilterChain");
    filterHolder.setFilter(new DelegatingFilterProxy());
    
    context.addFilter(filterHolder, "/*", EnumSet.allOf(DispatcherType.class));

    ContextLoaderListener listener = new ContextLoaderListener();
    context.addEventListener(listener);
    context.getInitParams().put("contextConfigLocation", "classpath:root-context.xml");

    ServletHolder holder = new ServletHolder(new DispatcherServlet());
    holder.setInitParameter("contextConfigLocation", "classpath:dispatcher-servlet.xml");


    context.addServlet(holder, "/*");

    server.setThreadPool(new QueuedThreadPool(MAX_THREADS));
    server.start();
    context.getServletHandler().initialize();
    server.join();

  }
}
