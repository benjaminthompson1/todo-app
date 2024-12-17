package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Controller
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String showTodoList(Model model) {
        // Add necessary objects and enums for the form
        model.addAttribute("newTodo", new Todo());
        model.addAttribute("todos", todoService.getAllTodos());
        model.addAttribute("priorities", Todo.Priority.values());
        model.addAttribute("recurrencePatterns", Todo.RecurrencePattern.values());
        
        // Add filtered lists
        model.addAttribute("overdueTodos", todoService.getOverdueTodos());
        model.addAttribute("completedTodos", todoService.getCompletedTodos());
        model.addAttribute("incompleteTodos", todoService.getIncompleteTodos());
        
        return "todos";
    }

    @PostMapping("/todo/add")
    public String addTodo(@ModelAttribute("newTodo") Todo todo,
                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDate,
                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime reminderDate,
                         @RequestParam(required = false) Long estimatedHours,
                         @RequestParam(required = false) Long estimatedMinutes) {
        
        // Set dates if provided
        if (dueDate != null) {
            todo.setDueDate(dueDate);
        }
        if (reminderDate != null) {
            todo.setReminderDate(reminderDate);
        }

        // Calculate and set estimated time if provided
        if (estimatedHours != null || estimatedMinutes != null) {
            long hours = estimatedHours != null ? estimatedHours : 0;
            long minutes = estimatedMinutes != null ? estimatedMinutes : 0;
            todo.setEstimatedTime(Duration.ofHours(hours).plusMinutes(minutes));
        }

        todoService.createTodo(todo);
        return "redirect:/";
    }

    @PostMapping("/todo/update/{id}")
    public String updateTodo(@PathVariable Long id,
                           @ModelAttribute Todo todo,
                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDate,
                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime reminderDate,
                           @RequestParam(required = false) Long estimatedHours,
                           @RequestParam(required = false) Long estimatedMinutes) {
        
        // Set dates if provided
        if (dueDate != null) {
            todo.setDueDate(dueDate);
        }
        if (reminderDate != null) {
            todo.setReminderDate(reminderDate);
        }

        // Calculate and set estimated time if provided
        if (estimatedHours != null || estimatedMinutes != null) {
            long hours = estimatedHours != null ? estimatedHours : 0;
            long minutes = estimatedMinutes != null ? estimatedMinutes : 0;
            todo.setEstimatedTime(Duration.ofHours(hours).plusMinutes(minutes));
        }

        todoService.updateTodo(id, todo);
        return "redirect:/";
    }

    @PostMapping("/todo/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return "redirect:/";
    }

    @PostMapping("/todo/toggle/{id}")
    public String toggleTodoComplete(@PathVariable Long id) {
        todoService.toggleComplete(id);
        return "redirect:/";
    }

    @GetMapping("/todo/filter")
    public String filterTodos(@RequestParam(required = false) Todo.Priority priority,
                            @RequestParam(required = false) Boolean showCompleted,
                            Model model) {
        if (priority != null) {
            model.addAttribute("todos", todoService.getTodosByPriority(priority));
        } else if (showCompleted != null) {
            model.addAttribute("todos", showCompleted ? 
                todoService.getCompletedTodos() : 
                todoService.getIncompleteTodos());
        } else {
            model.addAttribute("todos", todoService.getAllTodos());
        }
        
        // Add necessary form objects
        model.addAttribute("newTodo", new Todo());
        model.addAttribute("priorities", Todo.Priority.values());
        model.addAttribute("recurrencePatterns", Todo.RecurrencePattern.values());
        
        return "todos";
    }
}