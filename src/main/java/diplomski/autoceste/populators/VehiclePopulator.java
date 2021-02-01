package diplomski.autoceste.populators;

import diplomski.autoceste.forms.VehicleDto;
import diplomski.autoceste.models.PrivateUser;
import diplomski.autoceste.models.VehicleCategory;
import diplomski.autoceste.services.PrivateUserService;
import diplomski.autoceste.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiclePopulator {

    private PrivateUserService userService;
    private VehicleService service;

    @Autowired
    public VehiclePopulator(PrivateUserService userService, VehicleService service) {
        this.userService = userService;
        this.service = service;
    }

    public void populate() {

        PrivateUser user = userService.getPrivateUserByEmail("slavko@firma.com");
        VehicleDto v = new VehicleDto();
        v.setPlate("ZG2222LL");
        v.setCertificate(true);
        v.setUserId(user.getId());
        v.setHeight(1233);
        v.setManufacturer("BMW");
        v.setColor("crni");
        v.setType("M1");
        v.setCategory(VehicleCategory.I.toString());
        v.setMaxWeight(1233);

        service.addVehicle(v);
    }
}
