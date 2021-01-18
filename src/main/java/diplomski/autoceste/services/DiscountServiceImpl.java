package diplomski.autoceste.services;

import diplomski.autoceste.forms.DiscountDto;
import diplomski.autoceste.models.Discount;
import diplomski.autoceste.models.VehicleDiscountLabel;
import diplomski.autoceste.repositories.DiscountRepository;
import diplomski.autoceste.repositories.VehicleDiscountLabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {

    private VehicleDiscountLabelRepository vehicleDiscountLabelRepository;
    private DiscountRepository discountRepository;

    @Autowired
    public DiscountServiceImpl(VehicleDiscountLabelRepository vehicleDiscountLabelRepository, DiscountRepository discountRepository) {
        this.vehicleDiscountLabelRepository = vehicleDiscountLabelRepository;
        this.discountRepository = discountRepository;
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
}
