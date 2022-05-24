
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
    "vendorItemCode",
    "shippedQuantity",
    "productID",
    "itemName",
    "packageSizeQuantity",
    "itemCostAmount",
    "orderedQuantity"
})
public class InvoiceLineItem {

    @JsonProperty("vendorItemCode")
    private String vendorItemCode;
    @JsonProperty("shippedQuantity")
    private Integer shippedQuantity;
    @JsonProperty("productID")
    private String productID;
    @JsonProperty("itemName")
    private String itemName;
    @JsonProperty("packageSizeQuantity")
    private Integer packageSizeQuantity;
    @JsonProperty("itemCostAmount")
    private Integer itemCostAmount;
    @JsonProperty("orderedQuantity")
    private Integer orderedQuantity;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("vendorItemCode")
    public String getVendorItemCode() {
        return vendorItemCode;
    }

    @JsonProperty("vendorItemCode")
    public void setVendorItemCode(String vendorItemCode) {
        this.vendorItemCode = vendorItemCode;
    }

    @JsonProperty("shippedQuantity")
    public Integer getShippedQuantity() {
        return shippedQuantity;
    }

    @JsonProperty("shippedQuantity")
    public void setShippedQuantity(Integer shippedQuantity) {
        this.shippedQuantity = shippedQuantity;
    }

    @JsonProperty("productID")
    public String getProductID() {
        return productID;
    }

    @JsonProperty("productID")
    public void setProductID(String productID) {
        this.productID = productID;
    }

    @JsonProperty("itemName")
    public String getItemName() {
        return itemName;
    }

    @JsonProperty("itemName")
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @JsonProperty("packageSizeQuantity")
    public Integer getPackageSizeQuantity() {
        return packageSizeQuantity;
    }

    @JsonProperty("packageSizeQuantity")
    public void setPackageSizeQuantity(Integer packageSizeQuantity) {
        this.packageSizeQuantity = packageSizeQuantity;
    }

    @JsonProperty("itemCostAmount")
    public Integer getItemCostAmount() {
        return itemCostAmount;
    }

    @JsonProperty("itemCostAmount")
    public void setItemCostAmount(Integer itemCostAmount) {
        this.itemCostAmount = itemCostAmount;
    }

    @JsonProperty("orderedQuantity")
    public Integer getOrderedQuantity() {
        return orderedQuantity;
    }

    @JsonProperty("orderedQuantity")
    public void setOrderedQuantity(Integer orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
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
