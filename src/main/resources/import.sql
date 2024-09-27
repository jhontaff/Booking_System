INSERT INTO role (role_name) VALUES ('USER');
INSERT INTO role (role_name) VALUES ('ADMIN');

INSERT INTO room_type (room_name) VALUES ('Meeting Room');
INSERT INTO room_type (room_name) VALUES ('Private Office');

INSERT INTO booking_state (state) VALUES ('PENDING');
INSERT INTO booking_state (state) VALUES ('CANCELLED');
INSERT INTO booking_state (state) VALUES ('REJECTED');
INSERT INTO booking_state (state) VALUES ('APPROVED');

INSERT INTO extra_resource (resource_name, quantity) VALUES ('Projector', 4);
INSERT INTO extra_resource (resource_name, quantity) VALUES ('Whiteboard', 3);
INSERT INTO extra_resource (resource_name, quantity) VALUES ('HDMI Cable', 5);
INSERT INTO extra_resource (resource_name, quantity) VALUES ('Microphone', 3);

INSERT INTO room (room_name, room_type_id) VALUES ('Conference Room', 1);
INSERT INTO room (room_name, room_type_id) VALUES ('Community Room', 1);
INSERT INTO room (room_name, room_type_id) VALUES ('Private Office 101', 2);
INSERT INTO room (room_name, room_type_id) VALUES ('Private Office 102', 2);

INSERT INTO booking (user_id, check_in, check_out, room_id, booking_state_id, room_type_id)
VALUES (5, '2023-10-01 11:00:00', '2023-10-01 14:00:00', 1, 1,1);