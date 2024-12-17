package com.example.todo.service;

import com.example.todo.model.Todo;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class TodoService {
    private final List<Todo> todos = new ArrayList<>();

    // Basic CRUD operations
    public List<Todo> getAllTodos() {
        return new ArrayList<>(todos);
    }

    public Todo getTodoById(Long id) {
        return todos.stream()
                   .filter(todo -> todo.getId().equals(id))
                   .findFirst()
                   .orElse(null);
    }

    public Todo createTodo(Todo todo) {
        todos.add(todo);
        return todo;
    }

    public Todo updateTodo(Long id, Todo updatedTodo) {
        Optional<Todo> existingTodo = todos.stream()
                                         .filter(t -> t.getId().equals(id))
                                         .findFirst();
        
        if (existingTodo.isPresent()) {
            Todo todo = existingTodo.get();
            todo.setTitle(updatedTodo.getTitle());
            todo.setDescription(updatedTodo.getDescription());
            todo.setDueDate(updatedTodo.getDueDate());
            todo.setReminderDate(updatedTodo.getReminderDate());
            todo.setPriority(updatedTodo.getPriority());
            todo.setRecurrencePattern(updatedTodo.getRecurrencePattern());
            todo.setEstimatedTime(updatedTodo.getEstimatedTime());
            return todo;
        }
        return null;
    }

    public void deleteTodo(Long id) {
        todos.removeIf(todo -> todo.getId().equals(id));
    }

    // Task status management
    public void toggleComplete(Long id) {
        getTodoById(id).ifPresent(Todo::toggleComplete);
    }

    // Filter methods
    public List<Todo> getCompletedTodos() {
        return todos.stream()
                   .filter(Todo::isCompleted)
                   .collect(Collectors.toList());
    }

    public List<Todo> getIncompleteTodos() {
        return todos.stream()
                   .filter(todo -> !todo.isCompleted())
                   .collect(Collectors.toList());
    }

    public List<Todo> getOverdueTodos() {
        return todos.stream()
                   .filter(Todo::isOverdue)
                   .collect(Collectors.toList());
    }

    public List<Todo> getTodosByPriority(Todo.Priority priority) {
        return todos.stream()
                   .filter(todo -> todo.getPriority() == priority)
                   .collect(Collectors.toList());
    }

    public List<Todo> getTodosWithReminders() {
        return todos.stream()
                   .filter(Todo::needsReminder)
                   .collect(Collectors.toList());
    }

    public List<Todo> getRecurringTodos() {
        return todos.stream()
                   .filter(todo -> todo.getRecurrencePattern() != Todo.RecurrencePattern.NONE)
                   .collect(Collectors.toList());
    }

    // Helper method for getTodoById that returns Optional
    private Optional<Todo> getTodoById(Long id) {
        return todos.stream()
                   .filter(todo -> todo.getId().equals(id))
                   .findFirst();
    }
}