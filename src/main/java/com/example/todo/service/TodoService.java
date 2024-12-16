package com.example.todo.service;

import com.example.todo.model.Todo;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TodoService {
    private final List<Todo> todos = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public List<Todo> getAllTodos() {
        return todos;
    }

    public Todo addTodo(String task) {
        Todo todo = new Todo(counter.incrementAndGet(), task, false);
        todos.add(todo);
        return todo;
    }

    public void toggleTodo(Long id) {
        todos.stream()
            .filter(todo -> todo.getId().equals(id))
            .findFirst()
            .ifPresent(todo -> todo.setCompleted(!todo.isCompleted()));
    }

    public void deleteTodo(Long id) {
        todos.removeIf(todo -> todo.getId().equals(id));
    }
}