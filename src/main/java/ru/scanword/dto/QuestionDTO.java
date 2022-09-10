package ru.scanword.dto;

import lombok.Data;
import ru.scanword.domain.Scanword;

@Data
public class QuestionDTO {

    private Long id;
    private String answer;
    private String question;
    private Scanword scanword;

}
