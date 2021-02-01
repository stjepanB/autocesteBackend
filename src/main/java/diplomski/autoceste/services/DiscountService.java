package diplomski.autoceste.services;

import diplomski.autoceste.forms.DiscountDto;
import diplomski.autoceste.models.Discount;
import diplomski.autoceste.models.PrivateUser;
import diplomski.autoceste.models.Vehicle;
import diplomski.autoceste.models.VehicleDiscountLabel;

import java.util.List;
import java.util.Set;

public interface DiscountService {

    boolean addVehicleDiscountLabel(VehicleDiscountLabel label);

    List<VehicleDiscountLabel> getAllVehicleDiscountLabel();

    boolean addDiscount(DiscountDto dto);

    List<DiscountDto> getDiscounts();

    Set<Discount> findAllDiscountForPrivateUser(PrivateUser user);

    Set<Discount> findAllDiscountForVehicle(Vehicle vehicle);

}
