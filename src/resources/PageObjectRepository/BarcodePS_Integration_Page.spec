Page Title: Pyxis Logistics

#Object Definitions
======================================================================================================================================
barcodeLabel:xpath://label[text()='Scan or enter barcode']
searchBarcodeField:xpath://input[@id='barcodeValue']
hitsearchButton:xpath://button[@type='submit']
barcodeNumber:xpath://input[@value='${barcodeValue}']
parseProductID:xpath:(//label[text()='Parsed Product ID']/following::p)[1]
parse-ID:xpath://p[text()='-']
productID:xpath://label[text()='Product ID']
externalSystemDropDown:xpath://select[@name='pisDropdown']
linkedItemHeader:xpath://label[text()='Linked Item']
barcodeHeader:xpath://label[text()='Barcode']
linkedByDetails:xpath://label[text()='Linked By']
linkSourceHeader:xpath://label[text()='Link Source']
linkSourceDetails:xpath://p[text()='Local system (BD Pyxis TM Logistics)']
itemIDDetails:xpath://label[text()='Item ID']
verifyByDetails:xpath://label[text()='Verified By']
matchOnDetails:xpath://label[text()='Matched On']
======================================================================================================================================