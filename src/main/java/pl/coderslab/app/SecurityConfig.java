package pl.coderslab.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import pl.coderslab.service.SpringDataUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(new EncodingFilter(), ChannelProcessingFilter.class);

        http.authorizeRequests()
                .antMatchers("/home").permitAll()
                .antMatchers("/delivery/**", "/route/**").authenticated()
                .and().formLogin().loginPage("/user/login").defaultSuccessUrl("/home")
                .and().logout().logoutSuccessUrl("/user/login")
                .permitAll();
    }
}
