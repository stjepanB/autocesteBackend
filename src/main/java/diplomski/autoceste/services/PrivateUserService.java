package diplomski.autoceste.services;

import diplomski.autoceste.forms.RegisterPrivateUserDto;

public interface PrivateUserService {

    boolean register(RegisterPrivateUserDto dto);
}
