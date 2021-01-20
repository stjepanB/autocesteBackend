package diplomski.autoceste.services;

import diplomski.autoceste.forms.TripDto;

import java.util.List;

public interface BillingService {
    boolean createBills(List<TripDto> trips);
}
