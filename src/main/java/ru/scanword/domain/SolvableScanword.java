package ru.scanword.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "solvable_scanword")
@Data
public class SolvableScanword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "scanword_id")
    private Scanword scanword;

    @Column(name = "solved")
    private Boolean solved;

    @ManyToMany
    @JoinTable(
            name = "solvable_scanword_question",
            joinColumns = @JoinColumn(name = "solvable_scanword_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    List<Question> solvedQuestions;

}