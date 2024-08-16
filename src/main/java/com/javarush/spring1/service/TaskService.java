package com.javarush.spring1.service;

import com.javarush.spring1.dao.TaskDAO;
import com.javarush.spring1.domain.dto.TaskTo;
import com.javarush.spring1.map.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskDAO taskDAO;

    public Page<TaskTo> findAll(int pageNumber, int limit) {
        return taskDAO.findAll(PageRequest.of(pageNumber -1 , limit)).map(Mapper::map);
    }

    public Optional<TaskTo> getById(Integer id) {
        return taskDAO.findById(id).map(Mapper::map);
    }

    public Optional<TaskTo> save(TaskTo taskTo) {
        return Optional.of(Mapper.map(taskDAO.save(Mapper.map(taskTo))));
    }

    public void delete(TaskTo taskTo) {
        taskDAO.delete(Mapper.map(taskTo));
    }

}
