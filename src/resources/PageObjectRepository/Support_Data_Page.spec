Page Title: Pyxis Logistics

#Object Definitions
======================================================================================================================================
loader:xpath:.//div[@id='laoder3']
mini_loader:xpath://div[@class='loader-ring']
loading:xpath:.//div[contains(text(), 'Loading')]
list_recordNames:xpath:.//div[@class='rt-tbody']//div[@class='rt-td'][1]
record_name_list:xpath:.//button[@name='edit']
list_dosageForms:xpath:.//div[@class='rt-tbody']//div[@class='rt-td'][${num}]
firstData_distributor:xpath:.//div[@class='rt-tbody']/div/div/div[${num}]
list_dosageForm_Sort:xpath:.//div[@class='rt-tbody']//div[@class='rt-td columnRightAlign'][1]
list_dosageForm_Status:xpath:.//div[@class='rt-tbody']//div[contains(@class,'rt-td')][4]/div/div
list_edit_label:xpath:.//div[@class='rt-tbody']/div/div/div[4]
list_edit_carousel:xpath:.//div[@class='rt-tbody']/div/div/div[6]
list_carouselStatus:xpath:.//div[@class='rt-tbody']//div[@class='rt-td'][5]
list_glStatus:xpath:.//div[@class='rt-tbody']/div/div/div[3]
list_editAction:xpath:.//div[@class='rt-tbody']//div[@class='rt-td'][6]/button
sort_icon:xpath:.//div[@class='rt-resizable-header-content'][contains(text(), '${column}')]
text_column:xpath:.//div[@class = 'rt-tr-group']/div/div/div/button[contains(text(), 'Edit')]/../../preceding-sibling::div[${column}]
text_column_gl:xpath:.//div[@class = 'rt-tr-group']/div/div/button[contains(text(), 'Edit')]/../preceding-sibling::div[${column}]
search_box:xpath:.//input[@name='${searchFilter}']
element_by_id_and_name:xpath:.//*[@id='${id}' and @name='%{name}']
account_no_input:xpath: (.//input[@name='${searchFilter}'])[1]
link_supportData:xpath:.//span[contains(text(),'Support Data')]
link_supportDataMenuOptions:xpath:.//span[contains(text(),'${column}')]
popup_text:xpath:.//div[@class='modal-body']//h3
list_item_on_popup:xpath:.//div[contains(@class, 'modal-body')]//li[contains(text(), '${text}')]
item_edit_screen:xpath://a[@class='active']
page_title:xpath://h3/b
page_title_uom:xpath://h3/div
label_hold_reason_UOM:xpath://h3/div
popup_edit_hold:xpath:.//div[@class='modal-body']//h3
tab_link:xpath:.//div[@class='tabs']//a[contains(text(),'${tabName}')]
inp_field_text:xpath:.//label[@for='${fieldName}']/following-sibling::div/input
textarea_hold_reason_description:xpath:.//label[@for='${fieldName}']/following-sibling::div/textarea
popup_message:xpath://div[@class='toast-body']
action_button:xpath:.//button[contains(@id,'${action}')]
disabled_toggle:xpath://label[@for='${value}'][@class='disabled']
action_button_save:xpath:.//button[@id='save' and @name='save'] 
req_lot_checkbox:xpath://label[@for='${fieldName}']
dropdowns_externalSystem:xpath:.//select[@name='${fieldName}']
added_hold_reason_name:xpath:.//button[contains(text(),'${holdReasonName}')]
added_hold_reason_status:xpath:.//button[contains(text(),'${holdReasonName}')]/../../../div/div/div[contains(text(),'%{status}')]
field_status:xpath:.//div[contains(text(),'${holdReasonName}')]/following-sibling::div[2][text()='%{status}']
icon_mandatory:xpath:.//label[@for='${fieldName}']/span
action_toggle_button:xpath:.//input[@name='${toggle}']
action_toggle_button_1:xpath:.//label[@for='${toggle}']
div_alert:xpath:.//div[@role='alert']
btn_add:xpath:.//button[@id='add']
distributor_Account_no:xpath:(.//div[@class='rt-tbody']//div[@class='form-group ']/input)[1]
text_error_msg_duplicate:xpath:.//*[text()='${msg}']
text_error_msg:xpath:.//span[@class='help-block help-block-fixed']
text_error_msg_new:xpath:.//div[@role='alert']/ul/li
link_edit:xpath:.//div[contains(text(),'${fieldName}')]/../../..//button[@id='edit']
edit_uom:xpath:.//div[contains(text(),'${fieldName}')]/../../../div[7]/div/button
#edit_therapeutic:xpath:.//div[contains(text(),'${code}')]/../../../div[5]/div/span/span/button[@id='Edit']
edit_therapeutic:xpath://button[contains(text(),'${code}')]
link_edit_custom_label:xpath:.//button[contains(text(),'${fieldName}')]
link_edit_item:xpath:.//button[contains(text(),'${holdReason}')]
record_chkbox_label:xpath:.//button[contains(text(),'${recordName}')]/../../..//label
input_with_id:xpath:.//input[@id='${id}']
cross_button:xpath:.//span[@class="icon-close-bold-sm form-control-clear modal-cross-icon"]
edit_link_item_locations:xpath:.//div[contains(text(),'${holdReason}')]/following::div[2]/button[@id='edit']
cross_icon:xpath:.//span[@class='icon-close-bold-sm form-control-clear']
#required_text:xpath:.//p/span
required_text:xpath:.//div/p[contains(text(),'${text}')]
first_col_data:xpath:.//div[@class='rt-tr-group']/child::div/div[${num}]
first_inactive_data:xpath:(.//div[@class='rt-tbody']//div[contains(@class,'rt-td')][4]/div/div[text()='Inactive'])[1]
first_inactive_external_system:xpath:(.//div[@class='rt-tr-group']/child::div/div[4]/div[contains(text(),'Inactive')]/../../div/div)[1]
textBox_search:xpath:.//input[@name='${searchFilter}']
radio_button_disabled_class:xpath://div[contains(@class,'${className}')]
clear_search_box:xpath:.//div[@class='mr-16']
clear_search_box_parent:xpath://span[@class='icon-close-bold-sm form-control-clear']/..
searched_data:xpath:.//div[contains(text(),'${searchTerm}')]
text_no_data:xpath:.//div[@class='rt-noData']
text_no_data_new:xpath:.//div[contains(@class,'messageBeforeSearch')]/h5
label_hold_reason:xpath://div/h3
blank_field:xpath:.//div[@class='form-group has-error']/child::span[text()='${validationMsg}']
link_breadcrumb:xpath:.//a[contains(text(),'${val}')]
list_holdreasonStatus:xpath:.//div[@class='rt-tr-group']//div[contains(@class,'rt-td')][3]
blank_field:xpath:.//span[contains(text(),'${validationMsg}')]
message_field:xpath:.//input[@id='${model}']/following-sibling::span
textarea_message_field:xpath:.//textarea[@name='${model}']/following-sibling::span
textbox_carouselModel:xpath:.//input[@id='carouselModelId']
textbox_carouselName:xpath:.//input[@id='carouselName']
#link_edit:xpath:.//div[@class='rt-table']//*[text()='Name']/following::div[@class='rt-tr-group']//div[contains(text(),'${field}')]/following-sibling::div/button[contains(text(),'Edit')]
link_edit_table:xpath:.//div[contains(text(),'${item}')]/../../following-sibling::div//button[text()='Edit']
popup_add:xpath:.//div[@class='modal-body']//h3
error_message_carousel:xpath:.//li[contains(text(),'${msg}')]
dropdowns_dosage_pis:xpath://select[@name='${type}']
header_dosageForms:xpath:.//div[contains(@class, 'rt-resizable-header-content') and text() = '${header}']
dropdown_dosageForms:xpath:.//select[@name = 'externalSystems']
dropdown_dosageForms_first:xpath:.//select[@name = 'externalSystems']/option
edit_link_dosageform:xpath:.//button[@class='${editlink}']
input_field_dosage_code:xpath:.//input[@name= 'dosageFormCode']
added_isa:xpath://span[contains(text(),'${fieldName}')]
error_holdreason_msg:xpath:.//div[@class='alert alert-warning alert-dismissible fade show flex-row align-items-baseline']
error_manufacturer_msg:xpath:.//div[@class='alert alert-warning alert-dismissible fade show flex-row align-items-baseline']/ul/li
edit_link_facility:xpath:.//button[@class='btn btn-link mr-20 p-10']
dropdown_by_index:xpath:.//label[@for='${fieldName}']/following-sibling::div/select
list_wastereasonStatus:xpath:.//div[@class='rt-tbody']//div[@class='rt-td table-tooltip-overflow']//div//div[1]
edit_text_requiredfield:xpath:.//p[@class='asterik-sign asterik-sign-content mb-24']
filter_btn_item:xpath:.//button[@class='funnel badfil mr-24']
filter_items_heading:xpath:.//h5[@class='modal-title']
filter_dropdown:xpath:.//select[@name='${field}']
attribute_dropdown_value:xpath:.//select[@name='field']/option[contains(text(),'${field}')]
applyfilter_btn:xpath:.//button[@id='save']
dropdown_itemManagement:xpath://input[@placeholder='01-ES-UI-DM-1753-CH1']
text_requiredfield:xpath:.//p[@class='mt-0 mb-24 required']
text_inputFieldDistributor:xpath:.//input[@id='${field}']
link_contactDistributor:xpath:.//a[contains(text(), '${field}')]
edit_item:xpath:(.//div[@class='rt-tbody']//div[@class='rt-td'][${num}]//button)[1]
#select_element_from_list:xpath://div[@class='rt-td'][contains(text(),'${fieldName}')]
select_element_from_list:xpath://div[@class='rt-td'][${num}]
uom_sort_order_field:xpath:.//label[@for='${fieldName}']/following-sibling::div/input
text_editDistributor:xpath:.//label[contains(text(), '${text}')]
edit_links:xpath:.//div[@class='rt-tbody']//div[@class='rt-td'][4]/div/button[text()='${action}']
duplicate_link_of_added_custom_label:xpath://button[contains(text(),'${fieldName}')]/../../../div[6]/div/button[contains(text(),'%{action}')]
record_action_btn:xpath:.//button[contains(text(),'${record}')]/../../..//button[contains(text(),'%{action}')]
label_add_new_item:xpath://p[contains(@class,'fs-24')]
loading_label:xpath://div[contains(text(),'Loading...')]
label_add_new_item:xpath:.//div[@class='d-flex mb-24 mt-24 justify-content-between']/h3
action_button1:xpath://button[contains(text(),'${fieldName}')]
Add_new_order_actions:xpath:.//a[contains(text(),'${fieldName}')]
order_new_item_label:xpath:.//p[@class='title mb-24']/h3
search_po_item:xpath://input[@placeholder='${fieldName}']
po_search_item_result:xpath://div[contains(text(),'${fieldName}')]
po_item_by_index:xpath:.//div[@class='rt-tr-group']//div[@class='rt-tr -odd']//div['${rownum}']
item_to_order_quantity:xpath:.//input[@name='${fieldName}']
order_save_close:xpath:.//button[contains(text(),'${fieldName}')]
open_po_card_manual:xpath:.//div[contains(@class, 'manual-card')]
open_po_card_electronic:xpath:.//div[contains(@class, 'electronic-card')]
purchase_order_status:xpath:.//div[@class='d-flex justify-content-between left-section fs-14 lh-16']/div[3]/div
dashboard_link:xpath:.//li[@class='breadcrumb-item']/a[contains(text(),'${fieldName}')]
list_dispenseUnit_Status:xpath:.//div[@class='rt-tbody']//div[contains(@class,'rt-td')][4]/div/div
list_dispenseUnit_Sort:xpath:.//div[@class='rt-tbody']//div[contains(@class,'rt-td')][3]/div/div
dropdowns_printers:xpath:.//select[@name='${fieldName}']
dropdowns_gl_account_pis:xpath://select[@name='${type}']
save_po_number:xpath://div[contains(text(),'${fieldName}')]
pending_receive_card:xpath://div[contains(text(),'Pending Receive')]/following-sibling::div/div//div[contains(text(),'${fieldName}')]
received_card:xpath://div[contains(text(),'Received')]/following-sibling::div/div//div[contains(text(),'${fieldName}')]
invoice_item_checkbox:xpath:.//div[contains(text(),'${fieldName}')]/parent::div/preceding-sibling::div/div/label
item_cost:xpath:.//input[@id='${fieldName}']
ro_po_orders:xpath:.//input[contains(@id,'purchaseOrderDescription')]
proceed_link:xpath:.//a[@id='proceed-link']
#link_edit_item:xpath:.//div[contains(text(),'${item}')]/following::button[@id='edit']
link_edit_item:xpath:.//div[contains(text(),'${holdReason}')]/../../../div[8]/div/button
dropdown_reporting:xpath:.//div[@id='facilitySelectionDiv']
frame_reporting:xpath:.//iframe[@id='${id}']
error_mesg_report:xpath:.//li[text()='${text}']
delete_popup_message_therapeutic_class:xpath:.//div[@class='delete-class alert alert-info flex-row tabfocusable']/i
crossIcon_Carousel:xpath:.//span[@class='icon-close-bold-sm form-control-clear modal-cross-icon']
labelName_Carousel:xpath:.//label[contains(text(), '${item}')]
hold_reason_name:xpath:(//div[@class='item-description truncate'])[1]
text_medicationCode:xpath:.//div[@class='rt-tr-group']/div/div[2]
deletetext_medicationCode:xpath:.//ul[@class='classes-delete-therapeutic ml-20']/li
link_deleteMedicationClass:xpath:.//a[contains(text(), 'Delete Selected')]	
checkbox_MedicationClassSelectAll:xpath:.//label[@for='checkboxALL_CHECKBOX_KEY']
checkbox_item_tab:xpath:.//label[@for='${item}']
label_with_for:xpath:.//label[@for='${forValue}']
checkbox_item_tab1:xpath:.//div[@class='form-check']/following::label[${num}]
edit_corresponding_to_checkbox:xpath:(.//div[@class='form-check']/following::label[1]/../../../div[@class='rt-td'][8]/div/button)[${num}]
list_labelTag:xpath:.//div[@class='rt-tbody']/div[@class='rt-tr-group']/div/div[2]
text_descriptionMedication:xpath:.//textarea[@id='medicationClassDescription']
text_descriptionLabelTag:xpath:.//textarea[@id='labelTagDescription']
text_barcodeItem:xpath:.//div[@class='rt-tr-group']
button_routingRule:xpath:.//button[@id='${btnName}']
text_barcodePopUp:xpath:.//div[@class='item-description']
button_viewRoutingRule:xpath:.//button[contains(text(), 'View Rules')]
button_editRoutingRule:xpath:.//div[contains(text(),'${Routingrule}')]/../following-sibling::div/div/button[@name='edit']
button_editDosageForm:xpath:.//div[contains(text(),'${DosageForm}')]/following-sibling::div/div/button[@name='edit']
new_manufacturer_status:(.//div[@class='rt-tbody']//div[@class='rt-td'])[2]
dispenseunit_alert_message:xpath:.//div[@role='${fieldName}']/ul/li
link_edit_new:xpath://button[contains(text(),'${item}')]
#/../../following-sibling::div/div/button[@id='edit']
sort_uanble_to_order:xpath:.//div[@class='rt-tbody']//div[@class='rt-td columnRightAlign pr-20 table-tooltip-overflow'][${num}]
disabled_distributorfield:xpath:.//div[@class='d-flex disabled']
header_action_barcode:xpath:.//div[contains(@class, 'rt-resizable-header-content') and text() = 'Linked By']/../following-sibling::div/div[1]
first_inactiveValueCarousel:xpath:.//div[@class='rt-tr-group']/div/div[5]/span/label[contains(text(), 'Inactive')]/../../preceding-sibling::div[4]
default_dropdown_reports:xpath:.//span[@class='selected-value']
link_labelTag:xpath:.//a[@id='labeltags_trigger']
dropdown_labelTag:xpath:.//div[@class='dropdown-content']/ul/div/div/li
cross_button:xpath:.//span[@class="icon-close-bold-sm form-control-clear"]
icon_mandatory_GL:xpath://label[@for='${fieldName}']/../label[@class='asterik-sign']
updated_DosageCode:xpath://button[contains(text(),'${DosageCode}')]
link_edit_table_waste_reason:xpath://div/button[text()='${fieldName}']
list_manufacturerStatus:xpath:.//div[@class='rt-tbody']//div[@class='rt-td table-tooltip-overflow']//div//div[1]
package_size_PO:xpath:.//div[text()= '1' and @class='item-description truncate']
package_size_IS:xpath:.//div[text()= '5' and @class='item-description truncate']
package_size_RPO:xpath:.//div[@class='rt-td columnRightAlign']/span[text()= '1']
list_manufacturerStatus:xpath:.//div[@class='rt-tbody']//div[@class='rt-td table-tooltip-overflow'][2]
link_edit_dispense_unit:xpath:.//div[contains(text(),'${fieldName}')]/../../..//button[@id='edit']
idm_dropdown:xpath:.//*[@for='tenant']
edit_button_distributor:xpath:.//button[contains(text(), '${action}')]
tq_closeButton:xpath:.//button[@class='close']
view_routingRules:xpath:.//div[contains(text(),'${action}')]
===============================================================================================================================