package trellloii;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
//@EnableWebSecurity
@ComponentScan(basePackages = "trelloiii",basePackageClasses = BasicAuthConfiguration.class)

public class BasicAuthConfiguration {//extends WebSecurityConfigurerAdapter {

//    @Bean
//    public CommonsRequestLoggingFilter logFilter() {
//        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
//        filter.setIncludeQueryString(true);
//        filter.setIncludePayload(true);
//        filter.setMaxPayloadLength(100000);
//        filter.setIncludeHeaders(true);
//        filter.setAfterMessagePrefix("REQUEST DATA : ");
//        return filter;
//    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//                auth
//                .inMemoryAuthentication()
//                .withUser("ADMIN")
//                .password("{noop}root")
//                .roles("ADMIN");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http)
//            throws Exception {
//                http.csrf().disable().cors().configurationSource(httpServletRequest -> new CorsConfiguration().applyPermitDefaultValues()).and()
//                .authorizeRequests()
//                .antMatchers("/loginn").permitAll()
//                .anyRequest().hasRole("ADMIN").and().formLogin().disable()
////                .authenticated()
////                .and()
//                .httpBasic();
//    }
}
