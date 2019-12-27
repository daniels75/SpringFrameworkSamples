package org.daniels.spring.todo.model;


public class TodoDTO {
    private Long id;
    private String title;
    private String description;
    private int priority;
    private boolean complete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TodoDTO id(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TodoDTO title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public TodoDTO description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return complete;
    }

    public TodoDTO complete(boolean complete) {
        this.complete = complete;
        return this;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public TodoDTO priority(int priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TodoDTO)) {
            return false;
        }
        return id != null && id.equals(((TodoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TodoDTO{" +
                "id=" + getId() +
                ", title='" + getTitle() + "'" +
                ", description='" + getDescription() + "'" +
                ", priority=" + getPriority() + "" +
                ", complete=" + isComplete() + "" +
                "}";
    }
}
