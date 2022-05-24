
package com.org.tests.tq.multipartbatchpicks;

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
    "verifiedByOrderingPersonFirstName",
    "verifiedByOrderingPersonLastName",
    "orderPlacedByOrderingPersonFirstName",
    "orderPlacedByOrderingPersonLastName",
    "orderReviewedByOrderingPersonName"
})
public class OrderingPersonInformation {

    @JsonProperty("verifiedByOrderingPersonFirstName")
    private String verifiedByOrderingPersonFirstName;
    @JsonProperty("verifiedByOrderingPersonLastName")
    private String verifiedByOrderingPersonLastName;
    @JsonProperty("orderPlacedByOrderingPersonFirstName")
    private String orderPlacedByOrderingPersonFirstName;
    @JsonProperty("orderPlacedByOrderingPersonLastName")
    private String orderPlacedByOrderingPersonLastName;
    @JsonProperty("orderReviewedByOrderingPersonName")
    private String orderReviewedByOrderingPersonName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("verifiedByOrderingPersonFirstName")
    public String getVerifiedByOrderingPersonFirstName() {
        return verifiedByOrderingPersonFirstName;
    }

    @JsonProperty("verifiedByOrderingPersonFirstName")
    public void setVerifiedByOrderingPersonFirstName(String verifiedByOrderingPersonFirstName) {
        this.verifiedByOrderingPersonFirstName = verifiedByOrderingPersonFirstName;
    }

    @JsonProperty("verifiedByOrderingPersonLastName")
    public String getVerifiedByOrderingPersonLastName() {
        return verifiedByOrderingPersonLastName;
    }

    @JsonProperty("verifiedByOrderingPersonLastName")
    public void setVerifiedByOrderingPersonLastName(String verifiedByOrderingPersonLastName) {
        this.verifiedByOrderingPersonLastName = verifiedByOrderingPersonLastName;
    }

    @JsonProperty("orderPlacedByOrderingPersonFirstName")
    public String getOrderPlacedByOrderingPersonFirstName() {
        return orderPlacedByOrderingPersonFirstName;
    }

    @JsonProperty("orderPlacedByOrderingPersonFirstName")
    public void setOrderPlacedByOrderingPersonFirstName(String orderPlacedByOrderingPersonFirstName) {
        this.orderPlacedByOrderingPersonFirstName = orderPlacedByOrderingPersonFirstName;
    }

    @JsonProperty("orderPlacedByOrderingPersonLastName")
    public String getOrderPlacedByOrderingPersonLastName() {
        return orderPlacedByOrderingPersonLastName;
    }

    @JsonProperty("orderPlacedByOrderingPersonLastName")
    public void setOrderPlacedByOrderingPersonLastName(String orderPlacedByOrderingPersonLastName) {
        this.orderPlacedByOrderingPersonLastName = orderPlacedByOrderingPersonLastName;
    }

    @JsonProperty("orderReviewedByOrderingPersonName")
    public String getOrderReviewedByOrderingPersonName() {
        return orderReviewedByOrderingPersonName;
    }

    @JsonProperty("orderReviewedByOrderingPersonName")
    public void setOrderReviewedByOrderingPersonName(String orderReviewedByOrderingPersonName) {
        this.orderReviewedByOrderingPersonName = orderReviewedByOrderingPersonName;
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
