package ru.scanword.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.sql.Blob;

@Entity
@Table(name = "picture_question")
public class PictureQuestion extends Question{

    @Lob
    @Column(name = "picture")
    private Blob picture;
}
