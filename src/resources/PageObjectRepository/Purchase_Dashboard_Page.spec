Page Title: Pyxis Logistics
		
#Object Definitions		
======================================================================================================================================
loader:xpath:.//div[@id='laoder3']
mini_loader:xpath://div[@class='loader-ring']
action_button1:xpath://button[contains(text(),'${fieldName}')]
save_po_number:xpath://div[contains(text(),'${fieldName}')]
pending_receive_card:xpath://div[contains(text(),'Pending Receive')]/following-sibling::div/div//div[contains(text(),'${fieldName}')]
invoice_item_checkbox:xpath:.//div[contains(text(),'${fieldname}')]/../../..//label
item_cost:xpath:.//input[@id='${fieldName}']
Add_new_order_actions:xpath:.//a[contains(text(),'${fieldName}')]
order_new_item_label:xpath:.//p[@class='title mb-24']/h3
search_po_item:xpath://input[@placeholder='${fieldName}']
po_search_item_result:xpath://div[contains(text(),'${fieldName}')]
click_searched_item:xpath:.//div[@class='ReactTable']//div//div[contains(text(),'${fieldName}')]
po_item_by_index:xpath:.//div[@class='rt-tr-group']//div[@class='rt-tr -odd']//div['${rownum}']
item_to_order_quantity:xpath:.//input[@id='${fieldName}']
enter_order_quantity:xpath:.//div[@class='ReactTable']//div//div[contains(text(),'${fieldName}')]//parent::div/parent::div//following-sibling::div//input[@name='toOrderQuantity']
order_save_close:xpath:.//button[contains(text(),'${fieldName}')]
po_card_new_state:xpath:.//div[text()='New']//following-sibling::div//div//div[text()='${fieldName}']
open_po_card_electronic:xpath:.//div[contains(@class, 'electronic-card')]
purchase_order_status:xpath:.//div[@class='d-flex justify-content-between left-section fs-14 lh-16']/div[3]/div
dashboard_link:xpath:.//li[@class='breadcrumb-item']/a[contains(text(),'${fieldName}')]
dashboard_state:xpath://div[contains(text(),'${fieldName}')]
dashboard_new_state_card:xpath:.//div[contains(text(),'New')]/following-sibling::div//div[contains(text(),'${fieldName}')]
dashboard_card_type:xpath:.//div[contains(text(),'New')]/following-sibling::div//div[contains(text(),'${fieldName}')]//following-sibling::div
dashboard_item_isa_count:xpath:.//div[contains(text(),'New')]/following-sibling::div//div[contains(text(),'${fieldName}')]/parent::div/parent::div/following-sibling::div
dist_mapped_to_facility:xpath:.//div[contains(text(),'${fieldName}')]
dashboard_distributor_card_name:xpath:.//div[contains(text(),'${fieldName}')]/following-sibling::div//div[@class='tooltip-text']/div[contains(@class,'robotoBold')]
dashboard_search_input_box:xpath:.//input[@id='${fieldName}']
dashboard_group_by_dropdown:xpath:.//select[@name='${fieldName}']
order_color_code_new_state:xpath:.//div[contains(text(),'New')]/following-sibling::div//div[@class='tooltip-text']//div[contains(text(),'${fieldName}')]/following-sibling::div
auto_non_controlled:xpath:.//div[contains(text(),'${fieldName}')]/parent::div//input[@name='enableAutoReceiveNonControlledFlag']/parent::div//label
auto_receive_enablectwo:xpath:.//div[contains(text(),'${fieldName}')]/parent::div//input[@name='enableAutoReceiveControlledTwoFlag']/parent::div//label
auto_receive_enablecthreefive:xpath:.//div[contains(text(),'${fieldName}')]/parent::div//input[@name='enableAutoReceiveControlledThreeToFiveFlag']/parent::div//label
loc_mng_cancel:xpath:.//button[@id='${fieldName}']
select_dist_radiobutton:xpath:.//input[@value='${fieldName}']/following-sibling::label
select_isa_assign:xpath:.//select[@name='${fieldName}']
dashboard_resolve_now_button:xpath:.//a[text()='${fieldName}']
dashboard_resolve_now_msg:xpath:.//form[@name='dashboard']/following-sibling::div/div/p
open_po_card_pending_receive:xpath:.//div[text()='Pending Receive']//following-sibling::div//div//div[text()='${fieldName}']
open_po_card_exported:xpath:(.//div[text()='Exported']//following-sibling::div//div//div[text()='${fieldName}'])[1]
open_po_card_received:xpath:(.//div[text()='Received']//following-sibling::div//div//div[text()='${fieldName}'])[1]
pending_receive_card_item_count:xpath:.//div[text()='Pending Receive']//following-sibling::div//div//div[text()='${fieldName}']/parent::div/../../div[@class='tooltip-text']//following-sibling::div/div
kebab_menu:xpath:.//div[text()='${fieldName}']//i
kebab_menu_expanded:xpath:.//div[text()='${fieldName}']//div[contains(@class,'dropdown-menu')]//a
action_close_button:xpath:.//button[@class='close']
group_by_type_day_count:xpath:(.//div[contains(@class,'dashboard')]/div/span/i)[1]/parent::span/parent::div/following-sibling::div//div[contains(text(),'Pending Receive')]//following-sibling::div//div[contains(text(),'${fieldName}')]/following-sibling::div/div
group_by_type_distributor:xpath:(.//div[contains(@class,'dashboard')]/div/span/i)['${rownum}']
dashboard_last_updated:xpath:.//p/span
click_kebab_menu_option:xpath:.//div[text()='Exported']//div[contains(@class,'dropdown-menu')]//a[text()='${fieldName}']
enter_search_value:xpath:.//input[@id='${fieldName}']
dashboard_refresh_button:xpath:.//button[@id='${fieldName}']/i
action_button:xpath:.//button[@id='${fieldName}']
po_label:xpath:.//h2[text()='Purchasing Dashboard']
po_search_item_result_only:xpath://div[text()='${fieldName}']
click_item_checkbox_in_po:xpath:.//div[contains(text(),'${fieldName}')]/../../preceding-sibling::div/div/label
item_headers:xpath:(.//div[contains(@class,'order-details-container')]//div[contains(@role,'columnheader')]/div[contains(text(),'${fieldName}')])
get_item_name:xpath:.//div[@class='truncate']
click_on_po_internal_card:xpath:.//span[text()='1']
po_description_item_one:xpath:(.//input[@name='${fieldName}'])[1]
invalid_po_number:xpath:.//span[contains(text(),'${fieldName}')]
order_quantity_item_level:xpath:.//div[contains(@class,'right-section')]//input[@name='${fieldName}']
select_checkbox_all:xpath:.//label[contains(@for,'${fieldName}')]
po_item_by_name:xpath:.//div[@class='rt-tr-group']//div[@class='rt-tr -odd']//div[contains(text(),'${fieldName}')]
order_quantity_after_add:xpath:.//div[@class='add-new-pick-form']//div[@class='rt-tbody']//div[contains(text(),'${fieldName}')]/parent::div/parent::div/following-sibling::div//input[@name='toOrderQuantity']
click_checkbox_facilityitem1:xpath:(//div[@class='form-check '])[1]/label
click_checkbox_facilityitem2:xpath:(//div[@class='form-check '])[2]/label
void_link_button:xpath:(.//button[text()='Void'])[${index}]
po_description:xpath:(.//input[@name='purchaseOrderDescription'])[${index}]
void_confirm_text:xpath:.//div[@class='mb-24 popupText']
popup_message:xpath:.//div[@class='toast-body']
order_details_link_text:xpath:.//a[contains(text(),'Order Details')]
input_field:xpath:.//input[contains(@id,'${fieldName}')]
save_po_number:xpath://div[contains(text(),'${fieldName}')]
enter_po_number_per_order:xpath:(.//input[contains(@id,'purchaseOrderNumber')])[${index}]
po_all_isa:xpath:.//div[@class='list-head']
confirm_export_text:xpath:.//div[contains(@class,'modal-body')]/div
input_distributor_website:xpath:.//input[@id='${fieldName}']
distributorOptions_checkbox:xpath:.//div[contains(text(),'${facility}')]/../../following-sibling::div/div/input[@name = '%{flagname}']/../label
select_item_facility:xpath://div[@class='col-3 system-facility-list']//ul//li//div[contains(.,'${fieldName}')]
data_trans:xpath:.//span[@title='${IsaName}']/parent::span/parent::form/parent::div/following-sibling::p//li
po_number_length:xpath:.//input[@id='${fieldName}']
link_edit_new:xpath:.//div[contains(text(),'${item}')]//../../../div/div/button[@id='edit']
item_order_quantity_freeze:xpath:.//div[@class='right-section order-details-container ']//div[contains(text(),'${fieldName}')]//parent::div//following-sibling::div//input[@name='toOrderQuantity']
new_card_item_count:xpath:.//div[text()='New']//following-sibling::div//div//div[text()='${fieldName}']/../..//following-sibling::div/div
exported_card_item_count:xpath:.//div[text()='Exported']//following-sibling::div//div//div[text()='${fieldName}']/../..//following-sibling::div/div
unable_to_order_header:xpath:.//div/p[@class='robotoBold mb-0 fs-16']
resolve_now_link:xpath:.//a[@class='ml-8 robotoBold fs-14']
issue_listing_header:xpath:.//div[@class='header-container d-flex flex-row fs-14 p-16 mb-24']
items_list:xpath:.//div[@class="rt-tr-group"]
item_column:xpath:.//div[@class='rt-tr']/div
issue_button:xpath://button[@id='issue_button']
product_header:xpath:.//div[@class='d-flex justify-content-between align-items-center mb-32']
Add_Ditributor:xpath:.//span[@title='Add Preferred Distributor']
assign_popup:xpath:.//h3[@class='mb-16 modal-title']
item_list:xpath:.//div[@class='rt-tr -odd']/div
assign_cancel:xpath:.//button[@id='cancel']
no_order_msg:xpath:.//p[@class='robotoRegular fs-16']
print_order_sheet:xpath://button[contains(@class,'btn btn-secondary w-150')]
distributor_details:xpath://h5[contains(.,'${fieldName}')]
order_details_Itemsnumber:xpath://div[text()='items in 2 ISAs']
new_order_tile:xpath://div[contains(@class,'truncate d-flex align-items-center justify-content-between fs-16 robotoBold')]
item1:xpath://div[contains(@class,'grid d-flex align-items-baseline isacard active')]
item2:xpath://div[@class='d-flex justify-content-between mb-8'][contains(.,'isamemer - 2Void')]
vicCheck:xpath://div[contains(@class,'rt-td pl-20')]
ndcCheck:xpath://div[contains(@class,'rt-td pl-20')]
statusCheck:xpath://h5[@class='robotoBold'][contains(.,'Order Status')]
recievedCheck:xpath:(//div[contains(.,'Automatic Receive')])[9]
orderedCheck:xpath://div[@class='mb-8']
packetSizeCheck:xpath://input[contains(@name,'toOrderQuantity')]
commentsCheck:xpath:(//input[@class='form-control'])[1]
invoiceRecordCheckbox:xpath://label[contains(@for,'checkbox0')]
invoiceAllRecordCheckbox:xpath://label[contains(@for,'checkboxALL_CHECKBOX_KEY')]
recievedItemsColumn:xpath://input[contains(@name,'receivedItems')]
pendingRecieve:xpath:(//div[@class='truncate d-flex align-items-center justify-content-between fs-16 robotoBold'][contains(.,'${fieldName}')])[1]
itemCostColumn:xpath://input[contains(@name,'cost')]
packageSizeColumn:xpath://input[contains(@name,'packageSize')]
invalidPackageSize:xpath://span[@class='help-block help-block-fixed'][contains(.,'Invalid Package Size')]
invalidCost:xpath://span[@class='help-block help-block-fixed'][contains(.,'Invalid Cost')]
invalidRecieved:xpath://span[@class='help-block help-block-fixed'][contains(.,'Invalid Received')]
commentSection:xpath://button[@type='button'][contains(.,'Add')]
addCommentHereTextField:xpath://textarea[contains(@name,'commentText')]
addCommentButton:xpath:(//button[@type='button'][contains(.,'Add')])[2]
commentAdded:xpath://i[contains(@class,'icon-comment-bold fs-24')]
pOdescription:xpath://p[contains(@class,'sidebar-list truncate max-200')]
pONumber:xpath:(//div[contains(@class,'d-flex flex-column')]//p)[2]
drugClass:xpath://div[contains(@class,'modal-backdrop fade show custom-modal')]
invoiceNumber:xpath://input[contains(@name,'receiveOrderInvoiceId')]
exportNowButton:xpath://button[@type='button'][contains(.,'Export Now')]
exportNowPopup:xpath://button[@type='button'][contains(.,'Yes')]
purchaseOrderNumber:xpath://input[contains(@name,'purchaseOrderNumber')]
recieveButton:xpath://button[@name='Received']
recieveAndSendToQueueButton:xpath://button[@type='button'][contains(.,'Receive and Send To Queue')]
recieveAllButton:xpath://button[contains(@name,'receiveAll')]
recieveAllAndSendToQueue:xpath://button[@type='button'][contains(.,'Receive All and Send To Queue')]
bypassButton:xpath://button[@type='button'][contains(.,'Bypass')]
recievedTilePOPage:xpath:(//div[contains(.,'bypassed')])[14]
recievedTilePOPageOrderStatus:xpath:.//div[text()='Received']//following-sibling::div//div//div[text()='${fieldName}']
pendingrecievedTilePOPageOrderStatus:xpath:.//div[text()='Pending Receive']//following-sibling::div//div//div[text()='${fieldName}']
recieveAllPopupYes:xpath://button[contains(@class,'btn btn-primary w-80')]
recievedStatusChangeInRecievedSection:xpath://span[@class='received'][contains(.,'Received')]
recievedSectionOfPage:xpath:(//div[@class='badge-magenta fs-12 pt-4'][contains(.,'M')])[21]
comment_icon:xpath://i[contains(@class,'comment')]
isaDetailsAreVerified:xpath://h5[contains(.,'Total # of Items')]
type_dropdown:xpath:.//select[@idfield='${dropdown}']
toast_error_message:xpath:.//div[@class='toast-body']
dropdown_by_id_or_name:xpath:.//select[contains(@id, 'tag') or contains(@name, 'tag')]
distributor_auto_create:xpath://div[contains(@class,'collapse show')]//div[contains(@class,'botoBold')][contains(text(),'${fieldName}')]
link_schedule_setting:xpath://button[@id='autoCreate']
modal_screen:xpath://div[@class='${className}']
facility_on_modal_screen:xpath://p[contains(text(),'${facilityName}')]
radio_button:xpath://label[contains(@class,'radio-wrapper')][text()='${fieldName}']
button_modal_screen:xpath://button[contains(@class,'${className}')][contains(text(),'%{fieldName}')]
buttons_modal_screen:xpath://button[contains(@class,'${className}')]
mini_loader:xpath://div[@class='loader-ring']
verify_item_is_present:xpath:.//div[@class='right-section order-details-container ']//div[contains(text(),'${fieldName}')]
get_item_order_quantity:xpath:.//div[@class='right-section order-details-container ']//div[contains(text(),'${fieldName}')]//parent::div/following-sibling::div[contains(@class,'input-right')]/div/input
distributor_name_header:xpath:.//h5[contains(text(),'${fieldName}')]
type_dropdown:xpath:.//select[@name='tag1234889']
autocreate_text:xpath://div[@class='create-po-link']
autocreate_text_status:xpath://div[@class='create-po-link']/a
create_order_based_schedule:xpath:.//input[@id='${fieldName}']
select_schedule_day:xpath:.//button[text()='${fieldName}'][1]
exported_accordian_days:xpath:.//div[contains(text(),'Exported')]//following-sibling::div[@class='inner-accordian-container']//div[contains(text(),'${fieldName}')]
pending_receive_accordian_days:xpath:.//div[contains(text(),'Pending Receive')]//following-sibling::div[@class='inner-accordian-container']//div[contains(text(),'${fieldName}')]
no_record_found:xpath://h5[contains(text(),'No record found')]
item_active_flag:xpath://label[@for='activeFlag']
item_edit_link:xpath:.//button[@id='edit']
dosage_conc_vamount:xpath:.//input[@id='concentrationVolumeAmount']
order_page_item_name:xpath:.//div[contains(@title,'${fieldName}')]
new_state_date_accordian:xpath:.//div[text()='New']/following-sibling::div//i
received_state_date_accordian:xpath:.//div[text()='Received']/../div[contains(@class,'received')]//i/..
exported_state_date_accordian:xpath:.//div[text()='Exported']/../div[contains(@class,'received')]//i/..
expand_accordian_state:xpath:.//div[text()='${fieldName}']/following-sibling::div//i
verify_isa_name:xpath:.//div[@class='list-cards']//button[contains(text(),'Void')]/preceding-sibling::div[contains(text(),'${fieldName}')]
all_isa_text:xpath:.//div[@class='list-head']//div//button[contains(text(),'Void All')]/preceding-sibling::div
po_item_after_add:xpath:.//h5[contains(text(),'Select item to add')]//following-sibling::div//div[@class='ReactTable-tooltip']//div[contains(text(),'${fieldName}')]
po_number_active_order:xpath:.//div[contains(@class,'active')]//input[@placeholder='PO Number']
add_item_search_label:xpath:.//div[contains(@class,'po-create')]//p[contains(@class,'title')]/following-sibling::div//label[contains(text(),'Search for an item to add')]
verify_invoice_by_po_number:xpath://p[contains(text(),'${fieldName}')]
add_item_label:xpath:.//h3[contains(text(),'Add item to order')]
click_resolve_now_link:xpath:.//a[contains(text(),'${fieldName}')]
all_isa_active_count:xpath:.//div[@class='isa-listing']//div[contains(@class,'active')]/span
popup_text:xpath:.//div[contains(@class,'popupText')]/span
received_item_status:xpath:.//span[contains(@class,'received')]
action_button2:xpath://button[contains(@id,'${fieldName}')]
po_invoice_card:xpath://div[@class='list-cards']/div//input[contains(@id,'${fieldName}')]
verify_pending_receive_card:xpath://div[contains(@class,'border-invoice')]/h5[text()='${fieldName}']
verify_new_state_card:xpath://h5[text()='${fieldName}']
item_level_qty_error_msg:xpath:.//div[contains(@class,'right-section')]//span
refresh_Order_button:xpath://button[contains(text(),'${fieldName}')]
invoice_order_type:xpath:.//div[contains(@class,'border-invoice')]/div[contains(.,'${fieldName}')]
item_vic_order:xpath:.//div[contains(@role,'rowgroup')]/div[contains(@class,'vic')]/div[3]//div[contains(@class,'item-description')]
item_ndc_order:xpath:.//div[contains(@role,'rowgroup')]/div[contains(@class,'ndc')]/div[9]//div[contains(@class,'item-description')]
item_vic_invoice:xpath:.//div[contains(@role,'rowgroup')]/div[contains(@class,'vendorIdCode')]/div[3]//div[contains(@class,'truncate')]
item_ndc_invoice:xpath:.//div[contains(@role,'rowgroup')]/div[contains(@class,'productId')]/div[3]//div[contains(@class,'truncate')]
item_pkgsize_order:xpath:.//div[contains(@role,'rowgroup')]/div[contains(@class,'packageSizeQuantity')]/div[8]//div[contains(@class,'item-description')]
item_pkgsize_invoice:xpath:.//input[@id='packageSize']
item_pkgsize_invoicereceived:xpath:.//div[contains(@role,'rowgroup')]/div[contains(@class,'productId')]/div[7]//span
item_create_order_pkgsize:xpath:.//div[@class='ReactTable']//div//div[contains(text(),'${fieldName}')]/../../../div[7]
unable_to_order_item_pkgsize:xpath:.//div[@class='rt-tr -odd']/div[6]//div[contains(@class,'item-description')]
item_quantity_value:xpath:.//input[@name='toOrderQuantity']