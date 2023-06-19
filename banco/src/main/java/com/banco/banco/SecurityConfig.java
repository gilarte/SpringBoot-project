package com.banco.banco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.banco.banco.handler.LoginSuccessHandler;
import com.banco.banco.services.JpaUserDetailsService;


@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	

	@Autowired
	private LoginSuccessHandler successHandler;
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/*.antMatchers("/ver/**").hasAnyRole("USER")*/
		/*.antMatchers("/uploads/**").hasAnyRole("USER")*/
		/*.antMatchers("/form/**").hasAnyRole("ADMIN")*/
		/*.antMatchers("/eliminar/**").hasAnyRole("ADMIN")*/
		/*.antMatchers("/factura/**").hasAnyRole("ADMIN")*/
/*
		http.
		authorizeRequests()
        .antMatchers("/", "/registro", "/login", "/exito").permitAll()
        .antMatchers("/secure").hasAuthority("ROLE_ADMIN") // Agrega esta línea para restringir el acceso a "/secure" solo a usuarios con el rol de administrador
        .anyRequest().authenticated()
        .and()
        .formLogin()
            .successHandler(successHandler)
            .loginPage("/login")
            .defaultSuccessUrl("/dashboard")
        .permitAll()
        .and()
        .logout().permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/error")
        .and()
        .logout()
            .logoutUrl("/logout")
            .permitAll();
*/
		http
	    .authorizeRequests()
	        .antMatchers("/", "/registro", "/login", "/exito").permitAll()
	        .antMatchers("/secure", "showCuentasBancariasViewAdmin").hasAuthority("ROLE_ADMIN")
	        .anyRequest().authenticated()
	        .and()
	    .formLogin()
	        .successHandler(successHandler)
	        .loginPage("/login")
	        .defaultSuccessUrl("/dashboard")
	        .permitAll()
	        .and()
	    .logout().permitAll()
	        .and()
	    .exceptionHandling().accessDeniedPage("/error")
	        .and()
	    .logout()
	        .logoutUrl("/logout")
	        .permitAll();

	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{
		build.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);

	}
}
