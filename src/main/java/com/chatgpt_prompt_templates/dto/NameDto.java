package com.chatgpt_prompt_templates.dto;

import com.chatgpt_prompt_templates.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NameDto {

    @NotBlank(message= Constants.TEMPLATE_NAME_REQUIRED)
    @Size(min=1, max=24, message = Constants.TEMPLATE_NAME_VALID_LENGTH)
    private String name;
}
