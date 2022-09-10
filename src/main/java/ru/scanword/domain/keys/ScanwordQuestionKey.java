package ru.scanword.domain.keys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ScanwordQuestionKey implements Serializable {

    @Column(name = "scanword_id")
    Long scanwordId;

    @Column(name = "question_id")
    Long questionId;
}
