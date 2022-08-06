package com.edutecno.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig {
	//configuracion de usuarios usados en memoria
	@Bean
    InMemoryUserDetailsManager userDetailsService() {
		
        UserDetails user = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("admin"))
            .roles("ADMIN")
            .build();
        
        UserDetails user2 = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user,user2);
    }
	
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf().disable();
		http
		.authorizeRequests()//autorizando los request recibidos de los antMatchers y verificacion de rol
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/user/**").hasRole("USER")
		.antMatchers("/login")
		.permitAll()
		.anyRequest()//request que sea recibido a /login debe ser autenticado
		.authenticated()
		.and()
		.formLogin()//definiendo cual es la pagina inicial para el login
		.loginPage("/login")//URL para la pagina de login o inicio de sesión
		.failureUrl("/login?error=true")//URL para un login fallido
		.usernameParameter("user")//nombre del parametro del input en el formulario
		.passwordParameter("password")//nombre del parametro para el password en el input del formulario
		.defaultSuccessUrl("/default", true)
		.and()
		.exceptionHandling()//si ocurre un error de ingreso se ejecuta la pagina de recurso-prohibido
		.accessDeniedPage("/recurso-prohibido");
		
		http
		.logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login")
        .deleteCookies("JSESSIONID")
        .invalidateHttpSession(true);
				
		http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .invalidSessionUrl("/login");
		
		return http.build();
	}
	
	@Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }
	
	//metodo encargado de codificar la password
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
