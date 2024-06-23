package com.kucw.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public InMemoryUserDetailsManager userDetailsManager() {
    // 將帳密寫死在 spring boot 中
    // 常用在測試中
    UserDetails userTest1 =
        User.withUsername("test1").password("{noop}111").roles("ADMIN", "USER").build();

    UserDetails userTest2 = User.withUsername("test2").password("{noop}222").roles("USER").build();

    return new InMemoryUserDetailsManager(userTest1, userTest2);
  }
}
