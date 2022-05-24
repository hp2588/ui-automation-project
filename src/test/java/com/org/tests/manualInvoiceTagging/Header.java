
package com.org.tests.manualInvoiceTagging;

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
    "requestID",
    "timeStamp",
    "version",
    "tenantKey",
    "messageType",
    "facilityCode",
    "externalSystemName"
})
public class Header {

    @JsonProperty("requestID")
    private String requestID;
    @JsonProperty("timeStamp")
    private String timeStamp;
    @JsonProperty("version")
    private String version;
    @JsonProperty("tenantKey")
    private String tenantKey;
    @JsonProperty("messageType")
    private String messageType;
    @JsonProperty("facilityCode")
    private String facilityCode;
    @JsonProperty("externalSystemName")
    private String externalSystemName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("requestID")
    public String getRequestID() {
        return requestID;
    }

    @JsonProperty("requestID")
    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    @JsonProperty("timeStamp")
    public String getTimeStamp() {
        return timeStamp;
    }

    @JsonProperty("timeStamp")
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("tenantKey")
    public String getTenantKey() {
        return tenantKey;
    }

    @JsonProperty("tenantKey")
    public void setTenantKey(String tenantKey) {
        this.tenantKey = tenantKey;
    }

    @JsonProperty("messageType")
    public String getMessageType() {
        return messageType;
    }

    @JsonProperty("messageType")
    public void setMessageType(String messageType) {
        this.messageType = messageType;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
