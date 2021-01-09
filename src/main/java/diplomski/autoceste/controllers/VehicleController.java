package diplomski.autoceste.controllers;

import diplomski.autoceste.forms.VehicleDto;
import diplomski.autoceste.models.PrivateUser;
import diplomski.autoceste.models.Vehicle;
import diplomski.autoceste.services.PrivateUserService;
import diplomski.autoceste.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class VehicleController {

    private VehicleService vehicleService;
    private PrivateUserService privateUserService;

    @Autowired
    public VehicleController(VehicleService vehicleService, PrivateUserService privateUserService) {
        this.vehicleService = vehicleService;
        this.privateUserService = privateUserService;
    }

    @PostMapping(value = "/vehicle")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ORGANIZATION')")
    public HttpStatus registerVehicle(Authentication authentication, @RequestBody VehicleDto dto) {

        String userEmail = authentication.getName();
        if (authentication.getAuthorities().stream().filter(e -> e.getAuthority().equals("ROLE_USER")).count() == 1) {
            Long privateUserId = privateUserService.getPrivateUserIdByEmail(userEmail);
            dto.setUserId(privateUserId);
        } else {
            //TODO
        }

        return vehicleService.addVehicle(dto) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @GetMapping(value = "/vehicles")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ORGANIZATION')")
    public ResponseEntity<List<Vehicle>> getVehicles(Authentication authentication) {
        String email = authentication.getName();
        List<Vehicle> vehicles = null;
        if (authentication.getAuthorities().stream().filter(e -> e.getAuthority().equals("ROLE_USER")).count() == 1) {
            PrivateUser privateUser = privateUserService.getPrivateUserByEmail(email);
            vehicles = vehicleService.getVehiclesForPrivateUser(privateUser);
        }

        return ResponseEntity.of(Optional.ofNullable(vehicles));
    }
}
