CREATE TABLE IF NOT EXISTS  USERS(
                                     username varchar_ignorecase(50) not null primary key,
                                     password varchar_ignorecase(255) not null,
                                     enabled boolean not null);

CREATE TABLE IF NOT EXISTS AUTHORITIES (
                                           username varchar_ignorecase(60) not null,
                                           authority varchar_ignorecase(60) not null,
                                           constraint fk_authorities_users foreign key(username) references users(username) ON DELETE CASCADE);
create unique index ix_auth_username on authorities (username,authority);

INSERT INTO USERS(username, password, enabled) VALUES
('user','$2a$10$xCqrabnPGgYwMgHCzDBSY.wgFgC3LK3jLtWMYzG9O5yDCk10AEMCW', 1),
('user2','$2a$10$xCqrabnPGgYwMgHCzDBSY.wgFgC3LK3jLtWMYzG9O5yDCk10AEMCW', 1);

INSERT INTO AUTHORITIES VALUES
('user','ROLE_USER'),
('user2','ROLE_USER');