create table chat_link
(
    chat_id    bigint references chat (chat_id),
    link_id    bigint references link (link_id),
    constraint chat_link_primary_key primary key (chat_id, link_id),
    updated_at timestamp with time zone not null
)
