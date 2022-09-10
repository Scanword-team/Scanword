package ru.scanword.dto;

import lombok.Data;
import ru.scanword.domain.Question;
import ru.scanword.domain.Scanword;
import ru.scanword.domain.User;
import java.util.Set;

@Data
public class SolvableScanwordDTO {

    private long id;
    private User owner;
    private Scanword scanword;
    private Boolean solved;
    Set<Question> solvedQuestions;
}
