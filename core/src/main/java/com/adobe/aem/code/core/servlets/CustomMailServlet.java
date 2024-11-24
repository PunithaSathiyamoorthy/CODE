package com.adobe.aem.code.core.servlets;

import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service= Servlet.class,immediate = true,
property = {
        "sling.servlet.method=GET",
        "sling.servlet.paths=/custom/mailservlet"

        })
public class CustomMailServlet  extends SlingAllMethodsServlet {
    @Reference
    MessageGatewayService messageGatewayService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
 if(messageGatewayService !=null){
     MessageGateway<HtmlEmail> gateway = messageGatewayService.getGateway(HtmlEmail.class);
     HtmlEmail htmlEmail = new HtmlEmail();
     try {
         htmlEmail.setFrom("no-reply@gmail.com");
         htmlEmail.addTo("punitha.sathiyamoorthy98@gmail.com");
         htmlEmail.setContent( "Dear Puni, <br/> Sending mail by writing servlet is success<br/>","text/html");
         gateway.send(htmlEmail);
     } catch (EmailException e) {
         throw new RuntimeException(e);
     }
 }

        response.getWriter().write("Custom Servlet Calling");
    }




}
