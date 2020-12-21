package diplomski.autoceste.controllers;

import diplomski.autoceste.forms.VehicleDto;
import diplomski.autoceste.jwt.JwtService;
import diplomski.autoceste.services.PrivateUserService;
import diplomski.autoceste.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
public class VehicleController {

    private VehicleService vehicleService;
    private PrivateUserService privateUserService;
    private JwtService jwtService;

    @Autowired
    public VehicleController(VehicleService vehicleService, PrivateUserService privateUserService, JwtService jwtService) {
        this.vehicleService = vehicleService;
        this.privateUserService = privateUserService;
        this.jwtService = jwtService;
    }

    @PostMapping(value = "/vehicle")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ORGANIZATION')")
    public HttpStatus registerVehicle(@RequestHeader Map<String, String> headers, @RequestBody VehicleDto dto) {
        String token = headers.get("Authorization");
        String userEmail = jwtService.getUserEmail(token);
        Long privateUserId = privateUserService.getPrivateUserIdByEmail(userEmail);
        dto.setUserId(privateUserId);
        vehicleService.addVehicle(dto);
        return vehicleService.addVehicle(dto) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }
}
