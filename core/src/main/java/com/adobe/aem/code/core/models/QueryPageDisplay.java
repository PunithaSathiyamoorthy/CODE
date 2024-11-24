package com.adobe.aem.code.core.models;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import javax.jcr.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class QueryPageDisplay {
    @SlingObject
    ResourceResolver resourceResolver;
    @SlingObject
    List<Hit> latestPageDetails = new ArrayList<>();
    @PostConstruct
    protected void init(){
        setLatestPageDetails();

    }
public void setLatestPageDetails(){
    QueryBuilder queryBuilder = resourceResolver.adaptTo(QueryBuilder.class);
    Session session = resourceResolver.adaptTo(Session.class);
    Map<String, String> queryMap = new HashMap<>();
    queryMap.put("path","/content/we-retail/us/en");
    queryMap.put("type","cq:page");
    queryMap.put("p.limit","-1");
    queryMap.put("fulltext","women");

    Query query = queryBuilder.createQuery(PredicateGroup.create(queryMap), session);
    SearchResult result = query.getResult();
    latestPageDetails = result.getHits();
}
    public List<Hit> getLatestPageDetails() {
        return latestPageDetails;
    }

}
