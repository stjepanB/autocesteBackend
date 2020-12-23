package diplomski.autoceste.services;

import diplomski.autoceste.forms.VehicleDto;
import diplomski.autoceste.models.PrivateUser;
import diplomski.autoceste.models.Vehicle;

import java.util.List;

public interface VehicleService {

    boolean addVehicle(VehicleDto vehicle);

    List<Vehicle> getVehiclesForPrivateUser(PrivateUser privateUser);
}
