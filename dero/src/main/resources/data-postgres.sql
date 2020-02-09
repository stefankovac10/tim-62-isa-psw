SET TIME ZONE 'UTC';

--DIJAGNOZA
insert into diagnosis (code, description, name) values ('I00', 'Opis', 'Reumatska groznica bez promena na srcu');
insert into diagnosis (code, description, name) values ('I01.2', 'Opis', 'Akutno reumatsko zapaljenje mišića srca');
insert into diagnosis (code, description, name) values ('N00', 'Opis', 'Akutni zapaljenjski bubrežni sindrom');
insert into diagnosis (code, description, name) values ('N00.4', 'Opis', 'Upala pluca');
insert into diagnosis (code, description, name) values ('N00.5', 'Opis', 'Korona virus');
insert into diagnosis (code, description, name) values ('N00.6', 'Opis', 'Hroncni bronhitis');
insert into diagnosis (code, description, name) values ('N00.7', 'Opis', 'AIDS');
insert into diagnosis (code, description, name) values ('N00.8', 'Opis', 'Upala sinusa');
insert into diagnosis (code, description, name) values ('N00.9', 'Opis', 'Prelom kljucne kosti');
insert into diagnosis (code, description, name) values ('N00.10', 'Opis', 'Iscasenje skocnog zgloba');
insert into diagnosis (code, description, name) values ('N00.11', 'Opis', 'Sizofrenija');
insert into diagnosis (code, description, name) values ('N00.12', 'Opis', 'Bipolarni poremecaj');
insert into diagnosis (code, description, name) values ('N00.13', 'Opis', 'Lupus');
insert into diagnosis (code, description, name) values ('N00.14', 'Opis', 'Rabies');

--LIJEKOVI
insert into medication (code, description, name) values ('A02BC01', 'Opis', 'OMEPRAZOL');
insert into medication (code, description, name) values ('A02BC04', 'Opis', 'DEMEPRAZOL');
insert into medication (code, description, name) values ('A02BC02', 'Opis', 'PULCET');
insert into medication (code, description, name) values ('A02BC03', 'Opis', 'LANSOPROL');
insert into medication (code, description, name) values ('A02BC05', 'Opis', 'EMANERA');
insert into medication (code, description, name) values ('A02BC06', 'Opis', 'MOMENSA');
insert into medication (code, description, name) values ('A02BC07', 'Opis', 'TRACHISAN');
insert into medication (code, description, name) values ('A02BC08', 'Opis', 'ISLA');
insert into medication (code, description, name) values ('A02BC09', 'Opis', 'DEFRINOL FORTE');
insert into medication (code, description, name) values ('A03BC01', 'Opis', 'ENTEROBIOTIK');
insert into medication (code, description, name) values ('A03BC02', 'Opis', 'BRUFEN');
insert into medication (code, description, name) values ('A03BC03', 'Opis', 'LEVOPRONT');
insert into medication (code, description, name) values ('A03BC04', 'Opis', 'PARACETAMOL');
insert into medication (code, description, name) values ('A03BC05', 'Opis', 'ANALGIN');

--KLINIKE
insert into clinic (address, description, grade, income, name, price_list) values ('Adresaq', 'opis1', 4.3, 2000.0, 'Klinika1', 'price_list');
insert into clinic (address, description, grade, income, name, price_list) values ('Adresa2', 'opis2', 2.9, 2000.0, 'Klinika2', 'price_list2');
insert into clinic (address, description, grade, income, name, price_list) values ('Bulevar Evrope BB', 'Privatna poliklinika', 4.1, 3500.0, 'Poliklinika Galetic', 'price_list2');
insert into clinic (address, description, grade, income, name, price_list) values ('Dusana Vasiljeva 4', 'Privatna poliklinika', 4.1, 4000.0, 'Poliklinika Maric', 'price_list2');
insert into clinic (address, description, grade, income, name, price_list) values ('Bulevar Evrope BB', 'Privatna poliklinika', 3.4, 1234.0, 'Poliklinika Atina', 'price_list2');
insert into clinic (address, description, grade, income, name, price_list) values ('Kosovska 26', 'Privatna poliklinika', 4.23, 3800.0, 'Poliklinika Novakov', 'price_list2');
insert into clinic (address, description, grade, income, name, price_list) values ('Bulevar Mihajla Pupina 25', 'Privatna poliklinika', 4.11, 1500.0, 'NK Poliklinika', 'price_list2');
insert into clinic (address, description, grade, income, name, price_list) values ('Futoska 121', 'Institut za javno zdravlje Vojvodine', 4.2, 1550.0, 'Higijenski zavod', 'price_list2');
insert into clinic (address, description, grade, income, name, price_list) values ('Hajduk Veljkova', 'Klinicki centar Vojvodine', 4.00, 1000.0, 'Klinika za neurologiju', 'price_list2');
insert into clinic (address, description, grade, income, name, price_list) values ('Hajduk Veljkova', 'Klinicki centar Vojvodine', 3.89, 1500.0, 'Klinika za ocne bolesti', 'price_list2');
insert into clinic (address, description, grade, income, name, price_list) values ('Hajduk Veljkova', 'Klinicki centar Vojvodine', 4.11, 10000.0, 'Klinika za psihijatriju', 'price_list2');
insert into clinic (address, description, grade, income, name, price_list) values ('Hajduk Veljkova', 'Klinicki centar Vojvodine', 4.11, 2300.0, 'Klinika za hematologiju', 'price_list2');


insert into type_of_examination (description, name, price) values ('Slusa ti pluca', 'pulmolog', 2000);  -- id 1
insert into type_of_examination (description, name, price) values ('Slusa ti srce', 'kardiolog', 2500);  -- id 2
insert into type_of_examination (description, name, price) values ('Gleda ti oci', 'oftamolog', 3000);   -- id 3

insert into medical_record (blood_type, diopter, height, weight) values ('A', '0.2', 0.0, 0.0);
insert into medical_record (blood_type, diopter, height, weight) VALUES ('A', '-1', 180, 80);
insert into medical_record (blood_type, diopter, height, weight) VALUES ('0', '0.5', 170, 90);

insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name,password, telephone) values ('Adresa1', 'Novi Sad', 'Srbija', 'pera@gmail.com', true, 'Pera', '1512998189687', 'Peric', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '066998585'); -- id 1
insert into clinic_center_administrator(log_first_time, id) values (false, 1);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name,password, telephone) values ('Adresa2', 'Novi Sad', 'Srbija', 'mika@gmail.com', true, 'Mika', '1512998186917', 'Mikic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '066777585');
insert into clinic_center_administrator(log_first_time, id) values (true, 2);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Adresa3', 'Beograd', 'Srbija', 'jova@gmail.com', true, 'Jovan', '1507991158987', 'Jovanovic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '068978563');
insert into clinic_administrator (clinic_id, id) values (1, 3);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Adresa4', 'Beograd', 'Srbija', 'zika@gmail.com', true, 'Zivan', '1508971158987', 'Zivanovic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '062222563');
insert into clinic_administrator (clinic_id, id) values (1, 4);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Adresa5', 'Beograd', 'Srbija', 'isa2019klinicki.centar@gmail.com', true, 'Milos', '1508555558987', 'Milosevic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '062111563');
insert into registration_request (verified, id) values (false, 5);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Adresa6', 'Nevesinje', 'Republika Srpska', 'marija@gmail.com', true, 'Marija', '1592897368425', 'Marijanovic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '068953685'); -- id 6
insert into medical_staff (clinic_id, id) values (1, 6);
insert into doctor (specialised_type_id, id, grade) values (1, 6, 4.0);

--SESTRE
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Adresa7', 'Nevesinje', 'Republika Srpska', 'jelena@gmail.com', true, 'Marija', '1592890000025', 'J', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '068950000'); -- id 7
insert into medical_staff (clinic_id, id) values (1, 7);
insert into nurse (id) values (7);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Bulevar despota Stefana', 'Novi Sad', 'Republika Srbija', 'mica@gmail.com', true, 'Milica', '1592812341234', 'Injac', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689504040'); -- id 8
insert into medical_staff (clinic_id, id) values (1, 8);
insert into nurse (id) values (8);

insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Adresa8', 'Nevesinje', 'Republika Srpska', 'jeca@gmail.com', true, 'Marija', '1592891011025', 'Jovanovic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689513110'); -- id 9
insert into patient (med_rec_id, id) values (1, 9);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Safarikova', 'Novi Sad', 'Republika Srbija', 'marko@gmail.com', true, 'Marko', '1592811110025', 'Jovic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689511210');
insert into patient (med_rec_id, id) values (2, 10);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Sekspirova', 'Novi Sad', 'Republika Srbija', 'marko123@gmail.com', true, 'Marko', '1592812110025', 'Jovic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689511230');
insert into medical_staff(id, clinic_id) values (11, 3);
insert into doctor (id, specialised_type_id, grade) values (11, 2, 3.4);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Heroja pinkija', 'Novi Sad', 'Republika Srbija', 'mirko@gmail.com', true, 'Mirko', '1592813110025', 'Moric', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689511240');
insert into medical_staff(id, clinic_id) values (12, 3);
insert into doctor (id, specialised_type_id, grade) values (12, 1, 2.9);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Veselina Maslese', 'Novi Sad', 'Republika Srbija', 'vesko@gmail.com', true, 'Vesko', '1592814110025', 'Loric', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689511250');
insert into medical_staff(id, clinic_id) values (13, 1);
insert into doctor (id, specialised_type_id, grade) values (13, 3, 4.8);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Safarikova', 'Novi Sad', 'Republika Srbija', 'mjovic@gmail.com', true, 'Mirko', '1592811220025', 'Jovic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689544210');
insert into patient (med_rec_id, id) values (2, 14);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Safarikova', 'Novi Sad', 'Republika Srbija', 'jmilan@gmail.com', true, 'Milan', '1592811110033', 'Jovanovic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0686661210');
insert into patient (med_rec_id, id) values (2, 15);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Safarikova', 'Novi Sad', 'Republika Srbija', 'yayatoure@gmail.com', true, 'Yaya', '1592815555025', 'Toure', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689881210'); -- id 16
insert into patient (med_rec_id, id) values (2, 16);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Safarikova', 'Novi Sad', 'Republika Srbija', 'kolotoure@gmail.com', true, 'Kolo', '1592811110099', 'Toure', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689511299');
insert into patient (med_rec_id, id) values (2, 17);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Safarikova', 'Novi Sad', 'Republika Srbija', 'giggs@gmail.com', true, 'Ryan', '2292811110025', 'Giggs', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689123210');
insert into patient (med_rec_id, id) values (2, 18);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Safarikova', 'Novi Sad', 'Republika Srbija', 'slippyg@gmail.com', true, 'Slippy', '2392811110025', 'Gerrard', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0655511210');
insert into patient (med_rec_id, id) values (2, 19);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Safarikova', 'Novi Sad', 'Republika Srbija', 'drose@gmail.com', true, 'Derrick', '1592844440025', 'Rose', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0644441210');
insert into patient (med_rec_id, id) values (2, 20);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Safarikova', 'Novi Sad', 'Republika Srbija', 'bale@gmail.com', true, 'Christian', '1592811111125', 'Bale', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689589210');
insert into patient (med_rec_id, id) values (2, 21);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Safarikova', 'Novi Sad', 'Republika Srbija', 'bwayne@gmail.com', true, 'Bruce', '1234567891012', 'Wayne', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689534710');
insert into patient (med_rec_id, id) values (2, 22);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Safarikova', 'Novi Sad', 'Republika Srbija', 'veinruni@gmail.com', true, 'Vein', '1592811114321', 'Runi', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689511261');
insert into patient (med_rec_id, id) values (2, 23);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Safarikova', 'Novi Sad', 'Republika Srbija', 'jjzmaj@gmail.com', true, 'Jovan', '1592811195225', 'Jovanovic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689592210'); -- id 24
insert into patient (med_rec_id, id) values (2, 24);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Safarikova', 'Novi Sad', 'Republika Srbija', 'jovanka@gmail.com', true, 'Jovanka', '1597051110025', 'Broz', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689584010');
insert into patient (med_rec_id, id) values (2, 25);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Safarikova', 'Novi Sad', 'Republika Srbija', 'maksicm@gmail.com', true, 'Milenko', '1592811684025', 'Maksic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689519510'); -- id 26
insert into patient (med_rec_id, id) values (2, 26);

insert into vacation_request (accepted, start_date, end_date, clinic_id, staff_id) values (false, '2019-12-25T15:43:39Z', '2019-12-30T15:43:39Z', 1, 13);
insert into vacation_request (accepted, start_date, end_date, clinic_id, staff_id) values (false, '2019-12-23T15:43:39Z', '2019-12-31T15:43:39Z', 1, 12);


insert into prescription (certified, doctor_id, nurse_id) VALUES (false, 12, null);
insert into prescription (certified, doctor_id, nurse_id) VALUES (false, 12, null);
insert into prescription (certified, doctor_id, nurse_id) VALUES (false, 11, null);

insert into rooms_table (name, number, clinic_id) values ('Soba 1', 1, 1);
insert into rooms_table (name, number, clinic_id) values ('Soba 2', 2, 1);
insert into rooms_table (name, number, clinic_id) values ('Soba 3', 3, 1);
insert into rooms_table (name, number, clinic_id) values ('Soba 4', 4, 1);
insert into rooms_table (name, number, clinic_id) values ('Soba 5', 5, 1);
insert into rooms_table (name, number, clinic_id) values ('Soba 6', 6, 1);
insert into examination_room (id) values (1);
insert into operation_room (id) values (2);
insert into examination_room (id) values (3);
insert into examination_room (id) values (4);
insert into examination_room (id) values (5);
insert into examination_room (id) values (6);

insert into authorities (name) values ('ROLE_REQUEST');
insert into authorities (name) values ('ROLE_PATIENT');
insert into authorities (name) values ('ROLE_NURSE');
insert into authorities (name) values ('ROLE_DOCTOR');
insert into authorities (name) values ('ROLE_CCADMIN');
insert into authorities (name) values ('ROLE_CADMIN');

insert into user_authority (user_id, authority_id) values (1, 5);
insert into user_authority (user_id, authority_id) values (2, 5);
insert into user_authority (user_id, authority_id) values (3, 6);
insert into user_authority (user_id, authority_id) values (4, 6);
insert into user_authority (user_id, authority_id) values (5, 1);
insert into user_authority (user_id, authority_id) values (6, 4);
insert into user_authority (user_id, authority_id) values (7, 3);
insert into user_authority (user_id, authority_id) values (8, 3);
insert into user_authority (user_id, authority_id) values (9, 2);
insert into user_authority (user_id, authority_id) values (10, 2);
insert into user_authority (user_id, authority_id) values (11, 4);
insert into user_authority (user_id, authority_id) values (12, 4);
insert into user_authority (user_id, authority_id) values (13, 4);

insert into prescriptions_medication (prescription_id, medication_id) values (1, 2);
insert into prescriptions_medication (prescription_id, medication_id) values (1, 3);
insert into prescriptions_medication (prescription_id, medication_id) values (1, 4);

insert into prescriptions_medication (prescription_id, medication_id) values (2, 2);
insert into prescriptions_medication (prescription_id, medication_id) values (2, 1);
insert into prescriptions_medication (prescription_id, medication_id) values (2, 4);

insert into prescriptions_medication (prescription_id, medication_id) values (3, 1);
insert into prescriptions_medication (prescription_id, medication_id) values (3, 3);
insert into prescriptions_medication (prescription_id, medication_id) values (3, 5);

insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Jevrejska 12', 'Novi Sad', 'Republika Srbija', 'drnele@gmail.com', true, 'Dr Nele', '159281555555', 'Karajlic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689555555'); -- id 27
insert into medical_staff(id, clinic_id) values (27, 3);
insert into doctor (id, specialised_type_id, grade) values (27, 1, 4.8);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Ulica srpskih junaka', 'New Jersey', 'USA', 'drhaus@gmail.com', true, 'Gregory', '1594094110025', 'House', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689172250');
insert into medical_staff(id, clinic_id) values (28, 4);
insert into doctor (id, specialised_type_id, grade) values (28, 2, 4.8);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Sava Mala', 'Beooograd', 'Republika Srbija', 'sinisam@gmail.com', true, 'Sinisa', '1592500810025', 'Mali', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0639980250'); -- id 29
insert into medical_staff(id, clinic_id) values (29, 5);
insert into doctor (id, specialised_type_id, grade) values (29, 3, 4.8);


insert into appointment (start_date, duration, clinic_id) values ('2020-02-02T15:43:00Z000', 900000, 1);    -- id 1
insert into appointment (start_date, duration, clinic_id) values ('2020-02-02T15:43:00Z000', 1800000, 1);   -- id 2
insert into appointment (start_date, duration, clinic_id) values ('2020-02-02T15:55:00Z000', 1200000, 1);    -- id 3
insert into appointment (start_date, duration, clinic_id) values ('2020-10-24T15:43:00Z000', 1200000, 1);    -- id 4
insert into appointment (start_date, duration, clinic_id) values ('2020-12-01T11:43:00Z000', 1500000, 1);    -- id 5
insert into appointment (start_date, duration, clinic_id) values ('2020-09-18T07:43:00Z000', 900000, 1);    -- id 6
-- ograničićemo minimalno trajanje appointmenta na 15min, da ne bude baš 5min, pa sam promenio ovde koliko milisekundi imaju duration-i ... (900_000 ms = 15 min; 60_000 ms = 1min)

insert into examination_appointment (id) values (1);
insert into examination_appointment (id) values (2);
insert into examination_appointment (id) values (3);

insert into examination (discount, price, report, clinic_id, type_id, doctor_id, er_id, med_rec_id, nurse_id, patient_id, prescription_id, diagnosis_id, appointment_id) VALUES ('0', '900', 'bolestan', 1, 1, 11, 1, 2, 7, 10, null, 1, 1);
insert into examination (discount, price, report, clinic_id, type_id, doctor_id, er_id, med_rec_id, nurse_id, patient_id, prescription_id, diagnosis_id, appointment_id) VALUES ('0', '500', 'nije bolestan', 1, 1, 12, 1, 3, 7, 9, null, 2, 2);
insert into examination (discount, price, report, clinic_id, type_id, doctor_id, er_id, med_rec_id, nurse_id, patient_id, prescription_id, diagnosis_id, appointment_id) VALUES ('0', '600', 'nije bolestan', 1, 1, 12, 1, 3, 7, 9, null, 3, 3);



insert into appointment (start_date, duration, clinic_id) values ('2020-10-24T15:43:39Z', 600000, 1);   -- id 7
insert into appointment (start_date, duration, clinic_id) values ('2020-12-01T11:43:39Z', 720000, 1);   -- id 8
insert into appointment (start_date, duration, clinic_id) values ('2020-09-18T07:43:39Z', 920000, 1);   -- id 9


insert into examination_appointment (id, examination_id, examination_room_id) values (4, 1, 1);
insert into examination_appointment (id, examination_id, examination_room_id) values (5, 2, 1);
insert into examination_appointment (id, examination_id, examination_room_id) values (6, 3, 1);

insert into examination_request (doctor_id, patient_id, appointment_id) values (27, 24, 4);
insert into examination_request (doctor_id, patient_id, appointment_id) values (29, 25, 5);
insert into examination_request (doctor_id, patient_id, appointment_id) values (28, 22, 6);

--ZAHTJEV ZA REGISTRACIJU

insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('10th Street', 'London', 'UK', 'mikael@gmail.com', true, 'Mikael', '1234567891234', 'Arteta', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0000000001'); -- id 30
insert into registration_request (verified, id) values (false, 30);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('10th Street', 'New York', 'Republika Srpska', 'marijana@gmail.com', true, 'Marijana', '1234567891235', 'Marijanovic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0000000002');
insert into registration_request (verified, id) values (false, 31);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('11th Street', 'Boston', 'US', 'thierry@gmail.com', true, 'Thierry', '1234567891244', 'Henry', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0000000003');
insert into registration_request (verified, id) values (false, 32);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('12th Street', 'London', 'UK', 'henry@gmail.com', true, 'Henry', '1234567891236', 'Cavill', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0000000004');
insert into registration_request (verified, id) values (false, 33);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('12th Street', 'New York', 'US', 'novak@gmail.com', true, 'Novak', '1234567891237', 'Djokovic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0000000005');
insert into registration_request (verified, id) values (false, 34);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('13th Street', 'London', 'UK', 'rafael@gmail.com', true, 'Rafael', '1234567891238', 'Nadal', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0000000006');
insert into registration_request (verified, id) values (false, 35);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('14th Street', 'New York', 'US', 'roger@gmail.com', true, 'Roger', '1234567891239', 'Federer', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0000000007');
insert into registration_request (verified, id) values (false, 36);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('14th Street', 'Boston', 'US', 'dominic@gmail.com', true, 'Dominic', '1234567891240', 'Tiem', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0000000008');
insert into registration_request (verified, id) values (false, 37);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('16th Street', 'London', 'UK', 'tsitsipas@gmail.com', true, 'Stefanos', '1234567891241', 'Tsitsipas', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0000000009');
insert into registration_request (verified, id) values (false, 38);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('16th Street', 'New York', 'US', 'viktor@gmail.com', true, 'Viktor', '1234567891242', 'Troicki', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '00000000010'); -- id 39
insert into registration_request (verified, id) values (false, 39);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('15th Street', 'New York', 'US', 'struff@gmail.com', true, 'Jan-Lennard', '1234567891243', 'Struff', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '00000000011'); -- id 40
insert into registration_request (verified, id) values (false, 40);


---SESTRE
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Fruskogorska 25', 'Melburn', 'Australija', 'marija1@gmail.com', true, 'Marija', '1234567897896', 'Karan', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '000000012'); -- id 41
insert into medical_staff (clinic_id, id) values (2, 41);
insert into nurse (id) values (41);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Adolfa Hitlera 19/39', 'Munchen', 'Njemacka', 'ana@gmail.com', true, 'Ana', '1234567897895', 'Ivanovic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '000000013');
insert into medical_staff (clinic_id, id) values (2, 42);
insert into nurse (id) values (42);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Milosa Obilica 13/89', 'Ankara', 'Turska', 'tijana@gmail.com', true, 'Tijana', '1234567897893', 'Boskovic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '000000014');
insert into medical_staff (clinic_id, id) values (3, 43);
insert into nurse (id) values (43);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Beogradska 13', 'Tuzla', 'BIH', 'lepa@gmail.com', true, 'Lepa', '1234567897894', 'Brena', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '000000015'); -- id 44
insert into medical_staff (clinic_id, id) values (2, 44);
insert into nurse (id) values (44);

insert into user_authority (user_id, authority_id) values (44, 3);
insert into user_authority (user_id, authority_id) values (41, 3);
insert into user_authority (user_id, authority_id) values (42, 3);
insert into user_authority (user_id, authority_id) values (43, 3);

insert into operation (id, date, duration, clinic_id,med_rec_id, or_id, patient_id) values(1,'2020-02-10T12:00:00Z',null,1,1,2,9);
insert into operation (id, date, duration, clinic_id,med_rec_id, or_id, patient_id) values(2,'2020-02-11T13:00:00Z',null,1,2,2,10);
insert into operation (id, date, duration, clinic_id,med_rec_id, or_id, patient_id) values(3,'2020-02-11T15:00:00Z',null,1,2,2,14);


--insert into operations_doctors(operation_id, doctor_id) values (1,13);
--insert into operations_doctors(operation_id, doctor_id) values (1,12);
--insert into operations_doctors(doctor_id, operation_id) values (13,2);
--insert into operations_doctors(doctor_id, operation_id) values (12,2);
--insert into operations_doctors(doctor_id, operation_id) values (13,3);
--insert into operations_doctors(doctor_id, operation_id) values (12,3);
---userid:43

insert into examination_appointment (id, examination_id, examination_room_id) values (7, 1, 1); -- id 7
insert into examination_appointment (id, examination_id, examination_room_id) values (8, 2, 1);
insert into examination_appointment (id, examination_id, examination_room_id) values (9, 3, 1); -- id 9

insert into examination (discount, price, report, clinic_id, type_id, doctor_id, er_id, med_rec_id, nurse_id, patient_id, prescription_id, diagnosis_id, appointment_id) VALUES ('0', '1500', 'bolestan', 1, 2, 11, 1, 2, 7, 10, null, 1, 7);   -- id 4
insert into examination (discount, price, report, clinic_id, type_id, doctor_id, er_id, med_rec_id, nurse_id, patient_id, prescription_id, diagnosis_id, appointment_id) VALUES ('0', '2000', 'bolestan', 1, 2, 12, 1, 2, 7, 10, null, 2, 8);
insert into examination (discount, price, report, clinic_id, type_id, doctor_id, er_id, med_rec_id, nurse_id, patient_id, prescription_id, diagnosis_id, appointment_id) VALUES ('0', '2500', 'bolestan', 1, 3, 12, 1, 2, 7, 10, null, 3, 9);   -- id 6

-- Dr Nele (id == 27, spec_type_id == 1(pulmolog)), Klinika Galetic (id == 3), dana 14. februara 2020-e imao 6 pregleda u trajanju od 2h, tako da je popunio kvotu za ceo dan (radno vreme od 7h do 19h)
-- Zakljucak: Poliklinika Galetic i ako iskoci pacijentu koji hoce da zakaze pulmoloski pregled 14. februara, Dr Nele ne bi trebao biti dostupan
insert into appointment (start_date, end_date, duration, clinic_id) values ('2020-02-14T07:00:00Z000', '2020-02-14T09:00:00Z000', 7200000, 3);    -- id 10
insert into appointment (start_date, end_date, duration, clinic_id) values ('2020-02-14T09:00:00Z000', '2020-02-14T11:00:00Z000', 7200000, 3);
insert into appointment (start_date, end_date, duration, clinic_id) values ('2020-02-14T11:00:00Z000', '2020-02-14T13:00:00Z000', 7200000, 3);
insert into appointment (start_date, end_date, duration, clinic_id) values ('2020-02-14T13:00:00Z000', '2020-02-14T15:00:00Z000', 7200000, 3);
insert into appointment (start_date, end_date, duration, clinic_id) values ('2020-02-14T15:00:00Z000', '2020-02-14T17:00:00Z000', 7200000, 3);
insert into appointment (start_date, end_date, duration, clinic_id) values ('2020-02-14T17:00:00Z000', '2020-02-14T19:00:00Z000', 7200000, 3);    -- id 15
insert into examination_appointment (id, examination_id, examination_room_id) values (10, null, 1);
insert into examination_appointment (id, examination_id, examination_room_id) values (11, null, 1);
insert into examination_appointment (id, examination_id, examination_room_id) values (12, null, 1);
insert into examination_appointment (id, examination_id, examination_room_id) values (13, null, 1);
insert into examination_appointment (id, examination_id, examination_room_id) values (14, null, 1);
insert into examination_appointment (id, examination_id, examination_room_id) values (15, null, 1);
insert into examination (discount, price, report, clinic_id, type_id, doctor_id, er_id, med_rec_id, nurse_id, patient_id, prescription_id, diagnosis_id, appointment_id) VALUES ('0', '2500', 'bolestan', 3, 1, 27, 1, 2, 7, 10, null, 3, 10);   -- id 7
insert into examination (discount, price, report, clinic_id, type_id, doctor_id, er_id, med_rec_id, nurse_id, patient_id, prescription_id, diagnosis_id, appointment_id) VALUES ('0', '2500', 'bolestan', 3, 1, 27, 1, 2, 7, 14, null, 3, 11);
insert into examination (discount, price, report, clinic_id, type_id, doctor_id, er_id, med_rec_id, nurse_id, patient_id, prescription_id, diagnosis_id, appointment_id) VALUES ('0', '2500', 'bolestan', 3, 1, 27, 1, 2, 7, 15, null, 3, 12);
insert into examination (discount, price, report, clinic_id, type_id, doctor_id, er_id, med_rec_id, nurse_id, patient_id, prescription_id, diagnosis_id, appointment_id) VALUES ('0', '2500', 'bolestan', 3, 1, 27, 1, 2, 7, 16, null, 3, 13);
insert into examination (discount, price, report, clinic_id, type_id, doctor_id, er_id, med_rec_id, nurse_id, patient_id, prescription_id, diagnosis_id, appointment_id) VALUES ('0', '2500', 'bolestan', 3, 1, 27, 1, 2, 7, 17, null, 3, 14);
insert into examination (discount, price, report, clinic_id, type_id, doctor_id, er_id, med_rec_id, nurse_id, patient_id, prescription_id, diagnosis_id, appointment_id) VALUES ('0', '2500', 'bolestan', 3, 1, 27, 1, 2, 7, 18, null, 3, 15);   -- id 12
update examination_appointment set examination_id = 7 where id = 10;
update examination_appointment set examination_id = 8 where id = 11;
update examination_appointment set examination_id = 9 where id = 12;
update examination_appointment set examination_id = 10 where id = 13;
update examination_appointment set examination_id = 11 where id = 14;
update examination_appointment set examination_id = 12 where id = 15;

-- Dr Mirko Moric (id == 12, spec_type_id == 1(pulmolog)), Klinika Galetic (id == 3), dana 14. februara 2020-e imao 5 pregleda u trajanju od 2h (od 7h do 17h), tako da je slobodan od 17h do 19h
-- Zakljucak: Poliklinika Galetic ce iskociti pacijentu koji hoce da zakaze pulmoloski pregled 14. februara, dr Mirko Loric treba da je dostupan, Dr Nele ne bi trebao biti dostupan
insert into appointment (start_date, end_date, duration, clinic_id) values ('2020-02-14T07:00:00Z000', '2020-02-14T09:00:00Z000', 7200000, 3);    -- id 16
insert into appointment (start_date, end_date, duration, clinic_id) values ('2020-02-14T09:00:00Z000', '2020-02-14T11:00:00Z000', 7200000, 3);
insert into appointment (start_date, end_date, duration, clinic_id) values ('2020-02-14T11:00:00Z000', '2020-02-14T13:00:00Z000', 7200000, 3);
insert into appointment (start_date, end_date, duration, clinic_id) values ('2020-02-14T13:00:00Z000', '2020-02-14T15:00:00Z000', 7200000, 3);
insert into appointment (start_date, end_date, duration, clinic_id) values ('2020-02-14T15:00:00Z000', '2020-02-14T17:00:00Z000', 7200000, 3);      -- id 20
insert into examination_appointment (id, examination_id, examination_room_id) values (16, 1, 3);
insert into examination_appointment (id, examination_id, examination_room_id) values (17, 2, 3);
insert into examination_appointment (id, examination_id, examination_room_id) values (18, 3, 3);
insert into examination_appointment (id, examination_id, examination_room_id) values (19, 1, 3);
insert into examination_appointment (id, examination_id, examination_room_id) values (20, 2, 3);
insert into examination (discount, price, report, clinic_id, type_id, doctor_id, er_id, med_rec_id, nurse_id, patient_id, prescription_id, diagnosis_id, appointment_id) VALUES ('0', '2500', 'bolestan', 3, 1, 12, 1, 2, 7, 17, null, 3, 16);   -- id 13
insert into examination (discount, price, report, clinic_id, type_id, doctor_id, er_id, med_rec_id, nurse_id, patient_id, prescription_id, diagnosis_id, appointment_id) VALUES ('0', '2500', 'bolestan', 3, 1, 12, 1, 2, 7, 15, null, 3, 17);
insert into examination (discount, price, report, clinic_id, type_id, doctor_id, er_id, med_rec_id, nurse_id, patient_id, prescription_id, diagnosis_id, appointment_id) VALUES ('0', '2500', 'bolestan', 3, 1, 12, 1, 2, 7, 16, null, 3, 18);
insert into examination (discount, price, report, clinic_id, type_id, doctor_id, er_id, med_rec_id, nurse_id, patient_id, prescription_id, diagnosis_id, appointment_id) VALUES ('0', '2500', 'bolestan', 3, 1, 12, 1, 2, 7, 14, null, 3, 19);
insert into examination (discount, price, report, clinic_id, type_id, doctor_id, er_id, med_rec_id, nurse_id, patient_id, prescription_id, diagnosis_id, appointment_id) VALUES ('0', '2500', 'bolestan', 3, 1, 12, 1, 2, 7, 10, null, 3, 20);  -- id 17
update examination_appointment set examination_id = 13 where id = 16;
update examination_appointment set examination_id = 14 where id = 17;
update examination_appointment set examination_id = 15 where id = 18;
update examination_appointment set examination_id = 16 where id = 19;
update examination_appointment set examination_id = 17 where id = 20;