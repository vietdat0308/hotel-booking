----------------------------------
--Initialize hotel booking tables--
----------------------------------

SET search_path TO postgres;
set schema 'public';

CREATE TABLE hotels (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    location INT2 NOT NULL
);
CREATE TABLE room_type (
   id BIGSERIAL PRIMARY KEY,
   type INT2 NOT NULL,
   description text
);
CREATE TABLE rooms (
   id BIGSERIAL PRIMARY KEY,
   hotel_id BIGSERIAL NOT NULL,
   room_type_id BIGSERIAL NOT NULL,
   name VARCHAR(128) NOT NULL,
   price DOUBLE precision NOT NULL,
   status INT2 NOT NULL
);
CREATE TABLE users (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(128) NOT NULL,
   email VARCHAR(128) NOT NULL,
   phone_number VARCHAR(64) NOT NULL,
   address VARCHAR(128)
);
CREATE TABLE bookings (
  id BIGSERIAL PRIMARY KEY,
  room_id BIGSERIAL NOT NULL,
  user_id BIGSERIAL NOT NULL,
  check_in TIMESTAMP NOT NULL,
  check_out TIMESTAMP NOT NULL,
  status INT2 NOT NULL,
  created_at TIMESTAMP NOT NULL
);

ALTER TABLE rooms ADD CONSTRAINT fk_rooms_hotels FOREIGN KEY (hotel_id) REFERENCES hotels(id) ON DELETE CASCADE;
ALTER TABLE rooms ADD CONSTRAINT fk_rooms_room_type FOREIGN KEY (room_type_id) REFERENCES room_type(id) ON DELETE CASCADE;
ALTER TABLE bookings ADD CONSTRAINT fk_bookings_rooms FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE;
ALTER TABLE bookings ADD CONSTRAINT fk_bookings_users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;


------------------------------------
--Initialize hotel booking metadata--
------------------------------------
INSERT INTO users ("name",email,phone_number,address) VALUES
    ('admin','admin@hrs.com','0115234345',NULL),
    ('user','user@hrs.com','0203456879',NULL);
INSERT INTO hotels ("name","location") VALUES
    ('Premier Pearl Hotel',0),
    ('Milan Homestay',0),
    ('IXORA Ho Tram',1),
    ('Melia Ho Tram',1);
INSERT INTO room_type ("type",description) VALUES
    (0,'Standard'),
    (1,'Premium');
INSERT INTO rooms (hotel_id,room_type_id,"name",price,status) VALUES
    (1,1,'The Level 1-bedroom Private Pool Villa',15.0,1),
    (1,2,'The Level 2-bedroom Private Pool Villa',30.0,1),
    (2,1,'Classic Double Ocean ',33.0,1),
    (2,2,'Signature Suite',180.0,1),
    (3,1,'Signature Suite Premium',300.0,1),
    (3,2,'Presidential Suite',150.0,1),
    (4,1,'Presidential Suite Premium',200.0,1),
    (4,2,'Classic Double Ocean Premium',70.0,1);




