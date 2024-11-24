package com.adobe.aem.code.core.servlets;

import java.io.IOException;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = { Servlet.class })
@SlingServletResourceTypes(
        resourceTypes="code/components/page",
        methods= "{HttpConstants.METHOD_GET,HttpConstants.METHOD_POST}",
        extensions="{txt,html}")

public class PageCreationAndPublish extends SlingAllMethodsServlet {

    public void doGet(SlingHttpServletRequest req, SlingHttpServletResponse res) {
        try {
            ResourceResolver resourceResolver = req.getResourceResolver();
            String pagePath = createPage(resourceResolver);
            Session session = resourceResolver.adaptTo(Session.class);
            res.getWriter().write("Page Created Successfully....");
            deletePage(session, pagePath, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePage(Session session, String pagePath, SlingHttpServletResponse res)
            throws RepositoryException, IOException {
        if (session.nodeExists(pagePath)) {
            session.removeItem(pagePath);
            session.save();
            res.getWriter().write("Page deleted successfully");
        } else {
            res.getWriter().write("Page does not exist.");
        }
    }

    public String createPage(ResourceResolver resourceResolver) {
        String path = "/content/wknd-events";// parent path
        String pageName = "samplePage";
        String pageTitle = "Sample Page";
        String resourceType = "wknd/components/page";
        String template = "/conf/wknd-events/settings/wcm/templates/teaser-page";
        Session session = resourceResolver.adaptTo(Session.class);
        Page prodPage = null;
        try {
            if (session != null) {
                PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
                prodPage = pageManager.create(path, pageName, template, pageTitle);
                Node node = prodPage.adaptTo(Node.class);

                Node jcrNode = null;
                if (prodPage.hasContent()) {
                    jcrNode = prodPage.getContentResource().adaptTo(Node.class);
                } else {
                    jcrNode = node.addNode("jcr:content", "cq:PageContent");
                }

                jcrNode.setProperty("sling:resourceType", resourceType);
                Node parNode = jcrNode.addNode("par");
                parNode.setProperty("sling:resourceType", "foundation/components/parsys");

                Node textNode = parNode.addNode("text");
                textNode.setProperty("sling:resourceType", "foundation/components/text");
                textNode.setProperty("textIsRich", "true");
                textNode.setProperty("text", "Test Page");
                session.save();
                session.refresh(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prodPage.getPath();
    }

}