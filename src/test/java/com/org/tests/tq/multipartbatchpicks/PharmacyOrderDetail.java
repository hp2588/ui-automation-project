
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
    "prescriptionID",
    "orderEffectiveDateTime",
    "startDateTime",
    "endDateTime",
    "orderStrengthAmount",
    "orderStrengthUnitOfMeasureCode",
    "repeatPatternText",
    "minimumGiveAmount",
    "maximumGiveAmount",
    "giveStrengthAmount",
    "giveStrengthUnitOfMeasureCode",
    "giveDosageFormCode",
    "orderInstructionText",
    "orderRouteCode",
    "orderDoseAmount",
    "orderTotalDoseAmount",
    "prnReasonText",
    "scheduleText",
    "medExpirationDateTime",
    "copeOrderID",
    "orderDurationAmount",
    "concentrationVolumeAmount",
    "wasteAmount"
})
public class PharmacyOrderDetail {

    @JsonProperty("prescriptionID")
    private String prescriptionID;
    @JsonProperty("orderEffectiveDateTime")
    private String orderEffectiveDateTime;
    @JsonProperty("startDateTime")
    private String startDateTime;
    @JsonProperty("endDateTime")
    private String endDateTime;
    @JsonProperty("orderStrengthAmount")
    private Integer orderStrengthAmount;
    @JsonProperty("orderStrengthUnitOfMeasureCode")
    private String orderStrengthUnitOfMeasureCode;
    @JsonProperty("repeatPatternText")
    private String repeatPatternText;
    @JsonProperty("minimumGiveAmount")
    private Integer minimumGiveAmount;
    @JsonProperty("maximumGiveAmount")
    private Integer maximumGiveAmount;
    @JsonProperty("giveStrengthAmount")
    private Integer giveStrengthAmount;
    @JsonProperty("giveStrengthUnitOfMeasureCode")
    private String giveStrengthUnitOfMeasureCode;
    @JsonProperty("giveDosageFormCode")
    private String giveDosageFormCode;
    @JsonProperty("orderInstructionText")
    private String orderInstructionText;
    @JsonProperty("orderRouteCode")
    private String orderRouteCode;
    @JsonProperty("orderDoseAmount")
    private Integer orderDoseAmount;
    @JsonProperty("orderTotalDoseAmount")
    private Integer orderTotalDoseAmount;
    @JsonProperty("prnReasonText")
    private String prnReasonText;
    @JsonProperty("scheduleText")
    private String scheduleText;
    @JsonProperty("medExpirationDateTime")
    private String medExpirationDateTime;
    @JsonProperty("copeOrderID")
    private String copeOrderID;
    @JsonProperty("orderDurationAmount")
    private Integer orderDurationAmount;
    @JsonProperty("concentrationVolumeAmount")
    private Integer concentrationVolumeAmount;
    @JsonProperty("wasteAmount")
    private Integer wasteAmount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("prescriptionID")
    public String getPrescriptionID() {
        return prescriptionID;
    }

    @JsonProperty("prescriptionID")
    public void setPrescriptionID(String prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    @JsonProperty("orderEffectiveDateTime")
    public String getOrderEffectiveDateTime() {
        return orderEffectiveDateTime;
    }

    @JsonProperty("orderEffectiveDateTime")
    public void setOrderEffectiveDateTime(String orderEffectiveDateTime) {
        this.orderEffectiveDateTime = orderEffectiveDateTime;
    }

    @JsonProperty("startDateTime")
    public String getStartDateTime() {
        return startDateTime;
    }

    @JsonProperty("startDateTime")
    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    @JsonProperty("endDateTime")
    public String getEndDateTime() {
        return endDateTime;
    }

    @JsonProperty("endDateTime")
    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    @JsonProperty("orderStrengthAmount")
    public Integer getOrderStrengthAmount() {
        return orderStrengthAmount;
    }

    @JsonProperty("orderStrengthAmount")
    public void setOrderStrengthAmount(Integer orderStrengthAmount) {
        this.orderStrengthAmount = orderStrengthAmount;
    }

    @JsonProperty("orderStrengthUnitOfMeasureCode")
    public String getOrderStrengthUnitOfMeasureCode() {
        return orderStrengthUnitOfMeasureCode;
    }

    @JsonProperty("orderStrengthUnitOfMeasureCode")
    public void setOrderStrengthUnitOfMeasureCode(String orderStrengthUnitOfMeasureCode) {
        this.orderStrengthUnitOfMeasureCode = orderStrengthUnitOfMeasureCode;
    }

    @JsonProperty("repeatPatternText")
    public String getRepeatPatternText() {
        return repeatPatternText;
    }

    @JsonProperty("repeatPatternText")
    public void setRepeatPatternText(String repeatPatternText) {
        this.repeatPatternText = repeatPatternText;
    }

    @JsonProperty("minimumGiveAmount")
    public Integer getMinimumGiveAmount() {
        return minimumGiveAmount;
    }

    @JsonProperty("minimumGiveAmount")
    public void setMinimumGiveAmount(Integer minimumGiveAmount) {
        this.minimumGiveAmount = minimumGiveAmount;
    }

    @JsonProperty("maximumGiveAmount")
    public Integer getMaximumGiveAmount() {
        return maximumGiveAmount;
    }

    @JsonProperty("maximumGiveAmount")
    public void setMaximumGiveAmount(Integer maximumGiveAmount) {
        this.maximumGiveAmount = maximumGiveAmount;
    }

    @JsonProperty("giveStrengthAmount")
    public Integer getGiveStrengthAmount() {
        return giveStrengthAmount;
    }

    @JsonProperty("giveStrengthAmount")
    public void setGiveStrengthAmount(Integer giveStrengthAmount) {
        this.giveStrengthAmount = giveStrengthAmount;
    }

    @JsonProperty("giveStrengthUnitOfMeasureCode")
    public String getGiveStrengthUnitOfMeasureCode() {
        return giveStrengthUnitOfMeasureCode;
    }

    @JsonProperty("giveStrengthUnitOfMeasureCode")
    public void setGiveStrengthUnitOfMeasureCode(String giveStrengthUnitOfMeasureCode) {
        this.giveStrengthUnitOfMeasureCode = giveStrengthUnitOfMeasureCode;
    }

    @JsonProperty("giveDosageFormCode")
    public String getGiveDosageFormCode() {
        return giveDosageFormCode;
    }

    @JsonProperty("giveDosageFormCode")
    public void setGiveDosageFormCode(String giveDosageFormCode) {
        this.giveDosageFormCode = giveDosageFormCode;
    }

    @JsonProperty("orderInstructionText")
    public String getOrderInstructionText() {
        return orderInstructionText;
    }

    @JsonProperty("orderInstructionText")
    public void setOrderInstructionText(String orderInstructionText) {
        this.orderInstructionText = orderInstructionText;
    }

    @JsonProperty("orderRouteCode")
    public String getOrderRouteCode() {
        return orderRouteCode;
    }

    @JsonProperty("orderRouteCode")
    public void setOrderRouteCode(String orderRouteCode) {
        this.orderRouteCode = orderRouteCode;
    }

    @JsonProperty("orderDoseAmount")
    public Integer getOrderDoseAmount() {
        return orderDoseAmount;
    }

    @JsonProperty("orderDoseAmount")
    public void setOrderDoseAmount(Integer orderDoseAmount) {
        this.orderDoseAmount = orderDoseAmount;
    }

    @JsonProperty("orderTotalDoseAmount")
    public Integer getOrderTotalDoseAmount() {
        return orderTotalDoseAmount;
    }

    @JsonProperty("orderTotalDoseAmount")
    public void setOrderTotalDoseAmount(Integer orderTotalDoseAmount) {
        this.orderTotalDoseAmount = orderTotalDoseAmount;
    }

    @JsonProperty("prnReasonText")
    public String getPrnReasonText() {
        return prnReasonText;
    }

    @JsonProperty("prnReasonText")
    public void setPrnReasonText(String prnReasonText) {
        this.prnReasonText = prnReasonText;
    }

    @JsonProperty("scheduleText")
    public String getScheduleText() {
        return scheduleText;
    }

    @JsonProperty("scheduleText")
    public void setScheduleText(String scheduleText) {
        this.scheduleText = scheduleText;
    }

    @JsonProperty("medExpirationDateTime")
    public String getMedExpirationDateTime() {
        return medExpirationDateTime;
    }

    @JsonProperty("medExpirationDateTime")
    public void setMedExpirationDateTime(String medExpirationDateTime) {
        this.medExpirationDateTime = medExpirationDateTime;
    }

    @JsonProperty("copeOrderID")
    public String getCopeOrderID() {
        return copeOrderID;
    }

    @JsonProperty("copeOrderID")
    public void setCopeOrderID(String copeOrderID) {
        this.copeOrderID = copeOrderID;
    }

    @JsonProperty("orderDurationAmount")
    public Integer getOrderDurationAmount() {
        return orderDurationAmount;
    }

    @JsonProperty("orderDurationAmount")
    public void setOrderDurationAmount(Integer orderDurationAmount) {
        this.orderDurationAmount = orderDurationAmount;
    }

    @JsonProperty("concentrationVolumeAmount")
    public Integer getConcentrationVolumeAmount() {
        return concentrationVolumeAmount;
    }

    @JsonProperty("concentrationVolumeAmount")
    public void setConcentrationVolumeAmount(Integer concentrationVolumeAmount) {
        this.concentrationVolumeAmount = concentrationVolumeAmount;
    }

    @JsonProperty("wasteAmount")
    public Integer getWasteAmount() {
        return wasteAmount;
    }

    @JsonProperty("wasteAmount")
    public void setWasteAmount(Integer wasteAmount) {
        this.wasteAmount = wasteAmount;
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
