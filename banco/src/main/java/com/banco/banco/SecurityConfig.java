package com.banco.banco;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@Order(101)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
     * Configura la seguridad HTTP.
     *
     * @param http el objeto HttpSecurity para configurar
     * @throws Exception si ocurre algún error durante la configuración
     */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	    	.authorizeRequests()
	    	.antMatchers("/registration", "/login").permitAll()
	    	.antMatchers("/dashboard").authenticated()
	    	.anyRequest().authenticated()
	    	.and()
	    	.formLogin()
	    	.loginPage("/login")
	    	.defaultSuccessUrl("/dashboard")
	    	.permitAll()
	    	.and()
	    	.logout()
        	.logoutUrl("/logout")
        	.permitAll();
	}

    
	/**
     * Configura la autenticación.
     *
     * @param auth el objeto AuthenticationManagerBuilder para configurar
     * @throws Exception si ocurre algún error durante la configuración
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("admin").password("{noop}admin").roles("ADMIN");
    }
}

