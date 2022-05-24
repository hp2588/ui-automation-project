
package com.org.apiDataCreationSuite;

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
    "activeFlag",
    "pharmacyInformationSystemFlag",
    "systemType",
    "externalSystemName",
    "TimeZoneID",
    "pisProvidesMedClassFlag",
    "pisProvidesTherapeuticClassFlag",
    "allowPISItemEditFlag",
    "editExternalScanCodeLinksFlag",
    "ignorePISItemDeleteFlag",
    "ignorePISItemUpdateFlag"
})
public class ExternalSystem {

    @JsonProperty("activeFlag")
    private Boolean activeFlag;
    @JsonProperty("pharmacyInformationSystemFlag")
    private Boolean pharmacyInformationSystemFlag;
    @JsonProperty("systemType")
    private String systemType;
    @JsonProperty("externalSystemName")
    private String externalSystemName;
    @JsonProperty("TimeZoneID")
    private String timeZoneID;
    @JsonProperty("pisProvidesMedClassFlag")
    private Boolean pisProvidesMedClassFlag;
    @JsonProperty("pisProvidesTherapeuticClassFlag")
    private Boolean pisProvidesTherapeuticClassFlag;
    @JsonProperty("allowPISItemEditFlag")
    private Boolean allowPISItemEditFlag;
    @JsonProperty("editExternalScanCodeLinksFlag")
    private Boolean editExternalScanCodeLinksFlag;
    @JsonProperty("ignorePISItemDeleteFlag")
    private Boolean ignorePISItemDeleteFlag;
    @JsonProperty("ignorePISItemUpdateFlag")
    private Boolean ignorePISItemUpdateFlag;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("activeFlag")
    public Boolean getActiveFlag() {
        return activeFlag;
    }

    @JsonProperty("activeFlag")
    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    @JsonProperty("pharmacyInformationSystemFlag")
    public Boolean getPharmacyInformationSystemFlag() {
        return pharmacyInformationSystemFlag;
    }

    @JsonProperty("pharmacyInformationSystemFlag")
    public void setPharmacyInformationSystemFlag(Boolean pharmacyInformationSystemFlag) {
        this.pharmacyInformationSystemFlag = pharmacyInformationSystemFlag;
    }

    @JsonProperty("systemType")
    public String getSystemType() {
        return systemType;
    }

    @JsonProperty("systemType")
    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    @JsonProperty("externalSystemName")
    public String getExternalSystemName() {
        return externalSystemName;
    }

    @JsonProperty("externalSystemName")
    public void setExternalSystemName(String externalSystemName) {
        this.externalSystemName = externalSystemName;
    }

    @JsonProperty("TimeZoneID")
    public String getTimeZoneID() {
        return timeZoneID;
    }

    @JsonProperty("TimeZoneID")
    public void setTimeZoneID(String timeZoneID) {
        this.timeZoneID = timeZoneID;
    }

    @JsonProperty("pisProvidesMedClassFlag")
    public Boolean getPisProvidesMedClassFlag() {
        return pisProvidesMedClassFlag;
    }

    @JsonProperty("pisProvidesMedClassFlag")
    public void setPisProvidesMedClassFlag(Boolean pisProvidesMedClassFlag) {
        this.pisProvidesMedClassFlag = pisProvidesMedClassFlag;
    }

    @JsonProperty("pisProvidesTherapeuticClassFlag")
    public Boolean getPisProvidesTherapeuticClassFlag() {
        return pisProvidesTherapeuticClassFlag;
    }

    @JsonProperty("pisProvidesTherapeuticClassFlag")
    public void setPisProvidesTherapeuticClassFlag(Boolean pisProvidesTherapeuticClassFlag) {
        this.pisProvidesTherapeuticClassFlag = pisProvidesTherapeuticClassFlag;
    }

    @JsonProperty("allowPISItemEditFlag")
    public Boolean getAllowPISItemEditFlag() {
        return allowPISItemEditFlag;
    }

    @JsonProperty("allowPISItemEditFlag")
    public void setAllowPISItemEditFlag(Boolean allowPISItemEditFlag) {
        this.allowPISItemEditFlag = allowPISItemEditFlag;
    }

    @JsonProperty("editExternalScanCodeLinksFlag")
    public Boolean getEditExternalScanCodeLinksFlag() {
        return editExternalScanCodeLinksFlag;
    }

    @JsonProperty("editExternalScanCodeLinksFlag")
    public void setEditExternalScanCodeLinksFlag(Boolean editExternalScanCodeLinksFlag) {
        this.editExternalScanCodeLinksFlag = editExternalScanCodeLinksFlag;
    }

    @JsonProperty("ignorePISItemDeleteFlag")
    public Boolean getIgnorePISItemDeleteFlag() {
        return ignorePISItemDeleteFlag;
    }

    @JsonProperty("ignorePISItemDeleteFlag")
    public void setIgnorePISItemDeleteFlag(Boolean ignorePISItemDeleteFlag) {
        this.ignorePISItemDeleteFlag = ignorePISItemDeleteFlag;
    }

    @JsonProperty("ignorePISItemUpdateFlag")
    public Boolean getIgnorePISItemUpdateFlag() {
        return ignorePISItemUpdateFlag;
    }

    @JsonProperty("ignorePISItemUpdateFlag")
    public void setIgnorePISItemUpdateFlag(Boolean ignorePISItemUpdateFlag) {
        this.ignorePISItemUpdateFlag = ignorePISItemUpdateFlag;
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
