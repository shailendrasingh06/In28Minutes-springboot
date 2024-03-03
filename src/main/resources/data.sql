insert into user_details(id, birth_date, name)
values(1001, current_date, 'Shailendra');
insert into user_details(id, birth_date, name)
values(1002, current_date, 'Manom');
insert into user_details(id, birth_date, name)
values(1003, current_date, 'Ujala');

insert into post(id, description, user_id)
values(2001, 'I want to learn AWS', 1001);

insert into post(id, description, user_id)
values(2002, 'I want to learn GCP', 1002);

insert into post(id, description, user_id)
values(2003, 'I want to learn Azure', 1003);