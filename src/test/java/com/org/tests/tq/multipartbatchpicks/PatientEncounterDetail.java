
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
    "encounterID",
    "roomName",
    "bedID",
    "unitName"
})
public class PatientEncounterDetail {

    @JsonProperty("encounterID")
    private String encounterID;
    @JsonProperty("roomName")
    private String roomName;
    @JsonProperty("bedID")
    private String bedID;
    @JsonProperty("unitName")
    private String unitName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("encounterID")
    public String getEncounterID() {
        return encounterID;
    }

    @JsonProperty("encounterID")
    public void setEncounterID(String encounterID) {
        this.encounterID = encounterID;
    }

    @JsonProperty("roomName")
    public String getRoomName() {
        return roomName;
    }

    @JsonProperty("roomName")
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @JsonProperty("bedID")
    public String getBedID() {
        return bedID;
    }

    @JsonProperty("bedID")
    public void setBedID(String bedID) {
        this.bedID = bedID;
    }

    @JsonProperty("unitName")
    public String getUnitName() {
        return unitName;
    }

    @JsonProperty("unitName")
    public void setUnitName(String unitName) {
        this.unitName = unitName;
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
