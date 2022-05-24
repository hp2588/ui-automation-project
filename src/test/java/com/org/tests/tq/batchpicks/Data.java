
package com.org.tests.tq.batchpicks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "receivedDateTime",
    "facilityCode",
    "externalSystemName",
    "transactionPriorityCode",
    "deliveryDestinationName",
    "pharmacyOrderDetail",
    "patientEncounterDetail",
    "patientInformation",
    "orderingPersonInformation",
    "userDefine",
    "orderComponents"
})
public class Data {

    @JsonProperty("receivedDateTime")
    private String receivedDateTime;
    @JsonProperty("facilityCode")
    private String facilityCode;
    @JsonProperty("externalSystemName")
    private String externalSystemName;
    @JsonProperty("transactionPriorityCode")
    private String transactionPriorityCode;
    @JsonProperty("deliveryDestinationName")
    private String deliveryDestinationName;
    @JsonProperty("pharmacyOrderDetail")
    private PharmacyOrderDetail pharmacyOrderDetail;
    @JsonProperty("patientEncounterDetail")
    private PatientEncounterDetail patientEncounterDetail;
    @JsonProperty("patientInformation")
    private PatientInformation patientInformation;
    @JsonProperty("orderingPersonInformation")
    private OrderingPersonInformation orderingPersonInformation;
    @JsonProperty("userDefine")
    private UserDefine userDefine;
    @JsonProperty("orderComponents")
    private List<OrderComponent> orderComponents = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("receivedDateTime")
    public String getReceivedDateTime() {
        return receivedDateTime;
    }

    @JsonProperty("receivedDateTime")
    public void setReceivedDateTime(String receivedDateTime) {
        this.receivedDateTime = receivedDateTime;
    }

    @JsonProperty("facilityCode")
    public String getFacilityCode() {
        return facilityCode;
    }

    @JsonProperty("facilityCode")
    public void setFacilityCode(String facilityCode) {
        this.facilityCode = facilityCode;
    }

    @JsonProperty("externalSystemName")
    public String getExternalSystemName() {
        return externalSystemName;
    }

    @JsonProperty("externalSystemName")
    public void setExternalSystemName(String externalSystemName) {
        this.externalSystemName = externalSystemName;
    }

    @JsonProperty("transactionPriorityCode")
    public String getTransactionPriorityCode() {
        return transactionPriorityCode;
    }

    @JsonProperty("transactionPriorityCode")
    public void setTransactionPriorityCode(String transactionPriorityCode) {
        this.transactionPriorityCode = transactionPriorityCode;
    }

    @JsonProperty("deliveryDestinationName")
    public String getDeliveryDestinationName() {
        return deliveryDestinationName;
    }

    @JsonProperty("deliveryDestinationName")
    public void setDeliveryDestinationName(String deliveryDestinationName) {
        this.deliveryDestinationName = deliveryDestinationName;
    }

    @JsonProperty("pharmacyOrderDetail")
    public PharmacyOrderDetail getPharmacyOrderDetail() {
        return pharmacyOrderDetail;
    }

    @JsonProperty("pharmacyOrderDetail")
    public void setPharmacyOrderDetail(PharmacyOrderDetail pharmacyOrderDetail) {
        this.pharmacyOrderDetail = pharmacyOrderDetail;
    }

    @JsonProperty("patientEncounterDetail")
    public PatientEncounterDetail getPatientEncounterDetail() {
        return patientEncounterDetail;
    }

    @JsonProperty("patientEncounterDetail")
    public void setPatientEncounterDetail(PatientEncounterDetail patientEncounterDetail) {
        this.patientEncounterDetail = patientEncounterDetail;
    }

    @JsonProperty("patientInformation")
    public PatientInformation getPatientInformation() {
        return patientInformation;
    }

    @JsonProperty("patientInformation")
    public void setPatientInformation(PatientInformation patientInformation) {
        this.patientInformation = patientInformation;
    }

    @JsonProperty("orderingPersonInformation")
    public OrderingPersonInformation getOrderingPersonInformation() {
        return orderingPersonInformation;
    }

    @JsonProperty("orderingPersonInformation")
    public void setOrderingPersonInformation(OrderingPersonInformation orderingPersonInformation) {
        this.orderingPersonInformation = orderingPersonInformation;
    }

    @JsonProperty("userDefine")
    public UserDefine getUserDefine() {
        return userDefine;
    }

    @JsonProperty("userDefine")
    public void setUserDefine(UserDefine userDefine) {
        this.userDefine = userDefine;
    }

    @JsonProperty("orderComponents")
    public List<OrderComponent> getOrderComponents() {
        return orderComponents;
    }

    @JsonProperty("orderComponents")
    public void setOrderComponents(List<OrderComponent> orderComponents) {
        this.orderComponents = orderComponents;
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
