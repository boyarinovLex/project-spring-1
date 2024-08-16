package com.javarush.spring1.map;

import com.javarush.spring1.domain.dto.TaskTo;
import com.javarush.spring1.domain.entity.Task;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Mapper {

    public Task map(TaskTo taskTo) {
        return Task.builder()
                .id(taskTo.getId())
                .description(taskTo.getDescription())
                .status(taskTo.getStatus())
                .build();
    }

    public TaskTo map(Task task) {
        return TaskTo.builder()
                .id(task.getId())
                .description(task.getDescription())
                .status(task.getStatus())
                .build();
    }
}
