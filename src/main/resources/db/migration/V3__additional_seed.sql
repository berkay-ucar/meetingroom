-- USER ROLES
INSERT INTO user_roles (user_id,role_id) VALUES
                                             (13,1),
                                             (14,2),
                                             (15,2);

-- ROOMS
INSERT INTO rooms (name,capacity,location) VALUES
                                               ('Conference Room A',10,'1st Floor'),
                                               ('Conference Room B',8,'2nd Floor');

-- GROUPS
INSERT INTO groups (name) VALUES
                              ('Team Alpha'),
                              ('Team Beta');

-- GROUP MEMBERS
INSERT INTO group_members (group_id,user_id) VALUES
                                                 (1,14),
                                                 (1,15),
                                                 (2,15);

-- RESERVATIONS
INSERT INTO reservations (room_id,organizer_id,group_id,start_time,end_time) VALUES
                                                                                 (1,13,1,'2026-03-13 09:00:00','2026-03-13 10:00:00'),
                                                                                 (2,14,2,'2026-03-13 11:00:00','2026-03-13 12:00:00');

