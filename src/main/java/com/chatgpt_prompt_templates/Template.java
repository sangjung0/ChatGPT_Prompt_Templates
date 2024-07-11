package com.chatgpt_prompt_templates;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Template {
    public Template(String name, String json)
    {
        this.name = name;
        this.json = json;
    }

    @Id
    @Column(nullable = false, unique = true, length=255)
    private String name;

    @Column(nullable = false, length = 1023)
    private String json;

}