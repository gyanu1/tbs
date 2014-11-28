/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import mum.cs490.tbs.model.CallDetail;
import mum.cs490.tbs.model.CallingCodes;
import mum.cs490.tbs.model.CallingRate;
import mum.cs490.tbs.model.Customer;
import mum.cs490.tbs.model.PeakInfo;
import mum.cs490.tbs.model.Service;
import org.junit.Test;

/**
 *
 * @author puneetkhanal
 */
public class UpdateServiceTest extends BaseTestCase {

    @Test
    public void customerDaoTest() throws IOException {

        List<Service> services = new ArrayList<>();
        Reader reader = new Reader();

        reader.setReader(new PeakInfoReader());
        Map<String, List<PeakInfo>> data3 = reader.read("data/Peak.xls");

        for (PeakInfo peakInfo : data3.get("Sheet1")) {
            services.add(peakInfo.getPeakId().getService());
        }

        updateService.storeServices(services);

        updateService.storePeakInfo(data3);

         reader.setReader(new CustomerInfoReader());
        Map<String,List<Customer>> data4=reader.read("data/Customers.xls");
        
        
        updateService.storeCustomers(data4.get("Sheet1"));
      
        reader.setReader(new CallingCodeReader());
        Map<String,List<CallingCodes>> data=reader.read("data/calling_code.xls");
        updateService.storeCallingCodes(data);
        
        
        reader.setReader(new CallDetailsReader());
        Map<String,List<CallDetail>> data1=reader.read("data/Calls_Dec2013.xls");
        updateService.storeCallDetails(data1);
        
        System.out.println(data1.get("Dec_Calls").get(0));
        
        
        reader.setReader(new RateReader());
        Map<String,List<CallingRate>> data2=reader.read("data/Rates_20130901.xls");
        updateService.updateRate(data2);
        
        
       
    }

}
