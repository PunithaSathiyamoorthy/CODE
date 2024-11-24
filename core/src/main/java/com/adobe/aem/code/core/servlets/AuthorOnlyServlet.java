package com.adobe.aem.code.core.servlets;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.apache.sling.settings.SlingSettingsService;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = { Servlet.class },
        property = {
                "sling.servlet.paths=/bin/myEnvCheckServlet",
                "sling.servlet.methods=GET"
        })
public class AuthorOnlyServlet extends SlingAllMethodsServlet {

    @Reference
    private SlingSettingsService slingSettingsService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        // Determine the current environment based on the run modes
        if (isAuthorEnvironment()) {
            response.getWriter().write("Hello from the author environment!");
        } else if (isPublishEnvironment()) {
            response.getWriter().write("Hello from the publish environment!");
        } else {
            response.sendError(SlingHttpServletResponse.SC_FORBIDDEN, "This servlet is not active in the current environment.");
        }
    }

    private boolean isAuthorEnvironment() {
        return slingSettingsService.getRunModes().contains("author");
    }

    private boolean isPublishEnvironment() {
        return slingSettingsService.getRunModes().contains("publish");
    }
}

