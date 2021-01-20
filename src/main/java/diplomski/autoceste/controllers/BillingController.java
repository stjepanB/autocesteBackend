package diplomski.autoceste.controllers;

import diplomski.autoceste.forms.TripDto;
import diplomski.autoceste.services.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BillingController {

    private BillingService billingService;

    @Autowired
    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping("/service/billing")
    public ResponseEntity createBills(@RequestBody List<TripDto> trips) {
        return billingService.createBills(trips) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
