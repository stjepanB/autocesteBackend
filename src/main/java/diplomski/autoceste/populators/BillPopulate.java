package diplomski.autoceste.populators;

import diplomski.autoceste.models.PrivateUser;
import diplomski.autoceste.models.PrivateUserBill;
import diplomski.autoceste.services.BillingService;
import diplomski.autoceste.services.PrivateUserService;
import diplomski.autoceste.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;

@Service
public class BillPopulate {

    private BillingService service;
    private VehicleService vehicleService;
    private PrivateUserService userService;

    @Autowired
    public BillPopulate(BillingService service, VehicleService vehicleService, PrivateUserService userService) {
        this.service = service;
        this.vehicleService = vehicleService;
        this.userService = userService;
    }

    public void populate(){
        PrivateUserBill bill = new PrivateUserBill();
        PrivateUser user = userService.getPrivateUserByEmail("slavko@firma.com");
        bill.setAmount(123.23);
        bill.setDiscounts(new HashSet<>());
        bill.setPlateMark("ZG2222LL");
        bill.setVehicle(vehicleService.findByPlate("ZG2222LL"));
        bill.setPrivateUser(user);
        bill.setLocationExit("Zadar Zapad");
        bill.setRecordedTimeExit(LocalDateTime.of(2020,10,23,15,14));
        bill.setRecordedTimeEntry(LocalDateTime.of(2020,10,23,10,27));
        bill.setLocationEntry("Lučko");

        service.addBill(bill);
    }
}
