package ru.scanword.dto;

import lombok.Data;
import ru.scanword.domain.Audio;
import ru.scanword.domain.Dictionary;
import ru.scanword.domain.Image;
import ru.scanword.domain.Scanword;

import javax.persistence.Column;

@Data
public class QuestionDTO {

    private Long id;
    private String answer;
    private String question;
    private String type;
    private Dictionary dictionary;
    private Audio audio;
    private Image image;

}
