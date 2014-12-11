/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.orchestra.portale.persistence.mongo.documents;

import java.util.ArrayList;
import org.springframework.data.annotation.TypeAlias;


@TypeAlias("com.orchestra.portale.persistence.mongo.documents.ContactsComponent")
public class ContactsComponent extends AbstractPoiComponent{
    private ArrayList<PhoneContact> phoneList;
    private ArrayList<EmailContact> emailsList;
    private ArrayList<FaxContact> faxList;
    private ArrayList<GenericSocial> socialList;
    private String facebook;
    private String twitter;
    private String google;
    private String skype;

    public void setFaxList(ArrayList<FaxContact> faxList) {
        this.faxList = faxList;
    }

    public void setSocialList(ArrayList<GenericSocial> socialList) {
        this.socialList = socialList;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public ArrayList<FaxContact> getFaxList() {
        return faxList;
    }

    public ArrayList<GenericSocial> getSocialList() {
        return socialList;
    }

    public String getGoogle() {
        return google;
    }

    public String getSkype() {
        return skype;
    }

    public ArrayList<PhoneContact> getPhoneList() {
        return phoneList;
    }

    public ArrayList<EmailContact> getEmailsList() {
        return emailsList;
    }

    public void setPhoneList(ArrayList<PhoneContact> phoneList) {
        this.phoneList = phoneList;
    }

    public void setEmailsList(ArrayList<EmailContact> emailsList) {
        this.emailsList = emailsList;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getTwitter() {
        return twitter;
    }
    
    
}
