package diplomski.autoceste.controllers;

import diplomski.autoceste.formsData.RegisterPrivateUserDto;
import diplomski.autoceste.service.PrivateUserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin(origins = {"${allowed.origin.address}", "http://www.nekaMojaStranica.com"})
public class PrivateUserRegistrationController {

    private final PrivateUserRegistrationService service;

    @Autowired
    public PrivateUserRegistrationController(PrivateUserRegistrationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public HttpStatus register (@RequestBody RegisterPrivateUserDto dto){

        return service.register((dto)) ? HttpStatus.OK : HttpStatus.CONFLICT;
    }
}
