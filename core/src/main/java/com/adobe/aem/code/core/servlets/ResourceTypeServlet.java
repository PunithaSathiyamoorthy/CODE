package com.adobe.aem.code.core.servlets;
import org.apache.sling.api.servlets.HttpConstants;
import com.adobe.aem.code.core.service.PracticeService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.servlet.Servlet;
import java.io.IOException;


@Component(service = Servlet.class, property = {
        ServletResolverConstants.SLING_SERVLET_PATHS + "=/custom/code/resource",
        ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_GET })

@ServiceDescription("OSGI servlet")
public class ResourceTypeServlet extends SlingSafeMethodsServlet {

    @Reference
    PracticeService practiceService;

    @Override

    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws IOException {

        resp.setContentType("text/plain");
        resp.getWriter().write("Hello Girl" + "  " + practiceService.getServiceName());
    }


}
