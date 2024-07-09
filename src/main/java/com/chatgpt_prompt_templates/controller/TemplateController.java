package com.chatgpt_prompt_templates.controller;

import com.chatgpt_prompt_templates.dto.NameDto;
import com.chatgpt_prompt_templates.exception.ValidException;
import com.chatgpt_prompt_templates.exception.VerifyException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatgpt_prompt_templates.service.TemplateService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/template")
@RequiredArgsConstructor
@Slf4j
public class TemplateController {
    private final TemplateService templateService;

    @PostMapping("/get_all_templates")
    public ResponseEntity<?> getAllTemplates(){
        return Controller.run(()->{
            return new ResponseEntity<List<String>>(this.templateService.getAllTemplateNames(), HttpStatus.OK);
        });
    }

    @PostMapping("/get")
    public ResponseEntity<?> get(@RequestBody @Valid NameDto dt, BindingResult bindingResult){
        return Controller.run(()->{
           return new ResponseEntity<String>(this.templateService.getTemplate(dt.getName()), HttpStatus.OK);
        });
    }

    private interface ControllerInterface {
        ResponseEntity<?> run() throws Exception;
    }

    private static class Controller {
        public static ResponseEntity<?> run(ControllerInterface base){
            try{
                return base.run();
            }catch(VerifyException e){
                return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }catch(ValidException e){
                return new ResponseEntity<Map<String, String>>(e.getErrors(), HttpStatus.BAD_REQUEST);
            }catch(Exception e){
                return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
    }
}
