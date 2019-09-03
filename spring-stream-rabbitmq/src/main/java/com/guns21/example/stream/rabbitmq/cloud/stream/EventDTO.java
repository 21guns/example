package com.guns21.example.stream.rabbitmq.cloud.stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    @NotBlank(message = "资产编号不能为空")
    private String name;
}
