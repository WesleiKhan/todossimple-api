package com.weslei.todosimples.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = Task.Table_Name)
public class Task {

    public static final String Table_Name = "task";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @NotEmpty
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "description", nullable = false, length = 300)
    private String description;


    public Task() {}

    public Task(Long id, User user, String description) {

        this.id = id;
        this.user = user;
        this.description = description;

    }

    public long getId() {

        return this.id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public User getUser() {

        return this.user;
    }

    public void setUser(User user) {

        this.user = user;
    }

    public String getDescription() {

        return this.description;
    }

    public void setDescription(String description) {

        this.description = description;
    }


    @Override
    public boolean equals(Object obj) {

        if (obj == this) {

            return true;
        }

        if (obj == null) {

            return false;
        }

        if (!(obj instanceof Task)) {

            return false;
        }

        Task othertask = (Task) obj;

        return Objects.equals(this.id, othertask.id) && Objects.equals(this.user, othertask.user) && Objects.equals(this.description, othertask.description);
    }


    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        result = prime * result + Long.hashCode(this.id);

        return result;
    }
    
}
