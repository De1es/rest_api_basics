create table gift_certificate
(
    id               int auto_increment
        primary key,
    name             varchar(100)                        not null,
    description      varchar(255)                        not null,
    price            float                               not null,
    create_date      timestamp with time zone            not null,
    last_update_date timestamp with time zone            not null,
    duration         int                                 not null,
    constraint name
        unique (name)
);

create table tag
(
    id   int auto_increment
        primary key,
    name varchar(100) not null unique
);

create table gift_certificate_tag
(
    gift_certificate_id int not null,
    tag_id  int not null,
    constraint certificates_tags_ibfk_1
        foreign key (gift_certificate_id) references gift_certificate (id)
            on update cascade on delete cascade,
    constraint certificates_tags_ibfk_2
        foreign key (tag_id) references tag (id)
            on update cascade on delete cascade
);

create index cert_id
    on gift_certificate_tag (gift_certificate_id);

create index tag_id
    on gift_certificate_tag (tag_id);