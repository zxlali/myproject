package com.zxl.test.myproject.config;

import com.google.common.collect.Lists;
import com.zxl.test.myproject.service.DTAuthenticationProcessingFilter;
import com.zxl.test.myproject.service.DTAuthenticationProvider;
import com.zxl.test.myproject.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.SystemWideSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * Created by Alex on 17/6/2 下午7:35.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .and().formLogin().loginPage("/login")
            .permitAll().passwordParameter("Password")
            .usernameParameter("UserName").loginProcessingUrl("/login")
            .failureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error=true"))
            .successHandler(new SimpleUrlAuthenticationSuccessHandler("/index"))
        .and().logout().logoutSuccessUrl("/login").logoutUrl("/logout")
        .and().sessionManagement().sessionAuthenticationErrorUrl("/login")
            .invalidSessionUrl("/login").maximumSessions(1).expiredUrl("/login")
        .and().and().exceptionHandling().accessDeniedPage("/404")

        .and().addFilterBefore(dtAuthenticationProcessingFilter(), FilterSecurityInterceptor.class)
        ;
        http.csrf().disable();
    }

    @Override public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**/*.js", "/**/*.css", "/**/*.png", "/**/*.ico");
    }

    @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(dtAuthenticationProvider());
    }

    @Bean
    public DTAuthenticationProcessingFilter dtAuthenticationProcessingFilter() throws Exception {
        return new DTAuthenticationProcessingFilter(new ProviderManager(Lists.newArrayList(dtAuthenticationProvider())));
    }

    @Bean
    public SystemWideSaltSource systemWideSaltSource() {
        SystemWideSaltSource systemWideSaltSource = new SystemWideSaltSource();
        systemWideSaltSource.setSystemWideSalt("salt-");
        return systemWideSaltSource;
    }

    @Bean
    public DTAuthenticationProvider dtAuthenticationProvider() {
        DTAuthenticationProvider dtAuthenticationProvider = new DTAuthenticationProvider();
        dtAuthenticationProvider.setPasswordEncoder(md5PasswordEncoder());
        dtAuthenticationProvider.setSaltSource(systemWideSaltSource());
        dtAuthenticationProvider.setUserDetailsService(userDetailService());
        dtAuthenticationProvider.setHideUserNotFoundExceptions(false);
        return dtAuthenticationProvider;
    }

    @Bean
    public UserDetailService userDetailService() {
        return new UserDetailService();
    }

    @Bean
    public Md5PasswordEncoder md5PasswordEncoder() {
        return new Md5PasswordEncoder();
    }
}
