package diplomski.autoceste.services;

import diplomski.autoceste.forms.VehicleDto;
import diplomski.autoceste.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository repository;

    @Override
    public boolean addVehicle(VehicleDto vehicle) {

        return false;
    }
}
