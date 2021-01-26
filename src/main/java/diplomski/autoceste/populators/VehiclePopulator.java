package diplomski.autoceste.populators;

import diplomski.autoceste.forms.VehicleDto;
import diplomski.autoceste.models.PrivateUser;
import diplomski.autoceste.models.VehicleCategory;
import diplomski.autoceste.repositories.PrivateUserRepository;
import diplomski.autoceste.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiclePopulator {

    private PrivateUserRepository userRepository;
    private VehicleService service;

    @Autowired
    public VehiclePopulator(PrivateUserRepository userRepository, VehicleService service) {
        this.userRepository = userRepository;
        this.service = service;
    }

    public void populate() {

        List<PrivateUser> users = userRepository.findAll();
        PrivateUser user = users.stream().filter(e -> e.getEmail().equals("slavko@firma.com")).findFirst().get();
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
