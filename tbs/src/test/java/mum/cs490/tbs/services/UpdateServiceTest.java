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
import mum.cs490.tbs.model.CallingCodes;
import mum.cs490.tbs.model.Customer;
import mum.cs490.tbs.model.PeakInfo;
import mum.cs490.tbs.model.Service;
import mum.cs490.tbs.model.TbsUser;
import mum.cs490.tbs.model.UserRole;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author puneetkhanal
 */
public class UpdateServiceTest extends BaseTestCase {

    @BeforeClass
    public static void beforeClass() {

    }

    @Test
    public void customerDaoTest() throws IOException {

        UserRole role = new UserRole("ROLE_ADMIN");
        userDao.saveUserRole(role);
        TbsUser user = new TbsUser("admin", encoder.encode("admin123"));
        user.setId(1000L);
        user.setUserRole(role);
        userDao.saveUser(user);

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
        Map<String, List<Customer>> data4 = reader.read("data/Customers.xls");

        updateService.storeCustomers(data4.get("Sheet1"));

        reader.setReader(new CallingCodeReader());
        Map<String, List<CallingCodes>> data = reader.read("data/calling_code.xls");
        updateService.storeCallingCodes(data);

//        reader.setReader(new CallDetailsReader());
//        Map<String,List<CallDetail>> data1=reader.read("data/Calls_Dec2013.xls");
//        updateService.storeCallDetails(data1);
//        reader.setReader(new CallDetailsReader());
//        Map<String,List<CallDetail>> data5=reader.read("data/Calls_Dec2013.xls");
//        updateService.storeCallDetails(data5);
//        
//        System.out.println(data1.get("Dec_Calls").get(0));
//        
//        
//        reader.setReader(new RateReader());
//        Map<String,List<CallingRate>> data2=reader.read("data/Rates_20130901.xls");
//        updateService.updateRate(data2);
//        reader.setReader(new RateReader());
//   
//        Map<String,List<CallingRate>> data5=reader.read("data/Rates_20131215.xls");
//        updateService.updateRate(data5);
    }

}
