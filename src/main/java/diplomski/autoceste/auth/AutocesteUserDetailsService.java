package diplomski.autoceste.auth;

import diplomski.autoceste.model.User;
import diplomski.autoceste.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AutocesteUserDetailsService implements UserDetailsService {

    private AutocesteUserDetailsDao autocesteUserDetailsDao;

    @Autowired
    public AutocesteUserDetailsService(AutocesteUserDetailsDao autocesteUserDetailsDao) {
        this.autocesteUserDetailsDao = autocesteUserDetailsDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return autocesteUserDetailsDao.selectApplicationUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Email %s not found.", email)));
    }
}
