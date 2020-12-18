package diplomski.autoceste.controllers;

import diplomski.autoceste.forms.RegisterPrivateUserDto;
import diplomski.autoceste.services.PrivateUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin(origins = {"${allowed.origin.address}", "http://www.nekaMojaStranica.com"})
public class PrivateUserRegistrationController {

    private final PrivateUserServiceImpl service;

    @Autowired
    public PrivateUserRegistrationController(PrivateUserServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/register")
    public HttpStatus register (@RequestBody RegisterPrivateUserDto dto){

        return service.register((dto)) ? HttpStatus.OK : HttpStatus.CONFLICT;
    }
}
