package diplomski.autoceste.services;

import diplomski.autoceste.forms.DiscountDto;
import diplomski.autoceste.models.VehicleDiscountLabel;

import java.util.List;

public interface DiscountService {

    boolean addVehicleDiscountLabel(VehicleDiscountLabel label);

    List<VehicleDiscountLabel> getAllVehicleDiscountLabel();

    boolean addDiscount(DiscountDto dto);

    List<DiscountDto> getDiscounts();

}
