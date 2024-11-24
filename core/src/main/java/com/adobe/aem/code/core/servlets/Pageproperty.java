package com.adobe.aem.code.core.servlets;


import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths(value="/custom/demo/servlet")

public class Pageproperty extends SlingAllMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(Pageproperty.class);

    @Reference
    private transient PageManager pageManager;

    @SlingObject
    private SlingHttpServletRequest request;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getParameter("path");

        if (path == null || path.isEmpty()) {
            response.setStatus(SlingHttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Page path is required");
            return;
        }

        PageManager pageManager = request.getResourceResolver().adaptTo(PageManager.class);
        Page page = pageManager.getPage(path);

        if (page == null) {
            response.setStatus(SlingHttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Page not found");
            return;
        }

        JsonObject json = new JsonObject();
        json.addProperty("title", page.getTitle());
        json.addProperty("description", page.getDescription());
        json.addProperty("path", page.getPath());

        // Add more properties as needed

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toString());
    }
}
