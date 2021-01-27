package diplomski.autoceste.controllers;

import diplomski.autoceste.forms.BillDto;
import diplomski.autoceste.forms.TripDto;
import diplomski.autoceste.services.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping("/bills")
    public ResponseEntity<List<BillDto>> getBillsForUser(Authentication authentication) {
        String email = authentication.getName();

        return ResponseEntity.of(
                Optional.of(
                        billingService.findBillsByPrivateUserEmail(email)
                                .stream()
                                .map(BillDto::new)
                                .collect(Collectors.toList())));
    }
}
