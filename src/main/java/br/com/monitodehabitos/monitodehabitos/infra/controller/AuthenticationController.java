package br.com.monitodehabitos.monitodehabitos.infra.controller;

import br.com.monitodehabitos.monitodehabitos.infra.controller.loginDto.LoginDto;
import br.com.monitodehabitos.monitodehabitos.infra.controller.loginDto.ResponseToken;
import br.com.monitodehabitos.monitodehabitos.infra.infras.security.TokenService;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity makeLogin(@RequestBody @Valid LoginDto data){
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication =  authenticationManager.authenticate(authenticationToken);
        var tokenJwt = tokenService.generateToken((UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new ResponseToken(tokenJwt));
    }
}
