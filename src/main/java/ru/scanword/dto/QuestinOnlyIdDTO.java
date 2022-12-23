package ru.scanword.dto;

import lombok.Data;

@Data
public class QuestinOnlyIdDTO {

    private Long id;
    private String answer;
    private String question;
    private String type;
    private Long dictionaryId;
    private Long audioId;
    private Long imageId;

}
