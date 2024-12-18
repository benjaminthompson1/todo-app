package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     * Get all todo items
     * @return List of all todos
     */
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    /**
     * Get a specific todo by ID
     * @param id The ID of the todo
     * @return The todo if found, or null
     */
    public Todo getTodoById(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        return todo.orElse(null);
    }

    /**
     * Create a new todo
     * @param todo The todo to create
     * @return The created todo
     */
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    /**
     * Update an existing todo
     * @param id The ID of the todo to update
     * @param updatedTodo The updated todo data
     * @return The updated todo, or null if not found
     */
    public Todo updateTodo(Long id, Todo updatedTodo) {
        Optional<Todo> existingTodo = todoRepository.findById(id);
        
        if (existingTodo.isPresent()) {
            Todo todo = existingTodo.get();
            todo.setTitle(updatedTodo.getTitle());
            todo.setDescription(updatedTodo.getDescription());
            todo.setComplete(updatedTodo.isComplete());
            return todoRepository.save(todo);
        }
        
        return null;
    }

    /**
     * Delete a todo by ID
     * @param id The ID of the todo to delete
     */
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    /**
     * Toggle the completion status of a todo
     * @param id The ID of the todo to toggle
     */
    public void toggleTodoComplete(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        todo.ifPresent(t -> {
            t.setComplete(!t.isComplete());
            todoRepository.save(t);
        });
    }
}