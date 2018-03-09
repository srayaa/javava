package cn.javava.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/**/*.html",
                "/**/*.js",
                "/**/*.css",
                "/**/*.map",
                "/**/*.json",
                "/**/*.png");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/users", "/users/**",
                        "/rooms", "/rooms/**",
                        "/trades", "/trades/**",
                        "/shelves", "/shelves/**",
                        "/machines", "/machines/**",
                        "/games", "/games/**").permitAll();
    }
}
