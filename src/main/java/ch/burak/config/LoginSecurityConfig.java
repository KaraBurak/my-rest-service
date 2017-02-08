package ch.burak.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Burak Kara
 */
@Configuration
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests().antMatchers("/customerForm","/customerForm.html").authenticated().anyRequest().authenticated();
//        http.authorizeRequests().antMatchers("/**").authenticated().anyRequest().authenticated(); <--- mit bootstrap funktionieren wäre ziel but nooooooo it doesnt
        http.formLogin().loginPage("/login").permitAll().and().logout().permitAll();

        http.authorizeRequests().antMatchers("/css/**", "/js/**", "/templates/**","/images/**","/webjars**","/resources**").permitAll().anyRequest().permitAll();
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/index","/index.html","/css/**", "/js/**", "/templates/**","/resources/**","webjars/bootstrap/3.3.7-1/css/bootstrap-jsf.css","/webjars");
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("b").password("b").roles("USER");
    }

}
