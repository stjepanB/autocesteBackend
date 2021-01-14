package diplomski.autoceste.services;

import diplomski.autoceste.models.VehicleDiscountLabel;

import java.util.List;

public interface DiscountService {

    boolean addVehicleDiscountLabel(VehicleDiscountLabel label);

    List<VehicleDiscountLabel> getAllVehicleDiscountLabel();

}
