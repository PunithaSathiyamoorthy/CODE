package com.adobe.aem.code.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Inject;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},defaultInjectionStrategy =  DefaultInjectionStrategy.OPTIONAL)

public class HomeLoanEmiCalcModel {

//mvc
    @ValueMapValue
    public Double loan_amount;

    @ValueMapValue
    public Double interest;

    @ValueMapValue
    public int tenure;



    public Double getLoan_amount(){
        return loan_amount;
    }

    public Double getInterest(){
        return interest;
    }

    public int getTenure(){
        return tenure;
    }

    public String getFinalresults() {
        Double rate= this.getInterest()/(12*100);
        int time=this.getTenure()*12;
        double emi=(this.getLoan_amount()*rate*Math.pow(1+rate,time)-1);
        double intrest =(emi*time)- this.getLoan_amount();
        double totalPaidAmount = this.getLoan_amount()+intrest;

        return "Monthly EMI:(Rs.)"+ Math.round(emi)+"\n Intrest Amount is : (Rs.)"+Math.round(intrest)+"\nPrinciple Amount is:(Rs.)"+ this.getLoan_amount()+"/n Total Amount Payable:(Rs.)"+Math.round(totalPaidAmount);


    }
    }
