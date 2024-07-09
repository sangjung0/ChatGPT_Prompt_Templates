package com.chatgpt_prompt_templates.service;

import com.chatgpt_prompt_templates.Constants;
import com.chatgpt_prompt_templates.exception.NoSuchRecordException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.chatgpt_prompt_templates.Template;
import com.chatgpt_prompt_templates.TemplateRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class TemplateService {
    private final TemplateRepository templateRepo;

    public List<String> getAllTemplateNames() {
        return templateRepo.findAllNames();
    }

    public String getTemplate(String name) throws NoSuchRecordException {
        Optional<Template> found = this.templateRepo.findByName(name);
        if(found.isPresent()) return found.get().getJson();
        throw new NoSuchRecordException(String.format(Constants.RECORD_IS_NOT_EXIST, name));
    }

    public void setTemplateRepo(String name, String json) {
        Template template = new Template(name, json);
        this.templateRepo.save(template);
    }

}
