package in.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean 
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(request->
		 request.requestMatchers("/admin/**").hasRole("ADMIN")
		.requestMatchers(HttpMethod.GET).hasAuthority("READ")
		.requestMatchers(HttpMethod.POST).hasAuthority("CREATE")
		.requestMatchers(HttpMethod.PUT).hasAuthority("UPDATE")
		.requestMatchers(HttpMethod.DELETE).hasAuthority("DELETE")
		
//		.requestMatchers("/product/**").hasAnyRole("ADMIN","SELLER","CUSTOMER")
//		.requestMatchers(HttpMethod.GET).hasAuthority("READ")
//		.requestMatchers(HttpMethod.POST).hasAuthority("CREATE")
//		.requestMatchers(HttpMethod.PUT).hasAuthority("UPDATE")
//		.requestMatchers(HttpMethod.DELETE).hasAuthority("DELETE")
		)
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	
}
