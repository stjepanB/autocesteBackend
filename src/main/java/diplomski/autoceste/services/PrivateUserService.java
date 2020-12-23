package diplomski.autoceste.services;

import diplomski.autoceste.forms.RegisterPrivateUserDto;
import diplomski.autoceste.models.PrivateUser;

public interface PrivateUserService {

    boolean register(RegisterPrivateUserDto dto);

    Long getPrivateUserIdByEmail(String email);

    PrivateUser getPrivateUserByEmail(String email);
}
