package com.example.todo.model;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

public class Todo {
    private static final AtomicLong ID_GENERATOR = new AtomicLong();
    
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime dueDate;
    private LocalDateTime reminderDate;
    private Priority priority;
    private RecurrencePattern recurrencePattern;
    private Duration estimatedTime;
    private LocalDateTime lastCompletedDate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public enum Priority {
        HIGH, MEDIUM, LOW
    }

    public enum RecurrencePattern {
        NONE, DAILY, WEEKLY, MONTHLY, YEARLY
    }

    public Todo() {
        this.id = ID_GENERATOR.incrementAndGet();
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
        this.priority = Priority.MEDIUM;
        this.recurrencePattern = RecurrencePattern.NONE;
        this.completed = false;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.modifiedDate = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.modifiedDate = LocalDateTime.now();
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
        if (completed) {
            this.lastCompletedDate = LocalDateTime.now();
        }
        this.modifiedDate = LocalDateTime.now();
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
        this.modifiedDate = LocalDateTime.now();
    }

    public LocalDateTime getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(LocalDateTime reminderDate) {
        this.reminderDate = reminderDate;
        this.modifiedDate = LocalDateTime.now();
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
        this.modifiedDate = LocalDateTime.now();
    }

    public RecurrencePattern getRecurrencePattern() {
        return recurrencePattern;
    }

    public void setRecurrencePattern(RecurrencePattern recurrencePattern) {
        this.recurrencePattern = recurrencePattern;
        this.modifiedDate = LocalDateTime.now();
    }

    public Duration getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Duration estimatedTime) {
        this.estimatedTime = estimatedTime;
        this.modifiedDate = LocalDateTime.now();
    }

    public LocalDateTime getLastCompletedDate() {
        return lastCompletedDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    // Utility methods
    public boolean isOverdue() {
        return !completed && dueDate != null && LocalDateTime.now().isAfter(dueDate);
    }

    public boolean needsReminder() {
        return !completed && reminderDate != null && 
               LocalDateTime.now().isAfter(reminderDate) && 
               LocalDateTime.now().isBefore(dueDate);
    }

    public void toggleComplete() {
        this.completed = !this.completed;
        if (this.completed) {
            this.lastCompletedDate = LocalDateTime.now();
        }
        this.modifiedDate = LocalDateTime.now();
    }
}