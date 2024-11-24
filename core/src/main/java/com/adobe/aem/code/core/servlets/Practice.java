package com.adobe.aem.code.core.servlets;

import com.adobe.aem.code.core.service.PracticeService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import java.io.IOException;

import static java.lang.invoke.VarHandle.AccessMode.GET;

@Component(service = Servlet.class, property = {
        ServletResolverConstants.SLING_SERVLET_PATHS + "=/custom/code",
        ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_GET })

@ServiceDescription("Simple Demo Servlet")
public class Practice extends SlingSafeMethodsServlet {

    private static final long serialVersionUID=1L;

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse res) throws IOException, IOException {
        res.setContentType("text/plain");
            res.getWriter().write("Hello World!!!");

        }

    }

