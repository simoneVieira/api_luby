
package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.services.Autenticacao;
import com.example.demo.services.UsuarioService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SimoneBarbosa
 */
@RestController
//rota para cadastrar o usuário
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity cadastrarUsuario(@RequestBody Usuario usu) {

        usuarioService.registrarUsuario(usu);

        return new ResponseEntity(HttpStatus.CREATED);

    }
    //rota para autenticar o usuário, /usuario/autenticar.

    @RequestMapping(method = RequestMethod.POST, value = "/autenticar",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity autenticar(@RequestBody Usuario usu) {

        Usuario usuario = usuarioService.autenticarUsuario(usu);

        //Condicional para verificar as crendenciais do uruário.
        if (usuario == null || usuario.getLogin().equals("") || usuario.getSenha().equals("")) {
            return new ResponseEntity<>(usuario, HttpStatus.FORBIDDEN);
        }

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setSubject(usuario.getLogin());
        jwtBuilder.claim("autentica", usuario.getId());
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        jwtBuilder.signWith(Autenticacao.key);

        String token = jwtBuilder.compact();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        return new ResponseEntity<>(headers, HttpStatus.OK);

    }

}
