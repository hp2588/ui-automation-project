package com.org.data.packageshareapi;


import static com.org.automation.utils.YamlReader.getData;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManagePrinterReceiving {
	String printerKey;
	String printerName;
	
	@Test
    public void createPrinter() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("opaqueToken"));
        args.put("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityKeyReceiving"));
        args.put("tenantKey", getData("IDM.tenantKey"));
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/packagesharefeature/manage_printer_receiving.feature", args, true);

        for (Entry<String, Object> entry : result.entrySet()) {
            if("printerKey".equals(entry.getKey())) {
            	printerKey = (String) entry.getValue();                
                TestDataPropertyReaderAndWriter.setProperty("PrinterKeyReceiving", printerKey);
            }
            else if("printerName".equals(entry.getKey())) {
            	printerName = (String) entry.getValue();
                TestDataPropertyReaderAndWriter.setProperty("PrinterNameReceiving", printerName);
            }
        }
    }    
}