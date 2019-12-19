SET TIME ZONE 'UTC';

insert into clinic (address, description, grade, income, name, price_list) values ('Luja Braja, Novi Sad', 'opis1', 4.3, 2000.0, 'Urgentni Centar', 'price_list');
insert into clinic (address, description, grade, income, name, price_list) values ('Adresa2', 'opis2', 2.9, 2000.0, 'Klinika2', 'price_list2');

insert into medical_record (blood_type, diopter, height, weight) values ('A', '0.2', 0.0, 0.0);
insert into medical_record (blood_type, diopter, height, weight) VALUES ('A', '-1', 180, 80);
insert into medical_record (blood_type, diopter, height, weight) VALUES ('0', '0.5', 170, 90);

insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name,password, telephone) values ('Adresa1', 'Novi Sad', 'Srbija', 'pera@gmail.com', true, 'Pera', '1512998189687', 'Peric', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '066998585');
insert into clinic_center_administrator(log_first_time, id) values (false, 1);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name,password, telephone) values ('Adresa2', 'Novi Sad', 'Srbija', 'mika@gmail.com', true, 'Mika', '1512998186917', 'Mikic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '066777585');
insert into clinic_center_administrator(log_first_time, id) values (true, 2);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Adresa3', 'Beograd', 'Srbija', 'jova@gmail.com', true, 'Jovan', '1507991158987', 'Jovanovic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '068978563');
insert into clinic_administrator (clinic_id, id) values (1, 3);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Adresa4', 'Beograd', 'Srbija', 'zika@gmail.com', true, 'Zivan', '1508971158987', 'Zivanovic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '062222563');
insert into clinic_administrator (clinic_id, id) values (1, 4);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Adresa5', 'Beograd', 'Srbija', 'isa2019klinicki.centar@gmail.com', true, 'Milos', '1508555558987', 'Milosevic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '062111563');
insert into registration_request (verified, id) values (false, 5);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Adresa6', 'Nevesinje', 'Republika Srpska', 'marija@gmail.com', true, 'Marija', '1592897368425', 'Marijanovic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '068953685');
insert into medical_staff (clinic_id, vac_req_id, id) values (1, null, 6);
insert into doctor (specialised_type_id, id, grade) values (null, 6, 4.0);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Adresa7', 'Nevesinje', 'Republika Srpska', 'jelena@gmail.com', true, 'Marija', '1592890000025', 'J', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '068950000');
insert into medical_staff (clinic_id, vac_req_id, id) values (1, null, 7);
insert into nurse (id) values (7);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Bulevar despota Stefana', 'Novi Sad', 'Republika Srbija', 'mica@gmail.com', true, 'Milica', '1592812341234', 'Injac', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689504040');
insert into medical_staff (clinic_id, vac_req_id, id) values (1, null, 8);
insert into nurse (id) values (8);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Adresa8', 'Nevesinje', 'Republika Srpska', 'jeca@gmail.com', true, 'Marija', '1592891011025', 'Jovanovic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689513110');
insert into patient (med_rec_id, id) values (1, 9);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Safarikova', 'Novi Sad', 'Republika Srbija', 'marko@gmail.com', true, 'Marko', '1592811110025', 'Jovic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689511210');
insert into patient (med_rec_id, id) values (2, 10);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Sekspirova', 'Novi Sad', 'Republika Srbija', 'marko123@gmail.com', true, 'Marko', '1592812110025', 'Jovic', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689511230');
insert into medical_staff(id, clinic_id, vac_req_id) values (11, 1, null);
insert into doctor (id, specialised_type_id, grade) values (11, null, 3.4);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Heroja pinkija', 'Novi Sad', 'Republika Srbija', 'mirko@gmail.com', true, 'Mirko', '1592813110025', 'Moric', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689511240');
insert into medical_staff(id, clinic_id, vac_req_id) values (12, 1, null);
insert into doctor (id, specialised_type_id, grade) values (12, null, 2.9);
insert into user_table (address, city, country, email, enabled, first_name, jmbg, last_name, password, telephone) values ('Veselina Maslese', 'Novi Sad', 'Republika Srbija', 'vesko@gmail.com', true, 'Vesko', '1592814110025', 'Loric', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', '0689511250');
insert into medical_staff(id, clinic_id, vac_req_id) values (13, 1, null);
insert into doctor (id, specialised_type_id, grade) values (13, null, 4.8);

insert into diagnosis (code, description, name) values ('I00', 'Opis', 'Reumatska groznica bez promena na srcu');
insert into diagnosis (code, description, name) values ('I01.2', 'Opis', 'Akutno reumatsko zapaljenje mišića srca');
insert into diagnosis (code, description, name) values ('N00', 'Opis', 'Akutni zapaljenjski bubrežni sindrom');
insert into diagnosis (code, description, name) values ('N00.4', 'Opis', 'Akutni zapaljenski sindrom');

insert into medication (code, description, name) values ('A02BC01', 'Opis', 'OMEPRAZOL');
insert into medication (code, description, name) values ('A02BC04', 'Opis', 'DEMEPRAZOL');
insert into medication (code, description, name) values ('A02BC02', 'Opis', 'PULCET');
insert into medication (code, description, name) values ('A02BC03', 'Opis', 'LANSOPROL');
insert into medication (code, description, name) values ('A02BC05', 'Opis', 'EMANERA');

insert into prescription (certified, doctor_id, nurse_id) VALUES (false, 11, 7);
insert into prescription (certified, doctor_id, nurse_id) VALUES (true, 12, 7);
insert into prescription (certified, doctor_id, nurse_id) VALUES (false, 12, 7);

insert into rooms_table (name, number, clinic_id) values ('Soba 1', 1, 1);
insert into rooms_table (name, number, clinic_id) values ('Soba 2', 2, 1);
insert into examination_room (id) values (1);
insert into operation_room (id) values (2);

insert into type_of_examination (description, name) values ('Gleda ti oci', 'oftamolog');

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

insert into examination (date, discount, price, report, clinic_id, type_id, doctor_id, er_id, med_rec_id, nurse_id, patient_id, prescription_id, diagnosis_id) VALUES ('2019-12-10T15:43:39Z', '0', '900', 'bolestan', 1, 1, 11, 1, 3, 7, 10, null, 1);
insert into examination (date, discount, price, report, clinic_id, type_id, doctor_id, er_id, med_rec_id, nurse_id, patient_id, prescription_id, diagnosis_id) VALUES ('2019-12-10T15:43:39Z', '0', '500', 'nije bolestan', 1, 1, 12, 1, 3, 7, 9, null, 2);
insert into examination (date, discount, price, report, clinic_id, type_id, doctor_id, er_id, med_rec_id, nurse_id, patient_id, prescription_id, diagnosis_id) VALUES ('2019-12-12T15:43:39Z', '0', '600', 'nije bolestan', 1, 1, 12, 1, 3, 7, 9, null, 3);

insert into prescriptions_medication (prescription_id, medication_id) values (1, 2);
insert into prescriptions_medication (prescription_id, medication_id) values (1, 3);
insert into prescriptions_medication (prescription_id, medication_id) values (1, 4);

insert into prescriptions_medication (prescription_id, medication_id) values (3, 1);
insert into prescriptions_medication (prescription_id, medication_id) values (3, 3);
insert into prescriptions_medication (prescription_id, medication_id) values (3, 5);