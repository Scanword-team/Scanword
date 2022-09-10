package ru.scanword.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "question")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "answer")
    private String answer;

    @Column(name = "question")
    private String question;



}
