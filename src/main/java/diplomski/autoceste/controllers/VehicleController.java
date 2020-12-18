package diplomski.autoceste.controllers;

import diplomski.autoceste.forms.VehicleDto;
import diplomski.autoceste.jwt.JwtService;
import diplomski.autoceste.services.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController(value = "/vehicle")
public class VehicleController {

    private VehicleService vehicleService;
    private JwtService jwtService;

    @PostMapping("/register")
    public HttpStatus registerVehicle(@RequestHeader Map<String, String> headers, @RequestBody VehicleDto dto) {

        return HttpStatus.OK;
    }
}
