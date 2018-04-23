package app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("--WebSecurityConfig--");
		http
            .authorizeRequests()
                .antMatchers("/", "/images/**", "/js/*", "/Scripts/*", "/styles/*", "/login*", "/signup*", "/proposal", "/admin", "/success*", "/promo")
				.permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.loginProcessingUrl("/postLoginA")
				// .defaultSuccessUrl("/greeting", true)
				.and()
			.logout().permitAll(true).logoutSuccessUrl("/login?logout");
	 
	   // // http.csrf().disable();
	   // // http.headers().frameOptions().disable();
    }
	
	// @Bean
    // @Override
    // public UserDetailsService userDetailsService() {
        // UserDetails user =
             // User.withDefaultPasswordEncoder()
                // .username("user")
                // .password("password")
                // .roles("USER")
                // .build();
				
	// System.out.println("--UserDetailsService--" + user.getUsername());
        // return new InMemoryUserDetailsManager(user);
    // }
}