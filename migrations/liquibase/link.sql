create table link
(
    link_id bigint generated always as identity,
    url     text not null,

    primary key (link_id)
)
