package diplomski.autoceste.services;

import diplomski.autoceste.forms.TripDto;
import diplomski.autoceste.models.Discount;
import diplomski.autoceste.models.HighwaySection;
import diplomski.autoceste.models.PrivateUserBill;
import diplomski.autoceste.models.VehicleCategory;
import diplomski.autoceste.repositories.PrivateUserBillRepository;
import diplomski.autoceste.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BillingServiceImpl implements BillingService {

    private PrivateUserBillRepository repository;
    private VehicleRepository vehicleRepository;
    private DiscountService discountService;
    private HighwaySectionService sectionService;

    @Autowired
    public BillingServiceImpl(PrivateUserBillRepository repository, VehicleRepository vehicleRepository, DiscountService discountService, HighwaySectionService sectionService) {
        this.repository = repository;
        this.vehicleRepository = vehicleRepository;
        this.discountService = discountService;
        this.sectionService = sectionService;
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
        b.setVehicle(vehicleRepository.findByPlate(dto.getPlateMark()));
        b.setDiscounts(discountService.findAllDiscountForVehicle(b.getVehicle()));
        b.setDiscounts(discountService.findAllDiscountForPrivateUser(b.getPrivateUser()));
        b.setAmount(calculatePrice(sectionService.getHighwaySections(b.getLocationEntry(), b.getLocationExit()), b.getDiscounts(), b.getVehicle().getCategory()));
        return b;
    }

    public double calculatePrice(List<HighwaySection> highwaySections, Set<Discount> discounts, VehicleCategory category) {
        double totDiscount = (double) discounts.stream()
                .map(Discount::getPercentage)
                .reduce(0.0F, Float::sum);
        double amount = 0.0;

        for (HighwaySection s : highwaySections) {
            switch (category) {
                case I:
                    amount += (s.getInfrastructureCostI() - totDiscount * s.getInfrastructureCostI() + s.getOutsideCostI()) * s.getDistance();

            }
        }
    }
}
