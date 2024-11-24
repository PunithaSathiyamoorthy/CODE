package com.adobe.aem.code.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HelloWorldMultifield {
    @ValueMapValue
    public String text;

    @ValueMapValue
    public List<String> firstname;


    public String getText() {
        return text;
    }

    public List<String> getFirstname() {
        return firstname;
    }






}
