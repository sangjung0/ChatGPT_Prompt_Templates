package com.auth;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED, force = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique = true, length=254)
    private String email;

    @Column(nullable=false, length=30)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=10)
    private UserRoleEnum role;

    public User(String email, String password, UserRoleEnum role){
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String email, String password){
        this(email, password, UserRoleEnum.User);
    }
    public enum UserRoleEnum{
        User, ADMIN
    }
}

