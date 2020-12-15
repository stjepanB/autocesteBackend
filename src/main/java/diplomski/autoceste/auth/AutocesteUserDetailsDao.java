package diplomski.autoceste.auth;

import diplomski.autoceste.auth.tutorial.ApplicationUser;

import java.util.Optional;

public interface AutocesteUserDetailsDao {

    Optional<AutocesteUserDetails> selectApplicationUserByEmail(String email);

}
