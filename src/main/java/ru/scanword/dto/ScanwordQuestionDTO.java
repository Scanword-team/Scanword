package ru.scanword.dto;

import lombok.Data;
import ru.scanword.domain.Question;
import ru.scanword.domain.Scanword;
import ru.scanword.domain.keys.ScanwordQuestionKey;

@Data
public class ScanwordQuestionDTO {

    //private ScanwordQuestionKey id;
    private Scanword scanword;
    private Question question;
    private Boolean direction;
    private int x;
    private int y;
}
