package br.com.uniciv.minhastarefas.security;

import br.com.uniciv.minhastarefas.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

//Configuração para autenticação de auth
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Define se o usuario deve estar autenticado
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PATHS = new String[] {"/tarefa/**", "/categoria/**", "/usuario/**"};

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPoint unauthorizedHandler;

    @Bean // Pois será utilizado em outras partes do sistema
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder(); //Gera o hash para  senha
    }

    @Bean // Cria um bean para o jwt
    public AuthTokenFilter authenticationJwtTokeFilter() {

        return new AuthTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return super.authenticationManagerBean();
    }

    //Autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //Passando ao autenticar pelo banco de dados e não mais da memoria
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

        //Configurando autenticação em memonria
        /*auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("usuario")
                .password(passwordEncoder().encode("senha"))
                .roles("USER");*/
        //super.configure(auth);
    }

    //Autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf()
                .disable()
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll() //define tipo de permissão
                .antMatchers(HttpMethod.POST, PATHS) //Define url que terão autenticação
                .hasRole("ADMIN") // Tipo de permissão
                .antMatchers(HttpMethod.PUT, PATHS)
                .hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, PATHS)
                .hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, PATHS)
                .hasAnyRole("ADMIN", "USER")
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                //.httpBasic();
                .addFilterBefore(authenticationJwtTokeFilter(), UsernamePasswordAuthenticationFilter.class) //Add filter para usar o token jwt
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler);//Chama a classse de tratativa de excessão
    }

    //Criação do bean para utilização do cors
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8081"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
