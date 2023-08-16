package ru.job4j.accidents.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

/**
 * Класс для конфигурации Spring Security без WebSecurityConfigurerAdapter
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class NewSecurityConfig {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    DataSource ds;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Метод содержит описание, как искать пользователей.
     * У каждого пользователя есть роль. По роли мы определяем, что пользователь может делать.
     * Запросы авторизации и аутентификации.
     *
     * @return UserDetailsManager
     */
    @Bean
    public UserDetailsManager authenticateUsers() {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(ds);
        users.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
        users.setAuthoritiesByUsernameQuery("SELECT u.username, a.authority "
                + "FROM authorities a, users u "
                + "WHERE u.username = ? AND u.authority_id = a.id");
        return users;
    }

    /**
     * Метод содержит описание доступов и конфигурирование страницы входа в приложение.
     * Ссылки, которые доступны всем:
     * .antMatchers("/login")
     * .permitAll()
     * Ссылки доступны только пользователем с ролями ADMIN, USER:
     * .antMatchers("/**")
     * .hasAnyRole("ADMIN", "USER")
     * Настройка формы авторизации:
     * .formLogin()
     * .loginPage("/login")
     * .defaultSuccessUrl("/")
     * .failureUrl("/login?error=true")
     * .permitAll()
     * Отключаем защиту от межсайтовой подделки запросов
     * .csrf().disable();
     *
     * @param http HttpSecurity
     * @throws Exception исключение
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/reg")
                .permitAll()
                .antMatchers("/**")
                .hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf()
                .disable();
        return http.build();
    }

    /**
     * Пропускаем (не блокируем) картинки и логотипы оформления стартовых страниц.
     *
     * @return WebSecurityCustomizer
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/css/**", "/js/**", "/images/logo/**");
    }
}
