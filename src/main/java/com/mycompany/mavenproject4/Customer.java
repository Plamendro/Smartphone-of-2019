/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject4;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Plamen
 */
@ManagedBean(name = "customer")
@ApplicationScoped
public class Customer implements Serializable {

    public Customer() {
    }
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthDate;
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    private String addrLine1;
    private String addrCity;
    private String addrCountry;
    private String addrZip;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String mobileManifacturer1;
    private String mobileManifacturer2;
    private String mobileManifacturer3;
    private String email;
    //for downloading file
    private StreamedContent file;
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getMobileManifacturer3() {
        return mobileManifacturer3;
    }

    public void setMobileManifacturer3(String mobileManifacturer3) {
        this.mobileManifacturer3 = mobileManifacturer3;
    }

    public String getMobileManifacturer1() {
        return mobileManifacturer1;
    }

    public void setMobileManifacturer1(String mobileManifacturer1) {
        this.mobileManifacturer1 = mobileManifacturer1;
    }

    public String getMobileManifacturer2() {
        return mobileManifacturer2;
    }

    public void setMobileManifacturer2(String mobileManifacturer2) {
        this.mobileManifacturer2 = mobileManifacturer2;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFormattedBirthDate() {
        String formattedDate = "";

        if (birthDate != null) {
            formattedDate = sdf.format(birthDate);

        }
        return formattedDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getAddrLine1() {
        return addrLine1;
    }

    public void setAddrLine1(String addrLine1) {
        this.addrLine1 = addrLine1;
    }

    public String getAddrCountry() {
        return addrCountry;
    }

    public void setAddrCountry(String addrCountry) {
        this.addrCountry = addrCountry;
    }

    public String getAddrZip() {
        return addrZip;
    }

    public void setAddrZip(String addrZip) {
        this.addrZip = addrZip;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public void exportCSV() throws IOException {

        String csvFile = "D:\\CustomerData.csv";
        try (FileWriter writer = new FileWriter(csvFile, true)) {
            CSVUtils.writeLine(writer, Arrays.asList("First name ", "Middle name ", "Last name ", "Mobile Phone ", "Country ", "City ", "Street ", "PostalCode ", "Email","Voted for"));
            CSVUtils.writeLine(writer, Arrays.asList(firstName, middleName, lastName, mobilePhone, addrCountry, addrCountry, addrLine1, addrZip,email, mobileManifacturer1));

            writer.flush();
        }
    }

    public void download() throws IOException {
        String file_name = "D:\\CustomerData.csv";
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

        response.reset();
        response.setContentType("text/comma-separated-values");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file_name + "\"");

        try (OutputStream output = response.getOutputStream()) {
            List<String> strings = new ArrayList<>();

            strings.add("Firstname: " + firstName);
            strings.add("\n");
            strings.add("Middlename: " + middleName);
            strings.add("\n");
            strings.add("Lastname: " + lastName);
            strings.add("\n");
             strings.add("Email: " + email);
            strings.add("\n");
            strings.add("Birthdate: " + birthDate);
            strings.add("\n");
            strings.add("Country: "+addrCountry);
            strings.add("\n");
            strings.add("City: "+addrCity);
            strings.add("\n");
            strings.add("Street: "+addrLine1);
            strings.add("\n");
            strings.add("Postal Code: "+addrZip);
            strings.add("\n");
            strings.add("Mobile phone: "+mobilePhone);
            strings.add("\n");
            strings.add("Voted for " + mobileManifacturer1);

            for (String s : strings) {
                output.write(s.getBytes());
            }
            output.flush();
        }
        fc.responseComplete();
    }

    public StreamedContent getFile() {
        return this.file;
    }

}
