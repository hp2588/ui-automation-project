
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
    "customField1Value",
    "customField2Value",
    "customField3Value",
    "customField4Value",
    "customField5Value"
})
public class UserDefine {

    @JsonProperty("customField1Value")
    private String customField1Value;
    @JsonProperty("customField2Value")
    private String customField2Value;
    @JsonProperty("customField3Value")
    private String customField3Value;
    @JsonProperty("customField4Value")
    private String customField4Value;
    @JsonProperty("customField5Value")
    private String customField5Value;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("customField1Value")
    public String getCustomField1Value() {
        return customField1Value;
    }

    @JsonProperty("customField1Value")
    public void setCustomField1Value(String customField1Value) {
        this.customField1Value = customField1Value;
    }

    @JsonProperty("customField2Value")
    public String getCustomField2Value() {
        return customField2Value;
    }

    @JsonProperty("customField2Value")
    public void setCustomField2Value(String customField2Value) {
        this.customField2Value = customField2Value;
    }

    @JsonProperty("customField3Value")
    public String getCustomField3Value() {
        return customField3Value;
    }

    @JsonProperty("customField3Value")
    public void setCustomField3Value(String customField3Value) {
        this.customField3Value = customField3Value;
    }

    @JsonProperty("customField4Value")
    public String getCustomField4Value() {
        return customField4Value;
    }

    @JsonProperty("customField4Value")
    public void setCustomField4Value(String customField4Value) {
        this.customField4Value = customField4Value;
    }

    @JsonProperty("customField5Value")
    public String getCustomField5Value() {
        return customField5Value;
    }

    @JsonProperty("customField5Value")
    public void setCustomField5Value(String customField5Value) {
        this.customField5Value = customField5Value;
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
