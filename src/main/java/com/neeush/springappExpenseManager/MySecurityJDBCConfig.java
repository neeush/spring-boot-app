package com.neeush.springappExpenseManager;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class MySecurityJDBCConfig extends WebSecurityConfigurerAdapter {

	
	@Bean
	@Primary
	public DataSource dataSource() {
	    return DataSourceBuilder
	        .create()
	        .username("b12a4d75ebf773")
	        .password("a4ac8c89")
	        .url("jdbc:mysql://us-cdbr-iron-east-04.cleardb.net:3306/ad_b360b113133acf1")
	        .driverClassName("com.mysql.jdbc.Driver")
	        .build();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(dataSource()).usersByUsernameQuery("select username, password, enabled"
	            + " from users where username=?")
	        /*.authoritiesByUsernameQuery("select username, authority "
	            + "from authorities where username=?")*/
	        .passwordEncoder(new BCryptPasswordEncoder());
	}
	
	/*
	 * (non-Javadoc)
	 *String encoded=new BCryptPasswordEncoder().encode("admin@123");
		System.out.println(encoded);
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
           
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/authenticateTheUser") 
            //default value is login and this should match the login page post url
            .permitAll()
            .and()
        .logout()
            .permitAll();
		
		http.csrf().disable();
	}
	
	

}
