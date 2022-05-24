Page Title: Pyxis Logistics		
			
#Object Definitions			
======================================================================================================================================			
pick_queue_text:xpath:.//div[@class='tabs']/div/a			
loader:xpath:.//div[@id='laoder3']		
mini_loader:xpath://div[@class='loader-ring']	
pick_queue_all_checkbox:xpath:.//label[@for='allCheckbox']	
batchpick_queue_all_checkbox:xpath:.//label[@for='allCheckbox2']		
pick_queue_all:xpath:.//label[contains(@class,'form-check-label')]			
allCheckbox:xpath://div[@class='rt-resizable-header-content']//label[@class='form-check-label']			
pick_now_button:xpath:.//div[@class='d-flex']/button			
#tab_restocks:xpath://a[contains(text(), 'Restock')]  			
#icon_sort:xpath:.//div[contains(text(),'${column}')]			
#restock_select:xpath:.//div[contains(text(),'${column}')]/..//button[@id='Restock'][1]			
popup_restock:xpath:.//div[@class='modal-content']			
txt_itemId_itemDescription:xpath:.//h5[contains(text(),'${column}')]/following-sibling::p			
txt_itemID:xpath:			
txt_quantity:xpath:.//label[contains(text(),'Quantity')]/following-sibling::div/input			
txt_expiratrionDate_lotNumber:xpath:.//div[contains(text(),'${column}')]			
field_expirationDate:xpath:.//input[@id='expirationDate']			
field_lotNumber:xpath:.//input[@id='lotNumber']			
#btn_Add:xpath:.//button[contains(text(),'Add')]			
btn_Add:xpath:.//div[@class='d-flex align-items-center h-40']/button			
list_actions_items:xpath:.//a[contains(text(), '${item}')]			
icon_menu:xpath:.//i[@class='icon-menu-bold']/..			
link_menuOptions:xpath:.//a[contains(text(),'${column}')]   			
pick_now_first_transaction:xpath:(//div[@class='rt-tr-group'])[1]/div/div
#(.//div[@class='rt-table']//*[text()='Priority']/following::div[@class='rt-tr -odd' or @class='rt-tr -even'])[1]//div[@class='rt-td']
#.//div[@class='rt-table']//*[text()='Transaction Type']/following::div[@class='rt-tr -odd']/div[@class='rt-td']			
pick_queue_all_transaction:xpath:.//div[@class='rt-tbody']/div[@class='rt-tr-group']			
txt_item_description_new:xpath:.//h1			
#txt_item_quantity:css:.qtyFont			
txt_item_descriptions:xpath://div[contains(@class,'rt-tr -odd') or contains(@class,'rt-tr -even')]/div[5]			
txt_item_quantity:xpath:.//div[@class='qtyFont text-center visible pr-16 pl-16 robotoMedium']			
txt_item_location:xpath:.//span[@class='truncate d-inline-block']			
#.//div[@class='midPlanel pt-22 pb-22 ']//div[@class='contentBox']/span			
pick_queue_transaction_count:xpath:.//div[@class='tabs']/div/a/span			
transactions_table_count:xpath:.//div[@class='rt-tr-group']			
activate_first_transaction:xpath:(.//div[@class='rt-tr-group']//*[contains(text(),'Pick Now')])[1]			
mid_panel_status:xpath:(//p)[3]			
right_panel_status:xpath:.//div[@class='rightPanel']/div/span			
active_transaction_box:xpath:.//div[contains(@class,'midPlanel')]//h1			
#link_work_without_scanner:xpath:.//div[@class='midPlanel pt-22 pb-22 ']/child::div//a	
link_work_without_scanner:xpath:.//a[text()='Scan Override (F2)']			
bar_progress_success:xpath:.//div[@class='progress']/div[@class='progress-bar bg-success']			
dialog_box_authorization_popup:xpath:.//div[@class='modal-content']			
#pick_queue_all_checkbox:xpath:.//input[@id='allCheckbox']/parent::div[@class='form-check']			
txt_item_description:xpath:.//h1[contains(text(),'${item}')]
txt_item_subHeading:xpath:.//h2[contains(text(),'${item}')]						
txt_currentPick:xpath:.//div[contains(@class,'currentPick')]
priority_currentPick:xpath:.//div[contains(@class,'currentPick')]/../div[2]/span	
txt_popup_userid:xpath:.//input[@id='userID']			
txt_popup_password:xpath:.//input[@id='password']			
btn_popup_cancel:xpath:.//button[@id='deleteCancelButton']			
btn_popup_confirm:xpath:.//div[@class='modal-body']//button[@id='deleteConfirmButton']			
link_change_quantity:xpath:.//div[@class='leftPanel']/child::div/div[3]/a			
change_quantity_popup:xpath:.//div[@class='numpadContainer']			
txt_change_quantity_popup:css:span.changeQuantity			
input_quantity:css:input#numpadInput			
btn_change_quantity_popup:xpath:.//div[@class='row']			
btn_reset_quanitity_change_popup:css:#Reset			
btn_cross_quanitity_change_popup:css:#X			
txt_quantity_value:css:#numpadInput			
btn_save_quantity:css:#saveQuantity			
txt_actual_quantity:xpath:.//div[@class='leftPanel']/div/div			
btn_cancel_quantity_change_popup:css:#cancel			
btn_actions:xpath:.//button[contains(text(), 'Actions')]			
#icon_sorting:xpath:.//div[contains(text(), '${item}')]/parent::div[@class='rt-th rt-resizable-header -sort-asc -cursor-pointer']			
txt_tq_headers:xpath:.//div[contains(text(),'${item}')]			
current_pick_color:xpath:.//div[@class='activeItemContainer position-relative']			
link_hold:xpath://div[contains(text(),'${priority}')]/..//button[@id='%{action}'][1]			
txt_numberOfTrans:xpath:.//div[@class='rt-tr-group']			
tab_actions:xpath://a[contains(text(), '${action}')]			
btn_type:xpath:.//div[contains(@class,'actionButtons')]//button[@name='${action}']			
btn_type:xpath://div[contains(@class,'actionButtons')]//button[@name='${priority}']	
button_secondHoldRestock:xpath:.//button[@class = 'd-inline-block btn btn-secondary mr-16 order3' and @name='${action}']
button_secondRelease:xpath:.//button[@class = 'btn btn-secondary mr-2' and @name='Release']
button_releaseLink:xpath:.//button[@class = 'btn btn-link mr-20' and @name='Release']
chkbox_PickCheckbox:xpath:.//div[@class='rt-table']//*[text()='Transaction Type']/following::div[@class='form-check']			
chkbox_input_PickCheckbox:xpath:.//div[@class='rt-table']//*[text()='Transaction Type']/following::div[@class='form-check']//input			
popup_manual_cycle_count:xpath:.//*[@class='manualCycleCount']/h3			
link_count_transaction_actions:xpath:.//button[@id ='${item}']	
toast_message_success:xpath:.//div[@class='toast-body']			
#tq:xpath:.//div[@class='rt-table']			
#success_msg_after_override:xpath:.//			
btn_save_restock_details:xpath:.//div[@class='modal-body']//button[@id='save']			
btn_transaction_actions:xpath:.//div[@class='rt-table']//*[text()='Transaction Type']/following::div[@class='rt-tr -odd'][2]/div[@class='rt-td']//button[contains(text(),'${btnType}')]	
restock_now_first_transaction:xpath:.//div[@class='rt-table']//*[text()='Transaction Type']/following::div[@class='rt-tr -odd']/div[@class='rt-td']			
tab_restocks:xpath:.//a[contains(text(), 'Restock')]			
#restock_select:xpath:(//button[@id='Restock'])[1]			
Add_btn:id:add			
restock_confirm:id:save	
qOH_add_pick_screen:xpath://div[contains(text(),'Quantity on Hand')]/../../../../div[2]/div/div/div[4]/div/div[contains(text(),'${qOH}')]
#//div[contains(@class,'columnRightAlign')][contains(text(),'${qOH}')]		
restock_all_checkbox:xpath:.//label[@class='form-check-label']
scan_option:xpath://label[contains(@class,'${className}')]		
restock_button:xpath:.//div[@class='d-flex']/button[4]			
checkbox_first_transaction:xpath:.//div[@class='rt-tbody']//div[1]//div[1]//div[2]			
restock_select:xpath:.//div[@class='rt-tr-group'][1]//*[contains(text(),'Restock Now')]			
scan_text:xpath:.//div[contains(@class,'midPlanel')]//p[contains(text(), '${text}')]			
refresh_button:xpath:.//button[@id='Refresh']			
#restock_based_on_itemname_and_priority:xpath:(.//span[contains(text(),'${priority}')]/../../../div/div/div[contains(text(),'%{ItemName}')]/../../../div[8]/div/button[@id='Restock'])[1]	
pick_based_on_itemname_and_priority:xpath:(.//span[contains(text(),'${priority}')]/../../../div/div/div[contains(text(),'%{ItemName}')]/../../../div[9]/div/button[@id='Pick'])[1]		
hold_pick_based_on_itemname_and_priority:xpath:(.//span[contains(text(),'${priority}')]/../../../div/div/div[contains(text(),'%{ItemName}')]/../../../div[8]/div/button[@id='Hold'])[1]
#delete_pick_based_on_itemname_and_priority:xpath:(.//span[contains(text(),'${priority}')]/../../../div/div/div[contains(text(),'%{ItemName}')]/../../../div[8]/div/button[@id='Delete'])[1]
delete_pick_based_on_itemname_and_priority:xpath:(.//span[contains(text(),'${priority}')]/../../../../div/div/div/div[contains(text(),'%{ItemName}')]/../../../../div/div[9]/div/button[@id='Delete'])[1]
scan_text:xpath:.//div[contains(@class,'midPlanel')]//a[contains(text(), '${text}')]			
refresh_button:xpath:.//button[@id='Refresh']		
addedRestockItemName:xpath:(//form[@name='add-pick-form']//div[@class='rt-td table-tooltip-overflow']/div/div)[1]	
add_pick_button:xpath:.//button[@name='Add Pick']			
add_Pick_txt:xpath:.//div/h3[contains(text(),'Add Pick')]			
search_item:xpath:.//input[@name='queueFilter']
button_corresponding_to_patient_name:xpath://div[contains(text(),'${patientfirstName}')]/following::div/button[contains(text(),'%{buttonName}')]		
patientName_in_pick_queue:xpath://div[contains(text(),'${patientfirstName}')]
#clear_item_search:xpath:.//span[@class='icon-close-bold-sm form-control-clear']/child::before			
clear_item_search:xpath:.//span[@class='icon-close-bold-sm form-control-clear']			
Search_result_item:xpath:.//div[@class="search-label fs-14 mb-8 robotoBold"]/div			
first:xpath:.//div[@class='modal-body']/div/form/div[1]/div/div/div/following::div[1]/div/div/div[1]/div/following::div[2]/div			
#first:xpath:(//form[@name='add-pick-form']//div[@class='rt-tr -odd'])[1]			
popup_add_pick:xpath:.//div[@class='modal-content']			
text_add_pick:xpath:.//div[@class='modal-body']//h3			
add_pick_message:xpath:.//div[@class='messageBeforeSearch d-flex flex-column justify-content-center align-items-center']/h5			
search_field:xpath:.//div[@class='rt-tr']			
no_item_found:xpath:.//h5[@class='robotoBold fs-16 mb-16']			
no_data_available:xpath:.//div[@class='rt-noData']
cancel_btn_add_pick:id:cancel			
search_row_result:xpath:.//div[@class='rt-tr-group']/div			
click_element:xpath:.//div[@class='rt-tbody']/div[1]/div/div[contains(text(),'${item}')]			
Quantity:id:quantity			
icon_mandatory:xpath:.//label[contains(text(),'${field}')]			
Transaction Priority:xpath:.//select[@name='priority']	
Source Location:xpath:.//select[@name='sourceLocation']			
Destination:xpath:.//select[@name='destination']			
save_close_btn:xpath:.//button[contains(text(),'${field}')]			
cancel_btn:xpath:.//button[contains(text(),'${field}')]			
cancel_popup:xpath:.//div[@class='modal-content']/div/h5[contains(text(),'Confirm')]			
save_add_btn:xpath:.//button[contains(text(),'${field}')]			
required_message:xpath:.//div[@class='robotoRegular fs-14 mb-16 darkGray']			
additional_info_toggle:xpath:.//label[@for='toggle']			
patient_last_name:xpath:.//input[@id='lastname']			
patient_first_name:xpath:.//input[@id='firstname']			
patient_middle_name:xpath:.//input[@id='middlename']			
account_number:xpath:.//input[@id='account']			
room:xpath:.//input[@id='room']			
bed:xpath:.//input[@id='bed']			
comments:xpath:.//textarea[@id='comment']			
visit_number:xpath:.//input[@id='account']			
mrn:xpath:.//input[@id='mrn']			
order_number:xpath:.//input[@id='order']			
confirm_message:xpath:.//div[@class='modal-body']/p			
confirm_yes_btn:xpath:.//button[@id='primary']			
search_tq:xpath:.//input[@id='searchFilter']			
destination_sort_icon:xpath://*[@id="Picks_tabContent"]/div/div/div[1]/div/div[6]			
icon_sorting:xpath:.//div[@class='rt-th rt-resizable-header -cursor-pointer']/div[contains(text(),'${colname}')]			
icon_sorting_item:xpath://div[@class='rt-th rt-resizable-header -sort-asc -cursor-pointer']
header_col_name:xpath:.//div[contains(@class, 'rt-th rt-resizable-header')]
activate_first_restock_transaction:xpath:.//div[@class='rt-tr-group']//*[contains(text(),'Restock Now')]			
restock_first_trans:xpath:.//div[@class='rt-tr-group'][2]/div/div		
#restock_first_trans:xpath:.//div[@class='rt-tr-group']
change_qty_btn:xpath:.//a[contains(text(),'Change')]
edit_active_item_quantity:xpath:.//div[contains(@class,'qtyFont')]
active_item_qty_value:xpath:.//div[contains(@class,'${className}')]
edit_on_hand_item_quantity:xpath:.//div[contains(@class,'on-hand-qty')]
xpath_qoh:xpath://div[contains(@class,'on-hand-qty onHandFocus')]
queue_header:xpath://p[contains(@class,'tabUperHeader')]
save_qty_btn:xpath:.//a[contains(text(),'Save')]
qty_textbox:xpath:.//input[@name='changeQuantityInput']
qty_onHandInput:xpath:.//input[@name='changeOnHandInput']
qty_textbox1:xpath://div[contains(@class,'update-quantity ')]/div
show_all_items_toggle:xpath:.//label[@for='toggle']
restock_active_bar_message:xpath:.//div[@class='medicineNameBox pl-30 pr-30']/h1			
button_holdConfirm:xpath:.//button[@id='holdConfirmButton']			
first_return:xpath:.//div[@class='modal-body']/div/form/div[2]/div/div[2]/div
first_item:xpath://form[@name='add-pick-form']//div[contains(text(),'${ItemName}')]		
first_pick:xpath:(.//div[@class='modal-body']/div/form/div[2]/div/div/div[2]/div/div)[1]
first_item_add_pick_form:xpath:(//form[@name='add-pick-form']//div[@class='rt-tr -odd']//div[@class='item-description truncate'][contains(text(),'${ItemName}')])[%{row}]		
modal_search_results:xpath://form[@name='add-pick-form']//div[@class='rt-tbody']//div[@class='rt-td'][${colNum}]
#first_pick:xpath:.//div[@class='rt-tr-group']/child::div/div[1]			
error_message_quantity:xpath:.//span[@class='help-block help-block-fixed']			
fields_restock_popup:xpath:.//h5[contains(text(),'${colname}')]			
quantity_restock_popup:xpath:.//input[@id='confirmQuantity']			
lot_expiry_unknown_checkbox:xpath:.//label[@for='chkLotRequired']			
earlierst_expiration_error:xpath:.//input[@class='form-control w-200 h-32 date-validation-error']			
lot_number_error:xpath:.//div[@class='form-group has-error']			
cancel_return_popup:xpath:.//div[@class='modal-content']/div/h3[contains(text(),'Confirm')]			
confirm_message_return:xpath:.//div[@class='modal-body']/h3/following-sibling::span			
button_ManualRestockTrans:xpath:.//div[@class='rt-tr-group']/div/div[contains(text(), 'MANUALRESTOCK')]/following-sibling::div/div/button[@id='Restock'][1]			
bin_scan_override:xpath:.//a[contains(@class,'withoutScannerLink')]			
active_transaction_button:xpath:.//div[contains(@class, 'rightPanel')]//button[text()='${action-button}']			
dropdowns_wasteReason:xpath:.//label[@for='${fieldName}']/following-sibling::div/select			
popup_waste_item:xpath:.//div[@class='modal-content']//h3			
textarea_holdReason:xpath:.//textarea[@name='holdDescription']			
waste_item_popup_button:xpath:.//div[@class='modal-content']//button[contains(text(),'${button-name}')]			
message_field:xpath:.//div[@class='form-group has-error']/span[contains(text(),'${msg}')]			
link_CycleCount:xpath:.//a[contains(text(),'Cycle Count')]	   			
patient_name_sort_icon:xpath://div[@class='rt-thead -header vertical-scrollbar-present']//div[7]	
popup_message:xpath:.//*[contains(@class, 'toast')]
receiving_txn_checkbox:xpath://div[contains(text(),'${fieldName}')]/preceding-sibling::div[contains(text(),'RECEIVING')]/preceding-sibling::div//label
receiving_restock_now:xpath://div[contains(text(),'${fieldName}')]/preceding-sibling::div[contains(text(),'RECEIVING')]/following-sibling::div//button[contains(text(),'Restock Now')]
update_quantity_restock:xpath://div[contains(@class,'leftPanel')]//div[contains(@class,'mb')]//following::div[contains(@class,'${class}')]
update_restock_amount:xpath://div[@class='${fieldName}']/div[contains(@class,'form-group')]/input[@name='changeQuantityInput']
restock_transaction_quantity:xpath://div[contains(text(),'${fieldName}')]/../../preceding-sibling::div[contains(@class,'rt-td table-tooltip-overflow')]//div[@class='item-description truncate']
queue_text:xpath:.//p[contains(text(),'Queue')]
hold_button:xpath://div[@class='d-flex']//button[@id='${fieldName}']
modal_header:xpath://h3[contains(text(),'${fieldName}')]
lot_expiration_rows:xpath://div[@class='InstantRestock-table']//div[contains(@class,'rt-tr -even') or contains(@class,'rt-tr -odd')]/div[2]/div/div[contains(text(),'${lotNumber}')]
pick_ro_transaction:xpath:.//div[contains(text(),'${priority}')]/following-sibling::div[contains(text(),'%{item}')]/following::button[@id='Pick']
waste_reason_list:xpath:.//select[@name='wasteReasonId']
waste_quantity:xpath:.//input[@name='${name}']
active_transaction_quantityOnHand:xpath:.//div[@class='on-hand']//div[@class='on-hand-qty ']
waste_reason_labels:xpath:.//label[@for='${item}']/following::p
icon_mandatory_required:xpath:.//*[contains(text(),'${text}')]/span
picknow_txn_checkbox_sanity:xpath:(.//div[contains(text(),'${fieldName}')]/../../../div[2]/div/label)[1]
click_picknow_button_sanity:xpath:.//div[contains(text(),'${fieldName}')]/../../../div[9]/div/button[contains(text(),'Pick Now')]
verify_return_trxn:xpath:(//span[@class='text-underline-dotted'][contains(text(),'${priority}')])[1]
restock_transaction_checkbox:xpath:.//span[contains(text(),'${item}')]/../../../div//label
click_restocknow_button_sanity:xpath:.//span[contains(text(),'${priority}')]/../../../div//following-sibling::div//button[text()='Restock Now']
no_active_restock_transaction:xpath:.//div[contains(@class,'medicineNameBox')]/h1
click_restock_hold_button_sanity:xpath:.//div[contains(text(),'${fieldName}')]/following-sibling::div//button[contains(text(),'Hold')]
click_release_button_sanity:xpath:.//div[contains(text(),'${fieldName}')]/following-sibling::div//button[contains(text(),'Release')]
click_delete_button_sanity:xpath:.//div[contains(text(),'${fieldName}')]/following-sibling::div//button[contains(text(),'Delete')]
enter_delete_reason_sanity:xpath:.//textarea[@name='${fieldName}']
confirm_delete_button_sanity:xpath:.//button[contains(text(),'${fieldName}')]
restock_button_sanity:xpath:.//button[text()='Restock Now' and (@id='Restock')]
hold_button_sanity:.//button[text()='Restock' and (@id='Restock')]/following-sibling::button[text()='Hold']
delete_button_sanity:xpath:.//button[text()='Restock' and (@id='Restock')]/following-sibling::button[text()='Delete']
second_sort:xpath:.//select[@name='${fieldName}']
message_label_scan:xpath://p[contains(text(),'${messageContent}')]
cycleCount_trans:xpath://div[@class='rt-tbody']//div[contains(text(),'Cycle Count')]
confirm_qty_cycle:xpath:.//button[@id='saveAndCloseButton']
trans_cycle:xpath://div[@class='rt-tbody']//div[contains(text(),'${fieldName}')]
QOH_Popup:xpath:.//div[@class='form-group fs-32 robotoBold']
waste_items_toggle:xpath:.//label[@for='wasteItems']
quantity_wasted:xpath:.//input[@id='quantityWasted']
wfa_priority:xpath:.//div[@class='h-112 mb-16']/li
all_days_Cycle:xpath:.//button[contains(@id,'20_pm')]
disable_day_checkbox:xpath:.//label[@for='Sunday_checkbox']
active_bar_btn:xpath:.//button[@class='btn btn-primary']
hold_button_click:css:#Hold
delete_button_click:xpath:.//div[@class='rt-td']//button[@id= 'Delete' and text()='Delete']
release_button_sanity:css:#Release
text_verifyQuantity:xpath:.//p[contains(text(),'Quantity Verification Required" Enter Picked Quantity')]
text_pickLabelScan:xpath:.//p[contains(text(),'Waiting for Pick Label Scan')]
para_by_text:xpath:.//p[contains(text(),'${text}')]
tq_header:xpath:.//div[contains(@class,'tqHeader')]/div/div/div
tq_page_header:xpath:.//div[contains(@class,'tqHeader')]//following::h1
tq_page_header_second:xpath:.//div[contains(@class,'tqHeader')]//following::h2
text_column:xpath:.//div[contains(text(), '${column}')]
facility_on_WFA:xpath:.//div[contains(@class,'mainContent')]/../div//form/div/div
bin_scan_msg:xpath://h3[text()='Cycle Count']/following::label[text()='Scan the Bin Label to continue...']
unthorized_computer:xpath://div[contains(text(),'${text}')]
transaction_priorities_on_TQ:xpath://div[contains(@class,'boxData')]//div/li
transaction_priorities_on_isa_box:xpath:.//div[contains(@class,'dataBox')][${num}]//div/li
isa_boxes:xpath:.//div[contains(@class,'dataBox')]
btn_hold_first:xpath:.//div/button[@id='Hold']
btn_release_first:xpath:.//div/button[@id='Release']
btn_restocknow_first:xpath:.//div/button[contains(text(), 'Restock Now')]
first_hold_restock:xpath:.//div/button[contains(text(), 'Restock Now')]/following-sibling::button[@id='Hold'][1]
btn_picknow_first:xpath:.//div/button[contains(text(), 'Pick Now')]
crossIcon_TQ:xpath:.//button[@class='close']/span
success_message:xpath:.//div[@class='success toast fade show']/div[@class="leftContent float-left"]
patientName_text:xpath:.//div[contains(text(), '${patientName}')]
patientName_activetext:xpath:.//span[contains(text(), '${patientName}')]
button_AddRestock:xpath:.//button[@name='${action}']
text_onHoldTab:xpath:.//div[contains(text(),'On Hold')]
button_hold_active:xpath:.//button[@id=1]
action_button:xpath:.//button[@id='${action}']
button_actions_TQ:xpath:.//button[@id='${button}'] 
#transaction_priority_locked_item:xpath://div[@class='rt-td']
transaction_priority_locked_item:xpath://div[contains(@class,'rt-tr ') and @role='row']
txt_no_data_TQ:xpath:.//div[text()='No Data Available.']
isa_name_tq:xpath:.//div[@class='midPlanel pt-24 pb-24 ']/div/div/h2
restock_based_on_itemname_and_priority:xpath:(//span[contains(text(),'${priorityName}')]/../../../div[5]/div/div[contains(text(),'%{itemName}')]/../../../div[9]//button[contains(text(),'Restock')])[1]
#(//div[contains(text(),'${priorityName}')]/../div[5]/div/div[contains(text(),'%{itemName}')]/../../../div[8]/div/button[contains(text(),'Restock')])[1]
priority_header:xpath:.//div[contains(text(),'Current Restock - ')]
record_count_restock_popup:xpath:.//div[@class='modal-content']//button[text()='Delete']
restock_popup_reactTable:xpath://div
first_hold_return:xpath:(//div/span[contains(text(),'Return')]//following::button[text()='Hold'])[1]
dropdown_name:xpath://select[@name='${fieldName}']/../../label
list_batchPriorityName:xpath:.//div[@class='rt-td']/div/div[@class='robotoBold' and text()='${priorityName}']
columnHeading_batchPick:xpath:.//div[@class='rt-resizable-header-content'][text()='${column}']
heading_batchPick:xpath:.//div[@class='fs-20 mb-24 robotoRegular'][text()='Review/Release Batch Pick']
list_link_Ignore:xpath:.//button[@id ='ignore' and @class='btn btn-link ml-24 mr-10']
list_link_release:xpath:.//button[@id ='release' and @class='btn btn-link']
list_link_processed:xpath:.//div[@class='rt-tr-group']/div/div/div[contains(text(), 'Processed')]
list_link_batchPick:xpath:.//div[@class='rt-tr-group']/div/div/div/div[contains(text(), 'BATCH PICK')]
list_link_batchPickPickNow:xpath:.//div[@class='rt-tr-group']/div/div/div/div[contains(text(), 'BATCH PICK')]/../../following-sibling::div/div/button[@id='Pick']
list_batchCreationTime:xpath:.//div[@class='rt-td']/div/div[@class='robotoRegular mt-4 date-time']
button_releaseRightPanel:xpath:.//button[@id ='release' and @class='btn btn-primary']
button_ignoreRightPanel:xpath:.//button[@id ='ignore' and @class='btn btn-primary ml-24 mr-24']
list_batckCreationTimeRightPanel:xpath:.//div[@class='rt-td']/div/div[@class='robotoBold' and text()='${priorityName}']/following-sibling::div[@class='robotoRegular mt-4 date-time']
tq_dashboard_headers:xpath://div[@role='columnheader']
text_no_data:xpath:.//div[@class='rt-noData']
quantity_activeBox:xpath:.//div[contains(@class,'update-quantity')]/div
onFocus_Element:xpath:.//div[@class='update-quantity onFocus']/div
onFocus_Element_qoh:xpath:.//div[@class='on-hand-qty onHandFocus']/div
heading_batchPopup:xpath:.//div[contains(@class, 'modal-content')]/div/div/div[contains(text(), '${heading}')]
subHeading_batchPopup:xpath:.//div[contains(@class, 'modal-content')]/div/div/div[contains(text(), '${heading}')]/div/span
subHeading_batchPopupQuantity:xpath:.//div[contains(@class, 'modal-content')]/div/div/div[contains(text(), '${heading}')]/div
textbox_processedQuantity:xpath:.//input[@name='processedQuantity']
list_link_Return:xpath:.//div[@class='rt-tr-group']/div/div[@class='rt-td' and text() ='RETURN']
quantity_cce_trxn:xpath://div[contains(text(),'${patientName}')]/../div[4]
dropdowns_externalSystem:xpath:.//label[@for='${fieldName}']/following-sibling::div/select
cycle_count_txn_active:xpath:(//div[contains(@class,'tqHeader')]//div[contains(@class,'currentPick')]/following::div/span)[1]
tq_notification:xpath://div[@class='modal-body']
outside_tq:xpath:.//html
reconfirm_qty_cycle:xpath:.//input[@id='confirmQuantityReConfirm']
delete_transaction_with_priority:xpath:.//span[contains(text(),'${priority}')]/../../../div//following-sibling::div//button[text()='Delete']
release_cycle_Tx:xpath://button[@id='Release']
list_columnHeaders:xpath://div[contains(@class,'modal-body')]//div[contains(text(),'${text}')]
###########Added by BD Team#######################
qoh_restock:xpath://input[@name='changeOnHandInput']
waste_item_btn:xpath:.//button[contains(text(),'Waste')]
radio_button_disabled_class:xpath://div[contains(@class,'${className}')]
qty_update_confimation_popup:xpath:.//div[@class='modal-content']//*[contains(text(), 'Is this correct?')]
======================================================================================================================================			
