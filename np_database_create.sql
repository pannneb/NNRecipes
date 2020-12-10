
    drop table asocijacije.category if exists

    drop table asocijacije.word if exists

    drop sequence if exists hibernate_sequence

    create table asocijacije.category (
       id bigint not null,
        description varchar(255),
        name varchar(255),
        primary key (id)
    )

    create table asocijacije.word (
       id bigint not null,
        category_fk bigint,
        word varchar(255),
        primary key (id) 
    ) 
create sequence hibernate_sequence start with 1 increment by 1

    alter table asocijacije.word 
       add constraint FK9ty4k8vob028d7nt833py2xk9 
       foreign key (category_fk) 
       references asocijacije.category
