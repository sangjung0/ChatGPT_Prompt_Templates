package com.chatgpt_prompt_templates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemplateDto {

    @JsonProperty("label")
    private String label;

    @JsonProperty("type")
    private String type;

    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private String value;

    @JsonProperty("placeholder")
    private String placeholder;
}
