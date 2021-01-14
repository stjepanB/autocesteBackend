package diplomski.autoceste.services;

import diplomski.autoceste.models.VehicleDiscountLabel;
import diplomski.autoceste.repositories.VehicleDiscountLabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    private VehicleDiscountLabelRepository vehicleDiscountLabelRepository;

    @Autowired
    public DiscountServiceImpl(VehicleDiscountLabelRepository vehicleDiscountLabelRepository) {
        this.vehicleDiscountLabelRepository = vehicleDiscountLabelRepository;
    }

    public boolean addVehicleDiscountLabel(VehicleDiscountLabel label) {
        try {
            vehicleDiscountLabelRepository.save(label);
        } catch (DataIntegrityViolationException e) {
            return false;
        }
        return true;
    }

    @Override
    public List<VehicleDiscountLabel> getAllVehicleDiscountLabel() {
        return vehicleDiscountLabelRepository.findAll();
    }
}
