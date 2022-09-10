package ru.scanword.dto;

import lombok.Data;

import java.sql.Blob;

@Data
public class PictureQuestionDTO extends QuestionDTO{
    private Blob picture;
}
