/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.model.Tasks;
import com.example.demo.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SimoneBarbosa
 */
@Service
public class TasksService {

    //injeção de independencia. 
    @Autowired
    TasksRepository tasksRepository;

    //método que permite a inserção das tasks no banco.
    public void registrarTasks(Tasks tasks) {
        tasksRepository.save(tasks);
    }

    public Tasks buscaTasks(Long id) {
        return tasksRepository.findById(id).get();
    }

    public Tasks editarTasks(Tasks tarefa) {
        return tasksRepository.save(tarefa);
    }
    public void excluirTasks(Long id) {
        tasksRepository.deleteById(id);
    }
}
