package br.com.uniciv.minhastarefas.security;

import br.com.uniciv.minhastarefas.services.UserDetailsServiceImpl;
import br.com.uniciv.minhastarefas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Extende a classe OncePerRequestFilter, para que todas as requisições passem por ela.
@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String jwt = parseJwt(request);

        //Valida o token
        if (jwt != null & jwtUtils.validateJwsToken(jwt)) {

            String username = jwtUtils.getUserNameFromJwtToken(jwt);
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null,
                            userDetails.getAuthorities());

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //Seta a autenticação no contexto do spring
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    //Faz o parse do jwt
    private String parseJwt(HttpServletRequest request) {

        String headerAuth = request.getHeader("Authorization"); //pega o authorization do header

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer")) //Se tiver Bearer, faz o split
            return headerAuth.split(" ")[1];

        return null;
    }
}
