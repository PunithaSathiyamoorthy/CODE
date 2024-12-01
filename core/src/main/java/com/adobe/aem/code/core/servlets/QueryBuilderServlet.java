package com.adobe.aem.code.core.servlets;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(service = Servlet.class)
@SlingServletPaths(value = "/bin/demo/recent")
public class QueryBuilderServlet extends SlingAllMethodsServlet {

    @Reference
    private QueryBuilder queryBuilder;

    @Override
    protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse res) throws IOException {
        // Prepare query predicates
        Map<String, String> predicate = new HashMap<>();
        predicate.put("type", "cq:Page");
        predicate.put("path", "/content/code");
        predicate.put("property", "@jcr:content/jcr:title");
        predicate.put("property.value", "en");
        predicate.put("p.limit", "-1");

        // Get session from request's resource resolver
        Session session = req.getResourceResolver().adaptTo(Session.class);

        if (session == null) {
            res.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res.getWriter().write("{\"error\": \"Session not available\"}");
            return;
        }

        try {
            // Create the query using the predicates
            Query query = queryBuilder.createQuery(PredicateGroup.create(predicate), session);
            SearchResult result = query.getResult();
            List<Hit> hits = result.getHits();

            // Build JSON response
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (Hit hit : hits) {
                try {
                    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                    Resource resource = hit.getResource();
                    if (resource != null) {
                        Resource content = resource.getResourceResolver().getResource(resource.getPath() + "/jcr:content");

                        // Extract relevant properties
                        if (content != null) {
                            String title = content.getValueMap().get("jcr:title", String.class);
                            if (title != null) {
                                objectBuilder.add("title", title);
                            }
                        }

                        // Add the resource path
                        objectBuilder.add("path", resource.getPath());

                        // Add to array
                        arrayBuilder.add(objectBuilder);
                    }
                } catch (RepositoryException e) {
                    res.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    res.getWriter().write("{\"error\": \"Error processing results\"}");
                    return;
                }
            }

            // Set response content type and write JSON response
            res.setContentType("application/json");
            res.getWriter().write(arrayBuilder.build().toString());

        } catch (Exception e) {
            res.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res.getWriter().write("{\"error\": \"Query execution failed\"}");
        }
    }
}
