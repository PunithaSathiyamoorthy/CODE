package com.adobe.aem.code.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.propertytypes.ServiceDescription;

//http://localhost:4503/bin/demopathbasedservlet

@Component(service = Servlet.class, configurationPolicy = ConfigurationPolicy.REQUIRE,
        property = { "sling.servlet.methods=GET",
                "sling.servlet.paths=" + "/bin/runmodebasedservlet" })


@ServiceDescription("RunModeBasedServlet")
public class RunModeBasedServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
            throws ServletException, IOException {


        resp.getWriter().println("Inside RunModeBasedServlet");

    }


}
