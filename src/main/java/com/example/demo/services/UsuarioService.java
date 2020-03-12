/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SimoneBarbosa
 */
@Service
public class UsuarioService {
    //injeção de independencia. 
    @Autowired 
    UsuarioRepository usuarioRepository;
    
    //método que permite a inserção do usuário no banco.
     public void registrarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
     public Usuario autenticarUsuario(Usuario usu) {
 return usuarioRepository.findByLoginAndSenha(usu.getLogin(),usu.getSenha());
       
       
    }
    
}
