package diplomski.autoceste.controllers;

import diplomski.autoceste.forms.VehicleDiscountLabelDto;
import diplomski.autoceste.models.VehicleDiscountLabel;
import diplomski.autoceste.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class DiscountController {

    private DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/discount/label")
    public HttpStatus addDiscountLabel(@RequestParam("type") String type, @RequestBody VehicleDiscountLabelDto dto) {
        if (type.equals("vehicle")) {
            return discountService.addVehicleDiscountLabel(dto.toVehicleDiscountLabel()) ?
                    HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } else if (type.equals("private")) {
            return HttpStatus.BAD_REQUEST;
        } else if (type.equals("organisation")) {
            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.BAD_REQUEST;
    }


    @GetMapping(value = "/discount/labels")
    public ResponseEntity<List<VehicleDiscountLabelDto>> getDiscountLabels() {

        List<VehicleDiscountLabel> retreived = discountService.getAllVehicleDiscountLabel();

        return ResponseEntity.of(Optional.of(
                retreived.stream()
                        .map(VehicleDiscountLabelDto::new)
                        .collect(Collectors.toList())));
    }

}
