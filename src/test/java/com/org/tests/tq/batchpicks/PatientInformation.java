
package com.org.tests.tq.batchpicks;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "patientID",
    "firstName",
    "lastName",
    "middleName",
    "nameSuffixText",
    "dateOfBirthText",
    "accountID",
    "allegeriesText"
})
public class PatientInformation {

    @JsonProperty("patientID")
    private String patientID;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("middleName")
    private String middleName;
    @JsonProperty("nameSuffixText")
    private String nameSuffixText;
    @JsonProperty("dateOfBirthText")
    private String dateOfBirthText;
    @JsonProperty("accountID")
    private String accountID;
    @JsonProperty("allegeriesText")
    private String allegeriesText;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("patientID")
    public String getPatientID() {
        return patientID;
    }

    @JsonProperty("patientID")
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("middleName")
    public String getMiddleName() {
        return middleName;
    }

    @JsonProperty("middleName")
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @JsonProperty("nameSuffixText")
    public String getNameSuffixText() {
        return nameSuffixText;
    }

    @JsonProperty("nameSuffixText")
    public void setNameSuffixText(String nameSuffixText) {
        this.nameSuffixText = nameSuffixText;
    }

    @JsonProperty("dateOfBirthText")
    public String getDateOfBirthText() {
        return dateOfBirthText;
    }

    @JsonProperty("dateOfBirthText")
    public void setDateOfBirthText(String dateOfBirthText) {
        this.dateOfBirthText = dateOfBirthText;
    }

    @JsonProperty("accountID")
    public String getAccountID() {
        return accountID;
    }

    @JsonProperty("accountID")
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    @JsonProperty("allegeriesText")
    public String getAllegeriesText() {
        return allegeriesText;
    }

    @JsonProperty("allegeriesText")
    public void setAllegeriesText(String allegeriesText) {
        this.allegeriesText = allegeriesText;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
