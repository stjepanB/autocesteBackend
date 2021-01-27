package diplomski.autoceste.services;

import diplomski.autoceste.forms.TripDto;
import diplomski.autoceste.models.*;
import diplomski.autoceste.repositories.PrivateUserBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BillingServiceImpl implements BillingService {

    private PrivateUserBillRepository repository;
    private ReportService reportService;
    private VehicleService vehicleService;
    private DiscountService discountService;
    private HighwaySectionService sectionService;
    private PrivateUserService userService;

    @Autowired
    public BillingServiceImpl(PrivateUserBillRepository repository, ReportService reportService,
                              VehicleService vehicleService, DiscountService discountService, HighwaySectionService sectionService, PrivateUserService userService) {
        this.repository = repository;
        this.reportService = reportService;
        this.vehicleService = vehicleService;
        this.discountService = discountService;
        this.sectionService = sectionService;
        this.userService = userService;
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

    @Override
    public List<PrivateUserBill> findBillsByPrivateUserEmail(String email) {
        PrivateUser user = userService.getPrivateUserByEmail(email);
        return repository.findAllByPrivateUser(user);
    }

    @Override
    public void addBill(PrivateUserBill bill) {
        repository.save(bill);
    }

    private PrivateUserBill tripToBill(TripDto dto) {
        PrivateUserBill b = new PrivateUserBill();
        try {
            b.setPrivateUser(vehicleService.findByPlate(dto.getPlateMark()).getPrivateUser());
            b.setVehicle(vehicleService.findByPlate(dto.getPlateMark()));
        } catch (NullPointerException e) {

            boolean isSuccess = reportService.addReport(dto.getPlateMark(), dto.getRecordedTimeEntry(),
                    dto.getRecordedTimeExit(), dto.getLocationEntry(), dto.getLocationExit());

            if (isSuccess) {
                throw new DataIntegrityViolationException("Illegal costumer");
            }
            throw new DataIntegrityViolationException("Illegal costumer, report save failed.");
        }

        b.setLocationEntry(dto.getLocationEntry());
        b.setLocationExit(dto.getLocationExit());
        b.setPlateMark(dto.getPlateMark());
        b.setRecordedTimeEntry(dto.getRecordedTimeEntry());
        b.setRecordedTimeExit(dto.getRecordedTimeExit());
        b.setDiscounts(discountService.findAllDiscountForVehicle(b.getVehicle()));
        b.setDiscounts(discountService.findAllDiscountForPrivateUser(b.getPrivateUser()));
        b.setAmount(calculatePrice(sectionService.getHighwaySections(dto.getLocations()), b.getDiscounts(), b.getVehicle().getCategory()));
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
                    amount += (s.getInfrastructureCostI() - totDiscount * s.getInfrastructureCostI() +
                            s.getOutsideCostI()) * s.getDistance();
                    break;
                case IA:
                    amount += (s.getInfrastructureCostIA() - totDiscount * s.getInfrastructureCostIA() +
                            s.getOutsideCostIA()) * s.getDistance();
                    break;
                case II:
                    amount += (s.getInfrastructureCostII() - totDiscount * s.getInfrastructureCostII() +
                            s.getOutsideCostII()) * s.getDistance();
                    break;
                case III:
                    amount += (s.getInfrastructureCostIII() - totDiscount * s.getInfrastructureCostIII() +
                            s.getOutsideCostIII()) * s.getDistance();
                    break;
                case IV:
                    amount += (s.getInfrastructureCostIV() - totDiscount * s.getInfrastructureCostIV() +
                            s.getOutsideCostIV()) * s.getDistance();
                    break;
            }
        }

        return amount;
    }
}
