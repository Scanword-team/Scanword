package ru.scanword.domain;

import lombok.Data;
import ru.scanword.domain.keys.ScanwordQuestionKey;

import javax.persistence.*;

@Entity
@Table(name = "scanword_question")
@Data
public class ScanwordQuestion {

    @EmbeddedId
    private ScanwordQuestionKey id = new ScanwordQuestionKey();

    @ManyToOne
    @MapsId("scanwordId")
    @JoinColumn(name = "scanword_id")
    private Scanword scanword;

    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "direction")
    private Boolean direction;

    @Column(name = "number")
    private int number;

    @Column(name = "x")
    private int x;

    @Column(name = "y")
    private int y;

}
