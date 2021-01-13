package diplomski.autoceste.services;

import diplomski.autoceste.models.VehicleDiscountLabel;
import diplomski.autoceste.repositories.VehicleDiscountLabelRepository;
import org.springframework.dao.DataIntegrityViolationException;

public class DiscountServiceImpl {

    private VehicleDiscountLabelRepository vehicleDiscountLabelRepository;

    public boolean addVehicleDiscountLabel(VehicleDiscountLabel label) {
        try {
            vehicleDiscountLabelRepository.save(label);
        } catch (DataIntegrityViolationException e) {
            return false;
        }
        return true;
    }
}
