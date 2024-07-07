package com.chatgpt_prompt_templates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.auth", "com.chatgpt_prompt_templates"})
@EnableJpaRepositories(basePackages = {"com.auth", "com.chatgpt_prompt_templates"})
@EntityScan(basePackages = {"com.auth", "com.chatgpt_prompt_templates"})
public class ChatGptPromptTemplatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatGptPromptTemplatesApplication.class, args);
	}

}
