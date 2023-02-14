package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 해당 파일로 시큐리티 활성화
@Configuration // IoC
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // super 지움 - 기존 시큐리티가 가지고 있는 기능이 비활성화 됨.
        http.authorizeRequests().antMatchers("/", "/user/**", "/image/**",
                "/subscribe/**", "/comment/**")
                .authenticated().anyRequest().permitAll()
                .and().formLogin().loginPage("/auth/signin").loginProcessingUrl("/auth/signin").defaultSuccessUrl("/");

    }
}
