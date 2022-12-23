package ru.scanword.dto;

import lombok.Data;
import ru.scanword.domain.Scanword;

@Data
public class ScanwordQuestionAllDTO {
    private Scanword scanword;
    private Long questionId;
    private Boolean direction;
    private int number;
    private int x;
    private int y;
}
