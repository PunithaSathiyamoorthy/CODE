package com.adobe.aem.code.core.service;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service= servicetest.class,immediate = true,name="servicetest")
public class servicetest {
    public static Logger log = LoggerFactory.getLogger(servicetest.class);

    @Activate()
    public void activate(){
        log.info("activate method is executed");

    }
    @Deactivate()
    public void deactivate(){
        log.info("Deactivate method is executed");
        //when you delete the bundle decativate method is executed
    }

}
