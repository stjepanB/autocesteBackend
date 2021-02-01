package diplomski.autoceste.services;

import diplomski.autoceste.forms.DiscountDto;
import diplomski.autoceste.models.Discount;
import diplomski.autoceste.models.PrivateUser;
import diplomski.autoceste.models.Vehicle;
import diplomski.autoceste.models.VehicleDiscountLabel;
import diplomski.autoceste.repositories.DiscountRepository;
import diplomski.autoceste.repositories.VehicleDiscountLabelRepository;
import diplomski.autoceste.util.NumberComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final VehicleDiscountLabelRepository vehicleDiscountLabelRepository;
    private final DiscountRepository discountRepository;
    private List<Discount> activeDiscounts;
    private NumberComparator comparator;

    @Autowired
    public DiscountServiceImpl(VehicleDiscountLabelRepository vehicleDiscountLabelRepository, DiscountRepository discountRepository, NumberComparator comparator) {
        this.vehicleDiscountLabelRepository = vehicleDiscountLabelRepository;
        this.discountRepository = discountRepository;
        this.activeDiscounts = discountRepository.findAll();
        this.comparator = comparator;
    }

    @Override
    public boolean addVehicleDiscountLabel(VehicleDiscountLabel label) {
        try {
            vehicleDiscountLabelRepository.save(label);
        } catch (DataIntegrityViolationException e) {
            return false;
        }
        return true;
    }

    public Set<Discount> findAllDiscountForPrivateUser(PrivateUser user) {
        return new HashSet<>();
    }

    public HashSet<Discount> findAllDiscountForVehicle(Vehicle vehicle) {

        List<Discount> applyDiscounts = new ArrayList<>();
        for (Discount d : activeDiscounts) {
            List<VehicleDiscountLabel> labels = findAllLabelsByNames(d.getLabels());
            for (VehicleDiscountLabel l : labels) {
                if (l.getCategory().equals(vehicle.getCategory())) {
                    switch (l.getOperation()) {
                        case NONE:
                            if ((boolean) vehicle.get(l.getName())) {
                                applyDiscounts.add(d);
                            }

                        case EQUALS:
                            Number param = (Number) vehicle.get(l.getName());
                            if (param.equals(l.getValue())) {
                                applyDiscounts.add(d);
                            }
                            break;
                        case MORE:
                            param = (Number) vehicle.get(l.getName());
                            if (comparator.compare(param, l.getValue()) > 0) {
                                applyDiscounts.add(d);
                            }
                            break;
                        case LESS:
                            param = (Number) vehicle.get(l.getName());
                            if (comparator.compare(param, l.getValue()) < 0) {
                                applyDiscounts.add(d);
                            }
                            break;
                        case PRESENT:
                            break;
                    }
                }
            }
        }

        return new HashSet(applyDiscounts);
    }

    @Override
    public List<VehicleDiscountLabel> getAllVehicleDiscountLabel() {
        return vehicleDiscountLabelRepository.findAll();
    }

    @Override
    public boolean addDiscount(DiscountDto dto) {
        Discount d = dto.toDiscount();
        d.setLabels(dto.getLabels());
        try {
            discountRepository.save(d);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private List<VehicleDiscountLabel> findAllLabelsByNames(List<String> labelNames) {
        List<VehicleDiscountLabel> labels = new ArrayList<>();
        for (String l : labelNames) {
            labels.addAll(vehicleDiscountLabelRepository.findAllByName(l));
        }
        return labels;
    }

    public List<DiscountDto> getDiscounts() {
        List<Discount> d = discountRepository.findAll();
        return d.stream().map(DiscountDto::new).collect(Collectors.toList());
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void checkForNewDiscounts() {
        this.activeDiscounts.addAll(discountRepository.findByStartDateLessThanAndEndDateGreaterThan(LocalDate.now(), LocalDate.now()));
    }
}
