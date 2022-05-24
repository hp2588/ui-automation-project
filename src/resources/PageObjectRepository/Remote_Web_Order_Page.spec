Page Title: Pyxis Logistics
		
#Object Definitions		
======================================================================================================================================
loader:xpath:.//div[@id='laoder3']
mini_loader:xpath://div[@class='loader-ring']
dropdown_destination:xpath:.//select[@name='${name}']
tab_header_order:xpath:.//span[text()='${name}']/parent::*[contains(@class,'dropdown-toggle')]
order_data:xpath:.//div[contains(text(),'${item}')]/../../following-sibling::div[6]//div[text()='%{status}']
RO_item:xpath:.//div[contains(text(),'${text}')]
item_row:xpath:.//div[contains(text(),'${item}')]/../../..
items_RO_card:xpath:.//div[contains(@class,'card-items')]//div[@class='rt-table']//div[contains(@class,'rt-td')]
items_RO_quantity:xpath:.//div[contains(@class,'card-items')]//input[@name='quantity']
action_button:xpath:.//button[@id='${action}']
order_name:xpath:.//input[@id='${action}']
action_toggle_button_1:xpath:.//label[@for='${toggle}']
search_box:xpath:.//input[@name='${searchFilter}']
popup_cancel_ro:xpath:.//div[@class='modal-content']//span
order_item_list:xpath:.//div[contains(@class,'card-items')]//div[@class='rt-table']//div[contains(@class,'rt-td')][1]
sort_icon:xpath:.//div[@class='rt-resizable-header-content'][text()='${column}']
text_column:xpath:.//div[@class = 'rt-tr-group']//div[@class='rt-td']
item_list:xpath:.//div[@class='mt-24']//div[@class = 'rt-tr-group']
item_details:xpath:.//div[contains(text(),'${field1}')]//following::div/span[text()='%{field2}']
capture_column:xpath:.//div[@class = 'rt-tr']//following::div[@class='rt-tr-group']//div['${num}']
cross_button:xpath:.//span[contains(@class,'cursor-pointer')]
page_link:xpath:.//div[@class='d-flex']//span[contains(@class,'page-link') and text()='${char}']
destination_name_ro:xpath:.//span[contains(text(),'${Destination}')]/../p
error_msg:xpath:.//div[contains(@class,'error')]//span[text()='${text}']
text_quantity:xpath:.//input[@id='${id}']
item_pagination:xpath:.//ul[@class='pagination']//span[27]/following::span
vertical_scroll:xpath:.//div[contains(@class,'vertical-scrollbar-present')]
no_destination:xpath:.//div[contains(text(),'When you select a destination, the orders for that choice will show here')]
first_col_data:xpath:.//div[@class='rt-tr-group']/child::div/div[${num}]
order_data_view_order:xpath:.//div[contains(text(),'${name}')]
view_order_record:xpath:.//div[contains(text(), '${orderName}')]/../../../../div
destination_name:xpath:.//div[contains(@class,'isacard')]//button[text()='Void']/parent::div/div
ordred_quanity:xpath:.//div[contains(text(),'${item}')]//following::div[6]
item_checkbox:xpath:.(//div[contains(text(),'${item}')]/../../div//label[contains(@for,'checkbox')])[2]
success_mesg:xpath://div[contains(text(),'${text}')]
item_quantity_po:xpath:xpath:.//div[contains(text(),'${item}')]/following::input[@name='toOrderQuantity']
item_list_po:xpath://div[contains(@class,'purchaseOrderDetailKey')]//div[contains(text(),'${item}')]
order_quantity_multiples:xpath:.//div[contains(text(),'${item}')]/following::div[%{num}]
item_desc:xpath:.//div[@role='columnheader']//div[text()='Item Description']/following::div[contains(@class,'rt-td') and contains(text(),'${item}')]
item_details_orderpage:xpath:.//div[@role='columnheader']//div[text()='Item Description']/following::div[contains(@class,'rt-td') and contains(text(),'${item}')]/following-sibling::div[${index}]
page_title:xpath:.//div[contains(@class,'robotoBold') and contains(text(),'${page}')]
right_panel:xpath://div[text()='Click items at left to add them to this shopping cart']
column_path:xpath://div[contains(@class,'left-section')]//div[@class='rt-tr-group']/child::div/div[${num}]
order_page_colum_data:xpath:.//div[contains(@class,'viewAllOrder-details')]//div[@class='rt-tr-group']/child::div/div[${num}]
input_field:xpath:.//input[contains(@id,'${fieldName}')]
checkbox_against_item:xpath:.//div[contains(text(),'${item}')]//following-sibling::div//label[contains(@for,'%{flag}')]
input_field_against_Item:xpath:.//div[contains(text(),'${item}')]//following-sibling::div//input[contains(@id,'${input}')]
items_RO_quantity_with_id:xpath:.//input[@id='${item}']
no_destination_user:xpath:.//div[contains(text(),'You have not been designated to order items for any destination. Consult your system administrator." in case user is not mapped with the destination')]
view_order_name:xpath:.//div[contains(@class,'item-description') and text()='${name}']
