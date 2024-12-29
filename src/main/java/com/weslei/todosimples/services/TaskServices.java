package com.weslei.todosimples.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weslei.todosimples.models.Task;
import com.weslei.todosimples.models.User;
import com.weslei.todosimples.repositories.TaskRepository;
import com.weslei.todosimples.services.exceptions.DataBindingViolationException;
import com.weslei.todosimples.services.exceptions.ObjectNotFoundException;

@Service
public class TaskServices {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserServices userServices;


    public Task findById(Long id) {

        Optional<Task> task = this.taskRepository.findById(id);

        return task.orElseThrow(() -> new ObjectNotFoundException(
            "Tarafe não foi encontrada! Id: " + id + ", Tipo: " + Task.class.getName()
        ));
    }

    public List<Task> findAllByUserId(Long userId) {

        List<Task> tasks = this.taskRepository.findByUser_id(userId);

        return tasks;
    }

    @Transactional
    public Task create(Task obj) {

        User user = this.userServices.findById(obj.getUser().getId());

        obj.setId(null);

        obj.setUser(user);

        obj = taskRepository.save(obj);

        return obj;
    }

    @Transactional
    public Task update(Task obj) {

        Task newObj = findById(obj.getId());

        newObj.setDescription(obj.getDescription());

        return this.taskRepository.save(newObj);
    }

    @Transactional
    public void delete(Long id) {

        findById(id);

        try {
            
            this.taskRepository.deleteById(id);

        } catch (Exception e) {
            throw new DataBindingViolationException("Erro interno." + e);
        }
    }
    
}
