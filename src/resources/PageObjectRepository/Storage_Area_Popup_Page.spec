Page Title: Pyxis Logistics

#Object Definitions
======================================================================================================================================
loader:xpath:.//div[@id='laoder3']
mini_loader:xpath://div[@class='loader-ring']
txt_facility:xpath:.//div[@class='mr-24 fs-16']
facility_on_WFA:xpath:.//div[contains(@class,'mainContent')]/../div//form/div/div
div_modal_body:xpath:.//div[@class='modal-body']
list_isa:xpath:.//div[contains(@class,'dataBox')]
list_chkbox_isa:xpath:.//input[contains(@class,'isaCheckBox')]/parent::div[@class='form-check has-error']
chkbox_isa:xpath:.//span[@title='${ISAName}']/..//input[contains(@class,'isaCheckBox')]/parent::div[@class='form-check has-error']/label
chkbox_isa_input:xpath:.//span[@title='${ISAName}']/..//input[contains(@class,'isaCheckBox')]
chkbox_isa_background:xpath:(.//div[contains(@class, 'dataBox')])[${num}]
btn_startWork:xpath:.//button[contains(text(),'Start Work')]
popup_header_columns:xpath:.//div[@class='modal-body']//div[contains(@class, 'header-content')]
list_attribute_isa:xpath://div[contains(@class,'dataBox')]//p[contains(@class,'list')]/../../div[${num}]//li/span
grid_isa_priority_txn_count:xpath:.//div[contains(@class,'dataBox')]//p[contains(@class,'list')]/../../div[${num}]//li[contains(@class,'mb-16 fs-14')]
list_view_option:css:#list
div_list:css:#list>div
div_by_id:xpath:.//div[contains(@id, '${id}')]
data_trans:xpath:.//span[@title='${IsaName}']/parent::span/parent::form/parent::div/following-sibling::p//li
data_list_view:xpath:(.//div[@class='ReactTable'])[2]//div[@class='rt-tbody']/div[${num}]//div[@class='rt-td']/span
option_manualUse:xpath:.//span[@title='${ISAName}']/parent::span/following-sibling::span//input
click_manualUse:xpath:.//span[@title='${ISAName}']/parent::span/following-sibling::span//label
text_manual_use:xpath://span[@title='${item}']/../../span/following-sibling::span/span[contains(text(), 'Manual Use')]/span/input
storage_area_text:xpath:.//h5[contains(text(),'${pageName}')]
storage_area_dropdown:xpath:.//select[@name='wfaFacility']
storage_area_checkbox:xpath://*[@class='form-check has-error']
ISA_list:xpath:xpath:.//*[contains(@class, 'list p-16 mb-0')]	
ISA_list_Data:xpath:.//*[contains(@class, 'fs-16 truncate')]
btn_startwork:xpath:.//button[contains(text(), 'Start Work')]
btn_actions:xpath:.//button[contains(text(), 'Actions')]
link_chooseISA:xpath:. //a[contains(text(),'Choose ISA’s')]
isa_name:xpath:.//*[contains(text(),'${isaName}')]/following::li/span
printer_list:xpath:.//select[@name='isaPrinterList']
grid_view_option:id:tile
div_grid:css:#tile>div
list_actions_items:xpath://a[contains(text(),'${item}')]
drpdown_printer:xpath:.//span[@title='${isaName}']/parent::span/parent::form/parent::div/following-sibling::p//select
drpdown_printer_list:xpath:.//span[@title='${isaName}']/parent::span/parent::form/parent::div/following-sibling::p//select/option
dropdown_approvedComputer:xpath:.//label[@for='${fieldName}']/following-sibling::div/select
dropdown_printerList:xpath:(.//select[@name='isaPrinterList'])[1]
printer_dropdown_for_isa:xpath:.//div[contains(@class,'dataBox')]//span[contains(text(), '${isa}')]/../../../..//select[@name='isaPrinterList']
isa_card_by_name:xpath:.//div[contains(@class,'dataBox')]//span[contains(text(), '${isa}')]/../../../..
link_edit_isa:xpath:.//div[@class='rt-table']//*[text()='Name']/following::div[@class='rt-tr-group']//div[contains(text(),'${field}')]/following-sibling::div/button[contains(text(),'Edit')]
link_edit_approvedComputer:xpath:.//div[@class='rt-table']//*[text()='Approved Computer']/following::div[@class='rt-tr-group']//div[contains(text(),'${field}')]/following-sibling::div/div/button[contains(text(),'%{action}')]
tab_link:xpath:.//div[@class='tabs']//a[contains(text(),'${tabName}')]
inp_field_isa:xpath:.//input[@id='${fieldName}']
added_isa_name:xpath:.//div[contains(text(),'${printerName}')]
icon_mandatory:xpath:.//label[@for='${fieldName}']/span
message_field:xpath:.//div[@class='form-group has-error']/span[contains(text(),'${msg}')]
page_heading:xpath:.//b[contains(text(),'${field}')]
save_button:xpath:.//button[@id='save']
label_approvedComputers:xpath:.//input[@name = '${activeFlag}']/../label
button_by_text:xpath:.//button[contains(text(),'${text}')]
label_radio_by_text:xpath:.//label[contains(text(), '${text}')]
modal_approvedComputer:xpath:.//div[@class='modal-body']//button[contains(text(),'${name}')]
added_approved_computer:xpath:.//div[text()='${computerName}']
modal_approvedComputerPopup:xpath:.//div[@class='modal-body']//h3
first_external_isa:xpath:(.//div[contains(text(),'External')])[1]/..//div[@class='rt-td'][1]
ISA_nam_on_WFA_screen:xpath://span[@class='fs-16 truncate']
row_isa:xpath:.//div[@class='rt-tbody']/div[@class='rt-tr-group']
list_priority_isa:xpath:.//div[contains(@class,'dataBox')]//p[contains(@class,'list')]/../../div[${num}]//li[contains(@class,'mb-16 fs-14')]
no_pick_error:xpath:(.//div[contains(@class, 'dataBox')]//p[contains(@class,'list')])[${num}]//div[text()='%{error_msg}']
check_disabled_isa_gridview:xpath://span[contains(text(),'${fieldName}')]/../../../..
check_disabled_isa_listview:xpath:.//div[contains(text(),'${fieldName}')]/../../..
======================================================================================================================================
