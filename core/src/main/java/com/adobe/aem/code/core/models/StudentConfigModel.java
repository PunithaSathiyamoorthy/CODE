package com.adobe.aem.code.core.models;

import com.adobe.aem.code.core.service.serviceimpl.StudentsConfigMethods;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

@Model(adaptables = SlingHttpServletRequest.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class StudentConfigModel {

    //osgiservice annotation to call the osgi in sling model

    @OSGiService
    StudentsConfigMethods osgiconfig;

    public String getStudentName(){
        return osgiconfig.getStudentName();
    }

    public int getRollNumber(){
        return osgiconfig.getRollNumber();
    }

}
