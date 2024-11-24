package com.adobe.aem.code.core.servlets;

import javax.servlet.Servlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import java.util.Map;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;


@Component(service = { Servlet.class },
        property = {
                "sling.servlet.paths=/bin/demo/recent",
                "sling.servlet.methods=GET"
        })

public class querybuilder extends SlingAllMethodsServlet {

    @Reference
    QueryBuilder queryBuilder;

    protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse res) throws IOException
    {
        Map<String,String> predicate = new HashMap<>();
        predicate.put("type", "cq:Page");
        predicate.put("path", "/content/code");
        predicate.put("orderby", "@jcr:content/cq:lastModified");
        predicate.put("orderby.sort", "desc");
        predicate.put("p.limit", "-1");
        Session session = req.getResourceResolver().adaptTo(Session.class);
        Query query = queryBuilder.createQuery(PredicateGroup.create(predicate),session);
        SearchResult result = query.getResult();
        List<Hit> hits = result.getHits();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(Hit hit:hits)
        {
            try {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                Resource resource = hit.getResource();
                Resource content = resource.getResourceResolver().getResource(resource + "/jcr:content");
                if(content.getValueMap().get("jcr:title",String.class)!= null){
                    objectBuilder.add("title", content.getValueMap().get("jcr:title",String.class));
                }
                if(resource.getPath() != null){
                    objectBuilder.add("path", resource.getPath());
                }
                arrayBuilder.add(objectBuilder);
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
        }
        res.getWriter().write(arrayBuilder.build().toString());
    }
}