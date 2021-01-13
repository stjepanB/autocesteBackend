package diplomski.autoceste.services;

import diplomski.autoceste.forms.VehicleDto;
import diplomski.autoceste.forms.VehicleParameterDto;
import diplomski.autoceste.models.PrivateUser;
import diplomski.autoceste.models.Vehicle;
import diplomski.autoceste.models.VehicleCategory;
import diplomski.autoceste.repositories.UserRepository;
import diplomski.autoceste.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository repository;
    private UserRepository userRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean addVehicle(VehicleDto dto) {
        Vehicle v = new Vehicle();

        v.setCategory(VehicleCategory.valueOf(dto.getCategory()));
        v.setColor(dto.getColor());
        v.setType(dto.getType());
        v.setHeight(dto.getHeight());
        v.setManufacturer(dto.getManufacturer());
        v.setMaxWeightWithCargo(dto.getMaxWeight());
        v.setPlate(dto.getPlate());
        v.setPrivateUser(userRepository.findById(dto.getUserId()).get());
        v.setHasGreenCertificate(dto.getCertificate());

        try {
            repository.save(v);
        } catch (DataIntegrityViolationException e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Vehicle> getVehiclesForPrivateUser(PrivateUser privateUser) {
        List<Vehicle> vehicles = repository.findAllByPrivateUser(privateUser);
        return vehicles;
    }

    public List<VehicleParameterDto> getVehicleParams() {
        List<Object[]> result = repository.getColumnsWithTypes();
        return result.stream()
                .map(e -> Pair.of((String) e[0], (String) e[1]))
                .filter(e -> !e.getFirst().equals("id"))
                .filter(e -> !e.getFirst().equals("private_user_id"))
                .map(VehicleParameterDto::new)
                .collect(Collectors.toList());
    }
}
