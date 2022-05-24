package com.org.tests.transactionqueue;

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
@JsonPropertyOrder({ "data", "header" })
public class BatchPickAPIModel {

	@JsonProperty("data")
	private Data data;
	@JsonProperty("header")
	private Header header;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("data")
	public Data getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(Data data) {
		this.data = data;
	}

	@JsonProperty("header")
	public Header getHeader() {
		return header;
	}

	@JsonProperty("header")
	public void setHeader(Header header) {
		this.header = header;
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "receivedDateTime", "facilityCode", "externalSystemName", "transactionPriorityCode",
		"deliveryDestinationName", "pharmacyOrderDetail", "patientEncounterDetail", "patientInformation",
		"orderingPersonInformation", "userDefine", "orderComponents" })
class Data {

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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "requestID", "timeStamp", "version", "tenantKey", "facilityCode", "externalSystemName",
		"messageType" })
class Header {

	@JsonProperty("requestID")
	private String requestID;
	@JsonProperty("timeStamp")
	private String timeStamp;
	@JsonProperty("version")
	private String version;
	@JsonProperty("tenantKey")
	private String tenantKey;
	@JsonProperty("facilityCode")
	private String facilityCode;
	@JsonProperty("externalSystemName")
	private String externalSystemName;
	@JsonProperty("messageType")
	private String messageType;
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

	@JsonProperty("messageType")
	public String getMessageType() {
		return messageType;
	}

	@JsonProperty("messageType")
	public void setMessageType(String messageType) {
		this.messageType = messageType;
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "itemID", "itemName", "pickQuantity", "pickUnitOfMeasureCode", "strengthAmount",
		"strengthUnitOfMeasureCode" })
class OrderComponent {

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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "verifiedByOrderingPersonFirstName", "verifiedByOrderingPersonLastName",
		"orderPlacedByOrderingPersonFirstName", "orderPlacedByOrderingPersonLastName",
		"orderReviewedByOrderingPersonName" })
class OrderingPersonInformation {

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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "encounterID", "roomName", "bedID", "unitName" })
class PatientEncounterDetail {

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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "patientID", "firstName", "lastName", "middleName", "nameSuffixText", "dateOfBirthText",
		"accountID", "allegeriesText" })

class PatientInformation {

	@JsonProperty("patientID")
	private String patientID;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("nameSuffixText")
	private String nameSuffixText;
	@JsonProperty("dateOfBirthText")
	private String dateOfBirthText;
	@JsonProperty("accountID")
	private String accountID;
	@JsonProperty("allegeriesText")
	private String allegeriesText;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("patientID")
	public String getPatientID() {
		return patientID;
	}

	@JsonProperty("patientID")
	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("middleName")
	public String getMiddleName() {
		return middleName;
	}

	@JsonProperty("middleName")
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@JsonProperty("nameSuffixText")
	public String getNameSuffixText() {
		return nameSuffixText;
	}

	@JsonProperty("nameSuffixText")
	public void setNameSuffixText(String nameSuffixText) {
		this.nameSuffixText = nameSuffixText;
	}

	@JsonProperty("dateOfBirthText")
	public String getDateOfBirthText() {
		return dateOfBirthText;
	}

	@JsonProperty("dateOfBirthText")
	public void setDateOfBirthText(String dateOfBirthText) {
		this.dateOfBirthText = dateOfBirthText;
	}

	@JsonProperty("accountID")
	public String getAccountID() {
		return accountID;
	}

	@JsonProperty("accountID")
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	@JsonProperty("allegeriesText")
	public String getAllegeriesText() {
		return allegeriesText;
	}

	@JsonProperty("allegeriesText")
	public void setAllegeriesText(String allegeriesText) {
		this.allegeriesText = allegeriesText;
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "prescriptionID", "orderEffectiveDateTime", "startDateTime", "endDateTime", "orderStrengthAmount",
		"orderStrengthUnitOfMeasureCode", "repeatPatternText", "minimumGiveAmount", "maximumGiveAmount",
		"giveStrengthAmount", "giveStrengthUnitOfMeasureCode", "giveDosageFormCode", "orderInstructionText",
		"orderRouteCode", "orderDoseAmount", "orderTotalDoseAmount", "prnReasonText", "scheduleText",
		"medExpirationDateTime", "copeOrderID", "orderDurationAmount", "concentrationVolumeAmount", "wasteAmount" })

class PharmacyOrderDetail {

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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "customField1Value", "customField2Value", "customField3Value", "customField4Value",
		"customField5Value" })

class UserDefine {

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
