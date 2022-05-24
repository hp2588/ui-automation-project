
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
    "itemID",
    "itemName",
    "pickQuantity",
    "pickUnitOfMeasureCode",
    "strengthAmount",
    "strengthUnitOfMeasureCode"
})
public class OrderComponent {

    @JsonProperty("itemID")
    private String itemID;
    @JsonProperty("itemName")
    private String itemName;
    @JsonProperty("pickQuantity")
    private Integer pickQuantity;
    @JsonProperty("pickUnitOfMeasureCode")
    private String pickUnitOfMeasureCode;
    @JsonProperty("strengthAmount")
    private Integer strengthAmount;
    @JsonProperty("strengthUnitOfMeasureCode")
    private String strengthUnitOfMeasureCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("itemID")
    public String getItemID() {
        return itemID;
    }

    @JsonProperty("itemID")
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    @JsonProperty("itemName")
    public String getItemName() {
        return itemName;
    }

    @JsonProperty("itemName")
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @JsonProperty("pickQuantity")
    public Integer getPickQuantity() {
        return pickQuantity;
    }

    @JsonProperty("pickQuantity")
    public void setPickQuantity(Integer pickQuantity) {
        this.pickQuantity = pickQuantity;
    }

    @JsonProperty("pickUnitOfMeasureCode")
    public String getPickUnitOfMeasureCode() {
        return pickUnitOfMeasureCode;
    }

    @JsonProperty("pickUnitOfMeasureCode")
    public void setPickUnitOfMeasureCode(String pickUnitOfMeasureCode) {
        this.pickUnitOfMeasureCode = pickUnitOfMeasureCode;
    }

    @JsonProperty("strengthAmount")
    public Integer getStrengthAmount() {
        return strengthAmount;
    }

    @JsonProperty("strengthAmount")
    public void setStrengthAmount(Integer strengthAmount) {
        this.strengthAmount = strengthAmount;
    }

    @JsonProperty("strengthUnitOfMeasureCode")
    public String getStrengthUnitOfMeasureCode() {
        return strengthUnitOfMeasureCode;
    }

    @JsonProperty("strengthUnitOfMeasureCode")
    public void setStrengthUnitOfMeasureCode(String strengthUnitOfMeasureCode) {
        this.strengthUnitOfMeasureCode = strengthUnitOfMeasureCode;
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
