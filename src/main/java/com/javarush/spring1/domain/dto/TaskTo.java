package com.javarush.spring1.domain.dto;

import com.javarush.spring1.domain.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TaskTo {

    private Integer id;

    private String description;

    private Status status;

}
