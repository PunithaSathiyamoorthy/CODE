package com.adobe.aem.code.core.service;


import org.apache.sling.api.resource.ResourceResolver;

/**
 * @author Anirudh Sharma
 * <p>
 * This service gives instance of the resource resolver using service user approach
 */
public interface ResourceResolverService {

    /**
     * This method returns the instance of resource resolver
     *
     * @return @{@link ResourceResolver}
     */
    ResourceResolver getResourceResolver();
}