/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Tasks;
import com.example.demo.services.TasksService;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SimoneBarbosa
 */
@RestController
// rota para cadastrar tarefas
@RequestMapping(value = "/tarefa")
public class TasksController {

    @Autowired
    TasksService tasksService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity cadastrarTasks(@RequestBody Tasks tas) {

        tasksService.registrarTasks(tas);

        return new ResponseEntity(HttpStatus.CREATED);

    }

    //codigo que faz as buscas no banco de dados com a rota: /tarefa/id.
    @RequestMapping(method = RequestMethod.GET,
            value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Tasks> mostraCliente(@PathVariable Long id) {

        Tasks tarefa;
        try {
            tarefa = tasksService.buscaTasks(id);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(tarefa, HttpStatus.OK);
    }

    // metodo para editar as tasks com a rota: /tarefa/editar
    @PutMapping("/editar")
    public Tasks editarTasks(@RequestBody Tasks tasks) {
        return tasksService.editarTasks(tasks);
    }
   //metodo para deletar as tasks com a rota: /tarefa/id
    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}")
    ResponseEntity removerTasks(@PathVariable Long id) {

        return new ResponseEntity(HttpStatus.OK);
    }
}
