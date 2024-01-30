package com.solux.pyi.pyiplanyouridea.todos.dto;

import com.solux.pyi.pyiplanyouridea.todos.domain.Todos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {

    private Long todoUuid;
    private LocalDateTime todoDate;
    private String todoDetails;

    public TodoDto(Todos entity) {
        todoUuid = entity.getTodoUuid();
        todoDate = entity.getTodoDate();
        todoDetails = entity.getTodoDetails();
    }

}
