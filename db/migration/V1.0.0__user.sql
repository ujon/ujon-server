create table ujon.tbl_role
(
    role_id uuid         not null,
    name    varchar(100) not null,
    constraint pk_role__role_id primary key (role_id),
    constraint uk_role__name unique (name)
);

create table ujon.tbl_user
(
    user_id    uuid                     not null,
    username   varchar(50)              not null,
    password   varchar(100),
    name       varchar(20),
    role_id    uuid                     not null,
    created_at timestamp with time zone not null default now(),
    created_by varchar(50)              not null,
    constraint pk_user__user_id primary key (user_id),
    constraint uk_user__username unique (username),
    constraint fk_user__role_id foreign key (role_id)
        references ujon.tbl_role (role_id)
        on delete cascade
);

comment on column ujon.tbl_user.password is 'When using social login, the password can be `null`.';

create sequence ujon.seq_user_email__user_email_id
    start with 1
    increment by 1
    no minvalue
    no maxvalue
    cache 1
    as bigint;

create table ujon.tbl_user_email
(
    user_email_id integer     not null default nextval('seq_user_email__user_email_id'::regclass),
    user_id       uuid        not null,
    email         varchar(50) not null,
    is_primary    boolean     not null default false,
    is_confirmed  boolean     not null default false,
    constraint pk_user_email__user_email_id primary key (user_email_id),
    constraint uk_user_email__email unique (email),
    constraint fk_user_email__user foreign key (user_id)
        references ujon.tbl_user (user_id)
        on delete cascade
);

