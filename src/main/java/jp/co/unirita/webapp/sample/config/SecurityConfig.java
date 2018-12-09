package jp.co.unirita.webapp.sample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 認可の設定
        http.authorizeRequests()
                .antMatchers("/login", "/signup").permitAll()
                .anyRequest().authenticated()

                // ログイン設定
                .and().formLogin()
                .loginPage("/login")
                .failureHandler(new SampleAuthenticationFailureHandler())
                .defaultSuccessUrl("/")
                .usernameParameter("userName").passwordParameter("password")
                .permitAll()

                // ログアウト設定
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
                .logoutSuccessUrl("/login")
                .permitAll();
    }
}