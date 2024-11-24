package com.adobe.aem.code.core.service.serviceimpl;

import com.adobe.aem.code.core.service.PracticeService;
import org.osgi.service.component.annotations.Component;

@Component(service= PracticeService.class,immediate = true)
public class PracticeServiceImpl implements PracticeService {
  @Override
    public  String getServiceName(){
        return "My first Osgi Service referenced in servlet";
    }
}
