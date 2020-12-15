package diplomski.autoceste.security.tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import diplomski.autoceste.auth.tutorial.ApplicationUserService;
import diplomski.autoceste.jwt.JwtConfig;
import diplomski.autoceste.jwt.JwtTokenVerifier;
import diplomski.autoceste.jwt.JwtUsernameAndPasswordAuthenticationFilter;

import javax.crypto.SecretKey;

import static diplomski.autoceste.security.tutorial.ApplicationUserRole.*;

//@Configuration
//@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;
    private SecretKey secretKey;
    private JwtConfig jwtConfig;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService, SecretKey secretKey, JwtConfig jwtConfig) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(),jwtConfig,secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/api/**").hasRole(USER.name())
                .anyRequest()
                .authenticated();
    }

    @Override
    @Bean
    //defines how you retreive users from DB
    protected UserDetailsService userDetailsService() {
        UserDetails slavkoUser = User.builder()
                .username("slavko")
                .password(passwordEncoder.encode("password"))
                // .roles(STUDENT.name()) //ROLE_STUDENT
                .authorities(USER.getGrauntedAuthorities())
                .build();

        UserDetails zdeslav = User.builder()
                .username("zdeslav")
                .password(passwordEncoder.encode("password"))
                // .roles(ADMIN.name()) //change with authorities
                .authorities(ADMIN.getGrauntedAuthorities())
                .build();

        UserDetails miroslav = User.builder()
                .username("miroslav")
                .password(passwordEncoder.encode("password"))
                //.roles(ADMINTRAINEE.name()) //ROLE_ADMINTRAINEE
                .authorities(ADMIN.getGrauntedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(
                slavkoUser,
                zdeslav,
                miroslav
        );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }
}
