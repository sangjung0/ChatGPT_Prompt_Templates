package com.chatgpt_prompt_templates.service;

import com.chatgpt_prompt_templates.Constants;
import com.chatgpt_prompt_templates.dto.TemplateDto;
import com.chatgpt_prompt_templates.exception.NoSuchRecordException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    public List<String> getAllTemplateNames() {
        return templateRepo.findAllNames();
    }

    public List<TemplateDto> getTemplate(String name) throws NoSuchRecordException, JsonProcessingException {
        Optional<Template> found = this.templateRepo.findByName(name);
        if(found.isPresent()) return this.objectMapper.readValue(found.get().getJson(), objectMapper.getTypeFactory().constructCollectionType(List.class, TemplateDto.class));
        throw new NoSuchRecordException(String.format(Constants.RECORD_IS_NOT_EXIST, name));
    }

    public void setTemplateRepo(String name, String json) {
        Template template = new Template(name, json);
        this.templateRepo.save(template);
    }

}
