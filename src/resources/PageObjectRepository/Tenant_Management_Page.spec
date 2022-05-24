Page Title: PLX-UI		
		
#Object Definitions		
======================================================================================================================================
loader:xpath:.//div[@id='laoder3']
dropdown_destination:xpath:.//select[@name='${name}']
tab_header_order:xpath:.//span[text()='${name}']/parent::*[contains(@class,'dropdown-toggle')]
order_data:xpath:.//div[@class='rt-table']//*[text()='Order name']/following::div[@class='rt-tr-group']//div[contains(text(),'${name}')]/following::div[text()='%{status}']
RO_item:xpath:.//div[contains(text(),'${text}')]
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
item_details:xpath:.//div[text() ='${item_name}']/following::div[text()='%{quantity}']
capture_column:xpath:.//div[@class = 'rt-tr']//following::div[@class='rt-tr-group']//div['${num}']
cross_button:xpath:.//span[contains(@class,'cursor-pointer')]
page_link:xpath:.//div[@class='d-flex']//span[contains(@class,'page-link') and text()='${char}']
destination_name_ro:xpath:.//span[contains(text(),'${Destination}')]/../p
error_msg:xpath:.//div[contains(@class,'error')]//span[text()='${text}']
text_quantity:xpath://input[@id='${id}']
item_pagination:xpath:.//ul[@class='pagination']//span[27]/following::span
vertical_scroll:xpath:.//div[contains(@class,'vertical-scrollbar-present')]
no_destination:xpath:.//div[contains(text(),'When you select a destination, the orders for that choice will show here')]
first_col_data:xpath:.//div[@class='rt-tr-group']/child::div/div[${num}]
order_data_view_order:xpath:.//div[contains(text(),'${name}')]
destination_name:xpath:.//div[contains(@class,'isacard')]//button[text()='Void']/parent::div/div
ordred_quanity:xpath:.//div[text()='${item}']//following::div[4]
item_checkbox:xpath:.(//div[contains(text(),'${item}')]/../../div//label[contains(@for,'checkbox')])[2]
success_mesg:xpath://div[contains(text(),'${text}')]
item_quantity_po:xpath:xpath:.//div[contains(text(),'${item}')]/following::input[@name='toOrderQuantity']
item_list_po:xpath://div[contains(@class,'purchaseOrderDetailKey')]//div[contains(text(),'${item}')]
order_quantity_multiples:xpath:.//div[@class='rt-tr-group']/child::div/div[contains(text(),'${item}')]/following::div[%{num}]
item_desc:xpath:.//div[@role='columnheader']//div[text()='Item Description']/following::div[contains(@class,'rt-td') and contains(text(),'${item}')]
item_details_orderpage:xpath:.//div[@role='columnheader']//div[text()='Item Description']/following::div[contains(@class,'rt-td') and contains(text(),'${item}')]/following-sibling::div[${index}]
page_title:xpath:.//h3
right_panel:xpath://div[text()='Click items at left to add them to this shopping cart']
column_path:xpath://div[contains(@class,'left-section')]//div[@class='rt-tr-group']/child::div/div[${num}]
order_page_colum_data:xpath:.//div[contains(@class,'viewAllOrder-details')]//div[@class='rt-tr-group']/child::div/div[${num}]
text_button:xpath:.//button[text()='${text}']