create table avenger (
    id bigserial primary key,
    nick varchar(36),
    person varchar(128),
    description varchar(128),
    history text
);

alter table avenger
add constraint UK_5r88eemotwgru6k0ilqb2ledh unique (nick);