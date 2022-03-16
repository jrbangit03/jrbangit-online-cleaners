package com.jrbangit.onlinecleaners.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Users")
public class Users {
    @Id
    @Column(nullable = false, updatable = false)
    private String userId;
    private String password;
    private String userFn;
    private String userLn;
    private String userType;
}
