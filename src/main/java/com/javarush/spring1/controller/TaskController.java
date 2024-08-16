package com.javarush.spring1.controller;

import com.javarush.spring1.domain.dto.TaskTo;
import com.javarush.spring1.domain.entity.Status;
import com.javarush.spring1.service.TaskService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/page/{pageNumber}")
    public ModelAndView showPage(
            ModelAndView modelAndView,
            HttpSession session,
            @PathVariable(name = "pageNumber") int pageNumber,
            @RequestParam(defaultValue = "10") int limit
    ) {
        Page<TaskTo> taskPage = taskService.findAll(pageNumber, limit);
        List<TaskTo> taskToList = taskPage.getContent();
        int totalPages = taskPage.getTotalPages();

        session.setAttribute("currentPage", pageNumber);
        session.setAttribute("totalPages", totalPages);
        session.setAttribute("status", Status.values());
        modelAndView.addObject("taskToList", taskToList);
        modelAndView.setViewName("tasks");

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView showOneTaskAdnTasks(
            ModelAndView modelAndView,
            HttpSession session,
            @PathVariable(name = "id") Integer id,
            @RequestParam(defaultValue = "10") int limit
    ) {
        Optional<TaskTo> optionalTaskTo = taskService.getById(id);
        int pageNumber = (int) session.getAttribute("currentPage");
        Page<TaskTo> taskToList = taskService.findAll(pageNumber, limit);

        optionalTaskTo.ifPresent(taskTo -> modelAndView.addObject("task", taskTo));
        modelAndView.addObject("taskToList", taskToList);
        modelAndView.setViewName("tasks");

        return modelAndView;
    }

    @PostMapping("/page/{pageNumber}")
    public String createNewTask(
            @PathVariable(name = "pageNumber") int pageNumber,
            TaskTo taskTo,
            @RequestParam(required = false, name = "createTask") String createTask
    ) {
        if (Objects.nonNull(createTask)) {
            taskService.save(taskTo);
        }
        return redirectTo(pageNumber);
    }

    @PostMapping("/{id}")
    public String updateOrDeleteTask(
            TaskTo taskTo,
            @RequestParam(required = false, name = "deleteTask") String deleteTask,
            @SessionAttribute(name = "currentPage") int pageNumber
    ) {
        if (Objects.nonNull(deleteTask)) {
            taskService.delete(taskTo);
        } else {
            taskService.save(taskTo);
        }
        return redirectTo(pageNumber);
    }

    private String redirectTo(int pageNumber) {
        return "redirect:/tasks/page/%d".formatted(pageNumber);
    }
}
