package by.neon.tour.config;

import by.neon.tour.config.jwt.JwtAuthenticationEntryPoint;
import by.neon.tour.config.jwt.JwtAuthenticationFilter;
import by.neon.tour.config.jwt.JwtLoginFilter;
import by.neon.tour.config.jwt.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;

/**
 * @author Nikolay Moskal
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	@Autowired
	private JwtTokenUtils tokenUtils;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
				.authoritiesByUsernameQuery("SELECT username, role FROM roles WHERE username = ?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors().and()
			.csrf().disable()
				.formLogin()
				  	.loginPage("/login")
				  	.permitAll()
				  	.and()
				.logout()
					.logoutSuccessUrl("/login?logout")
					.permitAll()
					.and()
				.exceptionHandling()
				  	.accessDeniedPage("/403")
				  	.authenticationEntryPoint(entryPoint)
				  	.and()
				.authorizeRequests()
					.antMatchers("/user/signup").permitAll()
					.antMatchers("/client/signup").permitAll()
					.antMatchers("/tour/**").permitAll()
				.antMatchers("/user/**").hasRole("ADMIN")
				.antMatchers("/client/**").hasRole("ADMIN")
					.anyRequest().authenticated()
					.and()
				.addFilterBefore(new JwtLoginFilter("/login", authenticationManager(), tokenUtils),
						UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JwtAuthenticationFilter(tokenUtils), 
						UsernamePasswordAuthenticationFilter.class)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.headers().cacheControl();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
	}
}
