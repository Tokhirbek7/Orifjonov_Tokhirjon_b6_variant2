package uz.pdp.appexam_demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static uz.pdp.appexam_demo.permissionsAndRoles.ApplicationUserRole.ADMIN;
import static uz.pdp.appexam_demo.permissionsAndRoles.ApplicationUserRole.USER;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;




    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth

                .inMemoryAuthentication()
                .withUser("Toby").password(passwordEncoder.encode("123")).roles(String.valueOf(ADMIN)).authorities("" +
                        "READ_ALL", "EDIT", "ADD", "READ_BY_ID","EDIT")
                .and()
                .withUser("alder").password(passwordEncoder.encode("1234")).roles(String.valueOf(ADMIN)).authorities( "ADD" );
//
    }

    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails toby = User.builder()
                .username("Toby")
                .password(passwordEncoder.encode("123"))
                .roles(String.valueOf(ADMIN)) // ROLE_ADMIN;
                .build();
        UserDetails alder = User.builder()
                .username("alder")
                .password(passwordEncoder.encode("1234"))
                .roles(String.valueOf(ADMIN)) // ROLE_ADMIN;
                .build();
        UserDetails wick = User.builder()
                .username("wick")
                .password(passwordEncoder.encode("1234"))
                .roles(String.valueOf(USER)) // USER
                .build();
        return new InMemoryUserDetailsManager(
                toby,
                wick,
                alder
        );
    }
}
