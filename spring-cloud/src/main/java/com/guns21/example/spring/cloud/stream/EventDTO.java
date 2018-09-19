package com.guns21.example.spring.cloud.stream;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class EventDTO {
    @NotBlank(message = "资产编号不能为空")
    private String name;
}
