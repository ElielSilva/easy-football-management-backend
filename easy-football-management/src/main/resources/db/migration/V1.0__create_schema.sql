create table champions_ships (
                                 id bigint not null auto_increment,
                                 award float(23) not null,
                                 created_at datetime(6),
                                 name varchar(255),
                                 quantity integer not null,
                                 status enum ('CREATED','CURRENT','FINISH'),
                                 type enum ('CUP','LEAGUE'),
                                 users_id bigint,
                                 primary key (id)
) engine=InnoDB;

create table champions_ships_has_teams (
                                           id bigint not null auto_increment,
                                           champions_ships_id bigint,
                                           teams_id bigint,
                                           primary key (id)
) engine=InnoDB;

create table players (
                         id bigint not null auto_increment,
                         full_name varchar(255),
                         number integer not null,
                         position enum ('AT','GL','LD','LE','ME','PD','PE','VOL','ZG'),
                         team_id bigint,
                         primary key (id)
) engine=InnoDB;

create table results (
                         id bigint not null auto_increment,
                         date_match datetime(6),
                         qnt_gols_home integer not null,
                         qnt_gols_outside integer not null,
                         home_id bigint,
                         outside_id bigint,
                         primary key (id)
) engine=InnoDB;

create table statistics (
                            id bigint not null auto_increment,
                            gols integer not null,
                            matchs integer not null,
                            participations integer not null,
                            qnt_gols_against integer not null,
                            qnt_red_cards integer not null,
                            qnt_yellow_cards integer not null,
                            players_id bigint,
                            results_id bigint,
                            teams_id bigint,
                            primary key (id)
) engine=InnoDB;

create table teams (
                       id bigint not null auto_increment,
                       name varchar(255),
                       url_image varchar(255),
                       users_id bigint,
                       primary key (id)
) engine=InnoDB;

create table teams_players (
                               teams_id bigint not null,
                               players_id bigint not null
) engine=InnoDB;

create table users (
                       id bigint not null auto_increment,
                       email varchar(255),
                       full_name varchar(255),
                       phone varchar(255),
                       url_image varchar(255),
                       primary key (id)
) engine=InnoDB;

alter table champions_ships_has_teams
    add constraint UK3ne4ct9a1iycdyan2t74kgrdl unique (champions_ships_id);

alter table champions_ships_has_teams
    add constraint UK8shd8h1w0cyqy1k1hj6hutcx1 unique (teams_id);

alter table results
    add constraint UK6wuk1pi20fy8p6qwalydwkiyk unique (home_id);

alter table results
    add constraint UKiu0m8dqg2f8qijpop8frkap40 unique (outside_id);

alter table statistics
    add constraint UKitw9309d5evj6bd47a0iym75f unique (players_id);

alter table statistics
    add constraint UKsj3hryeo8bcpmlyoe46cpbxws unique (results_id);

alter table statistics
    add constraint UK54d5j5t3krps7m5t87hxlxpt2 unique (teams_id);

alter table teams
    add constraint UKj73369lonbegh0mybuj74b76c unique (users_id);

alter table teams_players
    add constraint UKnb0pdbtxmay2vnw649e9i8n7t unique (players_id);

alter table champions_ships
    add constraint FKeuic4x3e03emtu3mr0gu20uxf
        foreign key (users_id)
            references users (id);

alter table champions_ships_has_teams
    add constraint FK6yw5tgksrm3284c6wodj3feu8
        foreign key (champions_ships_id)
            references champions_ships (id);

alter table champions_ships_has_teams
    add constraint FK7aefun4ppjutcpgeesy81v9ol
        foreign key (teams_id)
            references teams (id);

alter table players
    add constraint FK5nglidr00c4dyybl171v6kask
        foreign key (team_id)
            references teams (id);

alter table results
    add constraint FK17prob05txh725antt1leq75o
        foreign key (home_id)
            references champions_ships_has_teams (id);

alter table results
    add constraint FK9pou57uymkuuio3xp3q1f6i8p
        foreign key (outside_id)
            references champions_ships_has_teams (id);

alter table statistics
    add constraint FK1fgv4sdp051xbob4ncebsv1ce
        foreign key (players_id)
            references players (id);

alter table statistics
    add constraint FKj3wm256bcu2dnohatygvr1sj1
        foreign key (results_id)
            references results (id);

alter table statistics
    add constraint FK45i6tbcikxw9tkpbbxpahegg6
        foreign key (teams_id)
            references champions_ships_has_teams (id);

alter table teams
    add constraint FKavydmycmchcsgmq5nje9rmyjr
        foreign key (users_id)
            references users (id);

alter table teams_players
    add constraint FKic8mysm1vaupst5gfnw711cr3
        foreign key (players_id)
            references players (id);

alter table teams_players
    add constraint FKlcfmku1xaseh4tx675kffmn3o
        foreign key (teams_id)
            references teams (id);