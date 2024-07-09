package com.chatgpt_prompt_templates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long>{
    Optional<Template> findByName(String name);

    @Query("SELECT t.name FROM Template t")
    List<String> findAllNames();
}
