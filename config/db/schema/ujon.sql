CREATE SCHEMA IF NOT EXISTS ujon;
CREATE TABLE ujon.tbl_document
(
    id         uuid PRIMARY KEY,
    type       varchar(10) not null,
    json_data  jsonb,
    text_data  text,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);

comment on column ujon.tbl_document.type is 'markdown, json';

CREATE TABLE ujon.tbl_tag
(
    id   bigserial PRIMARY KEY,
    name varchar(255) unique not null
);

CREATE TABLE ujon.tbl_document_tag
(
    id          bigserial PRIMARY KEY,
    document_id uuid      not null,
    CONSTRAINT fk_document_tags_01 FOREIGN KEY (document_id) REFERENCES ujon.tbl_document (id),
    tag_id      bigserial not null,
    CONSTRAINT fk_document_tags_02 FOREIGN KEY (tag_id) REFERENCES ujon.tbl_tag (id)
);


