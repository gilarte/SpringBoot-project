package com.banco.banco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@SuppressWarnings("deprecation")
@SpringBootApplication
public class BancoApplication {

	/**
     * Punto de entrada principal de la aplicación.
     *
     * @param args los argumentos de la línea de comandos
     */
	public static void main(String[] args) {
		SpringApplication.run(BancoApplication.class, args);
	}
	
	/**
     * Configuración de seguridad web.
     */
	@Configuration
	@EnableWebSecurity
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
	                .antMatchers("/**").permitAll()
	                .anyRequest().authenticated()
	                .and()
	            .csrf().disable();
	    }
	}
}
