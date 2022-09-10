package ru.scanword.dto;

import lombok.Data;

import java.sql.Blob;

@Data
public class AudioQuestionDTO extends QuestionDTO {
    private Blob audio;
}
