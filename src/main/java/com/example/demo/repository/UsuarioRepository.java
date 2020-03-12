/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Usuario;
import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author SimoneBarbosa
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {
 public Usuario findByLoginAndSenha(@org.springframework.data.repository.query.Param("login")String email,@org.springframework.data.repository.query.Param("senha")String senha);
}
