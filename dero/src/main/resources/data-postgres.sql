insert into clinic (address, description, income, name, price_list) values ('Adresa1', 'opis1', 2000.0, 'Klinika1', 'price_list')
insert into clinic (address, description, income, name, price_list) values ('Adresa2', 'opis2', 2000.0, 'Klinika2', 'price_list2')
insert into user_table (address, city, country, email, first_name, jmbg, last_name,password, telephone) values ('Adresa1', 'Novi Sad', 'Srbija', 'pera@gmail.com', 'Pera', '1512998189687', 'Peric', 'sifra1', '066998585')
insert into clinic_center_administrator(log_first_time, id) values (false, 1)
insert into user_table (address, city, country, email, first_name, jmbg, last_name,password, telephone) values ('Adresa2', 'Novi Sad', 'Srbija', 'mika@gmail.com', 'Mika', '1512998186917', 'Mikic', 'sifra2', '066777585')
insert into clinic_center_administrator(log_first_time, id) values (false, 2)
insert into user_table (address, city, country, email, first_name, jmbg, last_name, password, telephone) values ('Adresa3', 'Beograd', 'Srbija', 'jova@gmail.com', 'Jovan', '1507991158987', 'Jovanovic', 'sifra3', '068978563')
insert into clinic_administrator (clinic_id, id) values (1, 3)
insert into user_table (address, city, country, email, first_name, jmbg, last_name, password, telephone) values ('Adresa4', 'Beograd', 'Srbija', 'zika@gmail.com', 'Zivan', '1508971158987', 'Zivanovic', 'sifra4', '062222563')
insert into clinic_administrator (clinic_id, id) values (1, 4)
insert into diagnosis (code, description, name) values ('I00', 'Opis', 'Reumatska groznica bez promena na srcu')
insert into diagnosis (code, description, name) values ('I01.2', 'Opis', 'Akutno reumatsko zapaljenje mišića srca')
insert into diagnosis (code, description, name) values ('N00', 'Opis', 'Akutni zapaljenjski bubrežni sindrom')
insert into diagnosis (code, description, name) values ('N00.4', 'Opis', 'Akutni zapaljenski bubrežni sindrom')
insert into medication (code, description, name) values ('A02BC01', 'Opis', 'OMEPRAZOL')
insert into medication (code, description, name) values ('A02BC04', 'Opis', 'DEMEPRAZOL')
insert into medication (code, description, name) values ('A02BC02', 'Opis', 'PULCET')
insert into medication (code, description, name) values ('A02BC03', 'Opis', 'LANSOPROL')
insert into medication (code, description, name) values ('A02BC05', 'Opis', 'EMANERA')
insert into user_table (address, city, country, email, first_name, jmbg, last_name, password, telephone) values ('Adresa5', 'Beograd', 'Srbija', 'isa2019klinicki.centar@gmail.com', 'Milos', '1508555558987', 'Milosevic', 'sifra5', '062111563')
insert into registration_request (verified, id) values (false, 5)
insert into user_table (address, city, country, email, first_name, jmbg, last_name, password, telephone) values ('Adresa6', 'Nevesinje', 'Republika Srpska', 'marija@gmail.com', 'Marija', '1592897368425', 'Marijanovic', 'sifra6', '068953685')
insert into medical_staff (clinic_id, vac_req_id, id) values (1, null, 6)
insert into doctor (specialised_type_id, id) values (null, 6)
insert into user_table (address, city, country, email, first_name, jmbg, last_name, password, telephone) values ('Adresa7', 'Nevesinje', 'Republika Srpska', 'jelena@gmail.com', 'Marija', '1592890000025', 'J', 'sifra7', '068950000')
insert into medical_staff (clinic_id, vac_req_id, id) values (1, null, 7)
insert into nurse (id) values (7)
insert into medical_record (blood_type, diopter, height, weight) values ('A', '0.2', 0.0, 0.0)
insert into user_table (address, city, country, email, first_name, jmbg, last_name, password, telephone) values ('Adresa8', 'Nevesinje', 'Republika Srpska', 'jeca@gmail.com', 'Marija', '1592890010025', 'Jovanovic', 'sifra8', '068951110')
insert into patient (med_rec_id, id) values (1, 8)