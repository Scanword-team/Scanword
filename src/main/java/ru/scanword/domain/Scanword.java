package ru.scanword.domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "scanword")
@Data
public class Scanword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;

}
