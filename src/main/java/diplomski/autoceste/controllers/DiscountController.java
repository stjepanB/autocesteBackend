package diplomski.autoceste.controllers;

import diplomski.autoceste.forms.VehicleDiscountLabelDto;
import diplomski.autoceste.services.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscountController {

    private DiscountService discountService;

    @PostMapping("/discount/label")
    public HttpStatus addDiscountLabel(@PathVariable String type, @RequestBody VehicleDiscountLabelDto dto) {
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

}
