package diplomski.autoceste.services;

import diplomski.autoceste.forms.TripDto;
import diplomski.autoceste.models.PrivateUserBill;

import java.util.List;

public interface BillingService {
    boolean createBills(List<TripDto> trips);

    List<PrivateUserBill> findBillsByPrivateUserEmail(String email);

    void addBill(PrivateUserBill bill);
}
