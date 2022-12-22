create table audio
(
    id    bigserial not null,
    audio text,
    primary key (id)
)

create table dictionary
(
    id   bigserial not null,
    name varchar(255),
    primary key (id)
)

create table image
(
    id    bigserial not null,
    image text,
    primary key (id)
)

create table question
(
    id            bigserial not null,
    answer        varchar(255),
    question      varchar(255),
    type          varchar(255),
    audio_id      int8,
    dictionary_id int8,
    image_id      int8,
    primary key (id)
)

create table scanword
(
    id     bigserial not null,
    height int4,
    name   varchar(255),
    prompt int4,
    width  int4,
    primary key (id)
)

create table scanword_question
(
    question_id int8 not null,
    scanword_id int8 not null,
    direction   boolean,
    number      int4,
    x           int4,
    y           int4,
    primary key (question_id, scanword_id)
)

create table solvable_scanword
(
    id          bigserial not null,
    prompt      int4,
    solved      boolean,
    user_id     int8,
    scanword_id int8,
    primary key (id)
)

create table solvable_scanword_question
(
    solvable_scanword_id int8 not null,
    question_id          int8 not null
)

create table users
(
    id       bigserial not null,
    name     varchar(255),
    password varchar(255),
    role     varchar(255),
    status   varchar(255),
    primary key (id)
)

alter table question
    add constraint FKas17om8fk0j2qcy4fpgyaswrs foreign key (audio_id) references audio
alter table question
    add constraint FKj0sm9wprw6mlsatjvkl1d6el2 foreign key (dictionary_id) references dictionary
alter table question
    add constraint FKakad3vxpyeruq3mfnhk2x0ug6 foreign key (image_id) references image
alter table scanword_question
    add constraint FKsht4jvxnup2013yn8j70m5lw4 foreign key (question_id) references question
alter table scanword_question
    add constraint FKh0isnflp28su3xj0lah96pwrj foreign key (scanword_id) references scanword
alter table solvable_scanword
    add constraint FK2ossllug7odp1u8cupi0qpj6i foreign key (user_id) references users
alter table solvable_scanword
    add constraint FKg9iw8emxfuyxcc9xefsat7k7e foreign key (scanword_id) references scanword
alter table solvable_scanword_question
    add constraint FKelpfd5ji1y9upg4rsnkdelvko foreign key (question_id) references question
alter table solvable_scanword_question
    add constraint FKb2crivghc0gp5h13ioguipy5a foreign key (solvable_scanword_id) references solvable_scanword