create table messages
(
    id           int auto_increment
        primary key,
    nick_name    varchar(20) not null,
    message_time datetime    not null,
    message      varchar(30) not null
);