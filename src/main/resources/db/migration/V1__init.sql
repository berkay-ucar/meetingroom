-- PostgreSQL extension (room time overlap constraint için)
CREATE EXTENSION IF NOT EXISTS btree_gist;

-- USERS
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255)
);

-- ROLES
CREATE TABLE roles (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(100) UNIQUE
);

-- ROOMS
CREATE TABLE rooms (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255),
                       capacity INTEGER,
                       location VARCHAR(255)
);

-- GROUPS (meeting groups)
CREATE TABLE groups (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255)
);

-- RESERVATIONS
CREATE TABLE reservations (
                              id BIGSERIAL PRIMARY KEY,
                              room_id BIGINT,
                              organizer_id BIGINT,
                              group_id BIGINT,
                              start_time TIMESTAMP NOT NULL,
                              end_time TIMESTAMP NOT NULL,

                              CONSTRAINT fk_reservation_room
                                  FOREIGN KEY (room_id)
                                      REFERENCES rooms(id)
                                      ON DELETE CASCADE,

                              CONSTRAINT fk_reservation_user
                                  FOREIGN KEY (organizer_id)
                                      REFERENCES users(id)
                                      ON DELETE CASCADE,

                              CONSTRAINT fk_reservation_group
                                  FOREIGN KEY (group_id)
                                      REFERENCES groups(id)
                                      ON DELETE SET NULL
);

-- USER ROLES
CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,

                            PRIMARY KEY (user_id, role_id),

                            CONSTRAINT fk_user_roles_user
                                FOREIGN KEY (user_id)
                                    REFERENCES users(id)
                                    ON DELETE CASCADE,

                            CONSTRAINT fk_user_roles_role
                                FOREIGN KEY (role_id)
                                    REFERENCES roles(id)
                                    ON DELETE CASCADE
);

-- GROUP MEMBERS (users inside meeting groups)
CREATE TABLE group_members (
                               group_id BIGINT NOT NULL,
                               user_id BIGINT NOT NULL,

                               PRIMARY KEY (group_id, user_id),

                               CONSTRAINT fk_group_members_group
                                   FOREIGN KEY (group_id)
                                       REFERENCES groups(id)
                                       ON DELETE CASCADE,

                               CONSTRAINT fk_group_members_user
                                   FOREIGN KEY (user_id)
                                       REFERENCES users(id)
                                       ON DELETE CASCADE
);

-- RESERVATION PARTICIPANTS
CREATE TABLE reservation_participants (
                                          reservation_id BIGINT NOT NULL,
                                          user_id BIGINT NOT NULL,

                                          PRIMARY KEY (reservation_id, user_id),

                                          CONSTRAINT fk_reservation_participant_res
                                              FOREIGN KEY (reservation_id)
                                                  REFERENCES reservations(id)
                                                  ON DELETE CASCADE,

                                          CONSTRAINT fk_reservation_participant_user
                                              FOREIGN KEY (user_id)
                                                  REFERENCES users(id)
                                                  ON DELETE CASCADE
);

-- PERFORMANCE INDEX
CREATE INDEX idx_reservation_room_time
    ON reservations(room_id, start_time, end_time);

CREATE INDEX idx_reservation_user_time
    ON reservations(organizer_id, start_time, end_time);

-- ROOM DOUBLE BOOKING ENGELLEME (PostgreSQL)
ALTER TABLE reservations
    ADD CONSTRAINT no_room_overlap
    EXCLUDE USING gist (
    room_id WITH =,
    tsrange(start_time, end_time) WITH &&
);

-- DEFAULT ROLES
INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('USER');