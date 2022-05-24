
package com.org.tests.manualInvoiceTagging;

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
    "facilityCode",
    "invoiceDateTime",
    "purchaseOrderID",
    "invoiceID",
    "purchaseOrderDate",
    "invoiceTypeCode",
    "vendorCode",
    "invoiceLineItems"
})
public class Data {

    @JsonProperty("facilityCode")
    private String facilityCode;
    @JsonProperty("invoiceDateTime")
    private String invoiceDateTime;
    @JsonProperty("purchaseOrderID")
    private String purchaseOrderID;
    @JsonProperty("invoiceID")
    private String invoiceID;
    @JsonProperty("purchaseOrderDate")
    private String purchaseOrderDate;
    @JsonProperty("invoiceTypeCode")
    private String invoiceTypeCode;
    @JsonProperty("vendorCode")
    private String vendorCode;
    @JsonProperty("invoiceLineItems")
    private List<InvoiceLineItem> invoiceLineItems = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("facilityCode")
    public String getFacilityCode() {
        return facilityCode;
    }

    @JsonProperty("facilityCode")
    public void setFacilityCode(String facilityCode) {
        this.facilityCode = facilityCode;
    }

    @JsonProperty("invoiceDateTime")
    public String getInvoiceDateTime() {
        return invoiceDateTime;
    }

    @JsonProperty("invoiceDateTime")
    public void setInvoiceDateTime(String invoiceDateTime) {
        this.invoiceDateTime = invoiceDateTime;
    }

    @JsonProperty("purchaseOrderID")
    public String getPurchaseOrderID() {
        return purchaseOrderID;
    }

    @JsonProperty("purchaseOrderID")
    public void setPurchaseOrderID(String purchaseOrderID) {
        this.purchaseOrderID = purchaseOrderID;
    }

    @JsonProperty("invoiceID")
    public String getInvoiceID() {
        return invoiceID;
    }

    @JsonProperty("invoiceID")
    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    @JsonProperty("purchaseOrderDate")
    public String getPurchaseOrderDate() {
        return purchaseOrderDate;
    }

    @JsonProperty("purchaseOrderDate")
    public void setPurchaseOrderDate(String purchaseOrderDate) {
        this.purchaseOrderDate = purchaseOrderDate;
    }

    @JsonProperty("invoiceTypeCode")
    public String getInvoiceTypeCode() {
        return invoiceTypeCode;
    }

    @JsonProperty("invoiceTypeCode")
    public void setInvoiceTypeCode(String invoiceTypeCode) {
        this.invoiceTypeCode = invoiceTypeCode;
    }

    @JsonProperty("vendorCode")
    public String getVendorCode() {
        return vendorCode;
    }

    @JsonProperty("vendorCode")
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    @JsonProperty("invoiceLineItems")
    public List<InvoiceLineItem> getInvoiceLineItems() {
        return invoiceLineItems;
    }

    @JsonProperty("invoiceLineItems")
    public void setInvoiceLineItems(List<InvoiceLineItem> invoiceLineItems) {
        this.invoiceLineItems = invoiceLineItems;
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
