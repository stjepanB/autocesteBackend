package diplomski.autoceste.auth;


import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutocesteUserDetailsDaoService  implements AutocesteUserDetailsDao{

    @Override
    public Optional<AutocesteUserDetails> selectApplicationUserByEmail(String email) {
        return Optional.empty();
    }
}
