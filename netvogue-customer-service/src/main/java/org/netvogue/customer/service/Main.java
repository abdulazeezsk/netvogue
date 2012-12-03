package org.netvogue.customer.service;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.web.context.ContextLoaderListener;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;

public class Main {

  public static void main(final String args[]) throws Exception {

    int MAX_THREADS = 100;

    String webPort = System.getenv("PORT");
    if (webPort == null || webPort.isEmpty()) {
      webPort = "8081";
    }

    Server server = new Server(Integer.valueOf(webPort));

    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    server.setHandler(context);

    ContextLoaderListener listener = new ContextLoaderListener();
    context.addEventListener(listener);
    context.getInitParams().put("contextConfigLocation", "classpath:/org/netvogue/customer/service/application-context.xml");

    ServletHolder holder = new ServletHolder(new SpringServlet());
    holder.setInitParameter("com.sun.jersey.config.property.packages", "org.netvogue.customer.service;org.netvogue.rest.exceptions");
    holder.setInitParameter("contextConfigLocation", "classpath:/org/netvogue/customer/service/application-context.xml");
//    holder.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");


    if(System.getProperty("REQUEST_TRACE") != null) {
      holder.setInitParameter("com.sun.jersey.config.feature.Trace", "true");
      holder.setInitParameter("com.sun.jersey.spi.container.ContainerResponseFilters", "com.sun.jersey.api.container.filter.LoggingFilter");
      holder.setInitParameter("com.sun.jersey.spi.container.ContainerRequestFilters", "com.sun.jersey.api.container.filter.LoggingFilter");
    }

    holder.setInitOrder(-1);

    context.addServlet(holder, "/*");

    server.setThreadPool(new QueuedThreadPool(MAX_THREADS));
    server.start();
    context.getServletHandler().initialize();
    server.join();

  }
}
