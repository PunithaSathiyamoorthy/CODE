package com.adobe.aem.code.core.service.serviceimpl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

//it will listen the life cycle of osgi
@Component(service = StudentsConfigMethods.class,immediate = true)
//to call the osgi service we need to use designate
@Designate(ocd= StudentConfig1.class)
public class StudentConfigImpl implements StudentsConfigMethods {

    private String studentName;
    private int rollNumber;

    @Activate()
    protected void start(StudentConfig1 config){

        studentName = config.getStudentName();
        rollNumber=config.getRollNumber();
    }
    @Override
    public String getStudentName() {
        return studentName ;
    }

    @Override
    public int getRollNumber() {
        return rollNumber;
    }
}
