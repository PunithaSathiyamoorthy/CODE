package com.adobe.aem.code.core.service.serviceimpl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="Student Configuration",description = "Student Details")
public @interface StudentConfig1{
    @AttributeDefinition(name="Student Name",
            type= AttributeType.STRING,
            description = "Enter Student Name")
    public String getStudentName();

    @AttributeDefinition(name="Roll Number",
            type=AttributeType.INTEGER,
            description ="Enter Roll Number")
    public int getRollNumber();

}