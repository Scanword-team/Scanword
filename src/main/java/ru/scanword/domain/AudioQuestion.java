package ru.scanword.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.sql.Blob;

@Entity
@Table(name = "audio_question")
public class AudioQuestion extends Question{

    @Lob
    @Column(name = "audio")
    private Blob audio;
}
