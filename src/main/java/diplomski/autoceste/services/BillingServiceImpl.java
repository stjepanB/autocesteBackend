package diplomski.autoceste.services;

import diplomski.autoceste.forms.TripDto;
import diplomski.autoceste.models.PrivateUserBill;
import diplomski.autoceste.repositories.PrivateUserBillRepository;
import diplomski.autoceste.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillingServiceImpl implements BillingService {

    private PrivateUserBillRepository repository;
    private VehicleRepository vehicleRepository;

    @Autowired
    public BillingServiceImpl(PrivateUserBillRepository repository, VehicleRepository vehicleRepository) {
        this.repository = repository;
        this.vehicleRepository = vehicleRepository;
    }

    public boolean createBills(List<TripDto> trips) {
        try {
            repository.saveAll(
                    trips.stream()
                            .map(this::tripToBill)
                            .collect(Collectors.toList()));
        } catch (DataIntegrityViolationException e) {
            return false;
        }
        return true;
    }

    private PrivateUserBill tripToBill(TripDto dto) {
        PrivateUserBill b = new PrivateUserBill();
        b.setDirection(dto.getDirection());
        b.setLocationEntry(dto.getLocationEntry());
        b.setLocationExit(dto.getLocationExit());
        b.setPlateMark(dto.getPlateMark());
        b.setRecordedTimeEntry(dto.getRecordedTimeEntry());
        b.setRecordedTimeExit(dto.getRecordedTimeExit());
        b.setPrivateUser(vehicleRepository.findByPlate(dto.getPlateMark()).getPrivateUser());

        return b;
    }
}
