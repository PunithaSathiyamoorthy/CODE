package com.adobe.aem.code.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables= Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,resourceType = "code/components/hello")
@Exporter(name = "jackson", extensions = "json",selector = "punitha")
//custom selector
public class SlingModelExporterExample {
    @ValueMapValue
    public String text;
    @ValueMapValue
    public String firstName;
    @ValueMapValue
    public String lastName;

    @ValueMapValue(name = "sling:resourceType",injectionStrategy = InjectionStrategy.OPTIONAL)
    public String slingResourceType;

    @ValueMapValue(name = "jcr:lastModifiedBy")
    public String lastModifiedBy;

    @ValueMapValue(name = "jcr:lastModified")
    public String lastModifiedDate;

    @ValueMapValue(name = "jcr:createdBy")
    public String createdBy;
    @JsonProperty(value="Checking")
    public String checknongettervalue(){
        return "Not a Getter Value";
    }

    @JsonIgnore
    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getSlingResourceType() {
        return slingResourceType;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getText() {
        return text;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


}
