package com.adobe.aem.code.core.servlets;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.framework.Constants;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(
        service = { Servlet.class },
        property = {
                Constants.SERVICE_DESCRIPTION + "=JSON Servlet with Query Parameters",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.paths=" + "/bin/jsonservlet"
        }
)
public class OutputJson extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Getting query parameters
        String nameParam = request.getParameter("name");
        String ageParam = request.getParameter("age");

        JSONObject jsonResponse = new JSONObject();

        try {
            // Check if name parameter is provided
            if (StringUtils.isNotBlank(nameParam)) {
                jsonResponse.put("name", nameParam);
            } else {
                jsonResponse.put("error", "Name parameter is required");
            }

            // Check if age parameter is provided and is a valid number
            if (StringUtils.isNotBlank(ageParam) && StringUtils.isNumeric(ageParam)) {
                jsonResponse.put("age", Integer.parseInt(ageParam));
            } else {
                jsonResponse.put("error", "Age parameter is invalid or missing");
            }

        } catch (JSONException e) {
            response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error while creating JSON response: " + e.getMessage());
            return;
        }

        // Writing JSON to response
        response.getWriter().write(jsonResponse.toString());
    }
}
