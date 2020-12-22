package diplomski.autoceste.controllers;

import diplomski.autoceste.forms.VehicleDto;
import diplomski.autoceste.services.PrivateUserService;
import diplomski.autoceste.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
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
        Long privateUserId = privateUserService.getPrivateUserIdByEmail(userEmail);
        dto.setUserId(privateUserId);
        return vehicleService.addVehicle(dto) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }
}
