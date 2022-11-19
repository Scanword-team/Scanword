create table question
(
    id  int8 generated by default as identity,
    answer varchar(255),
    audio varchar(255),
    image varchar(255),
    question varchar(255),
    type varchar(255),
    primary key (id)
);

create table scanword
(
    id  int8 generated by default as identity,
    height int4, name varchar(255),
    width int4, prompt int4,
    primary key (id)
);
create table scanword_question
(
    question_id int8 not null,
    scanword_id int8 not null,
    direction boolean,
    number int4,
    x int4,
    y int4,
    primary key (question_id, scanword_id)
);
create table solvable_scanword
(
    id  int8 generated by default as identity,
    solved boolean,
    user_id int8,
    scanword_id int8,
    primary key (id)
);
create table solvable_scanword_question
(
    solvable_scanword_id int8 not null,
    question_id int8 not null
);
create table users
(
    id  int8 generated by default as identity,
    name varchar(255),
    password varchar(255),
    role varchar(255),
    status varchar(255),
    primary key (id)
);
alter table scanword_question
    add constraint FKsht4jvxnup2013yn8j70m5lw4 foreign key (question_id) references question;
alter table scanword_question
    add constraint FKh0isnflp28su3xj0lah96pwrj foreign key (scanword_id) references scanword;
alter table solvable_scanword
    add constraint FK2ossllug7odp1u8cupi0qpj6i foreign key (user_id) references users;
alter table solvable_scanword
    add constraint FKg9iw8emxfuyxcc9xefsat7k7e foreign key (scanword_id) references scanword;
alter table solvable_scanword_question
    add constraint FKelpfd5ji1y9upg4rsnkdelvko foreign key (question_id) references question;
alter table solvable_scanword_question
    add constraint FKb2crivghc0gp5h13ioguipy5a foreign key (solvable_scanword_id) references solvable_scanword;