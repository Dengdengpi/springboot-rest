insert into user_details(id, birth_date, name)
values (10001, current_date(), 'Terry'),
(10002, current_date(), 'Bobbie');

insert into post(id, description, user_id) values
(1, 'hello world', 10001),
(2, 'yo im bob', 10002),
(3,'say hiiii', 10001),
(4,'whats up', 10002);