INSERT INTO manufacturetest.public.department
    (id, name)
VALUES (1, 'Цех №1'),
       (2, 'Цех №2');

INSERT INTO manufacturetest.public.employee
    (id, name, surname, patronymic, role, description, login, password)
VALUES ('1', 'Петр', 'Коваль', 'Илларионович', 2, 'Снабжение', 'petrKoval',
        '$2a$10$pQYIVcxmTlUIgjBGQtR4a.zlbr.cjR3XRf/UL5/YWN/XCOyRxghr6'),
       ('2', 'Жанна', 'Василюк', 'Геннадьевна', 1, 'Бухгалтер', 'jannaVasiluk',
        '$2a$10$gKcGkeAWdp3KftVYY9rTWuCkQe8R.T3KDqfFaRcxNNqeUKkdD4oFK'),
       ('3', 'Лариса', 'Бортник', 'Антоновна', 3, 'Кладовщик', 'larisaBortnik',
        '$2a$10$PeJIXerWZwWD3umyUdAAwOFJhKJcXe37MCA8HnS.aCL.B1PXF54vq');

INSERT INTO manufacturetest.public.product_type
    (id, name)
VALUES (1, 'прочее');

INSERT INTO manufacturetest.public.provider
    (id, name)
VALUES (1, 'ОАО');

INSERT INTO manufacturetest.public.product
    (name, manager_id, provider_id, product_type_id)
VALUES ('ВТУЛКА 4040', 1, 1, 1),
       ('ГАЙКА М20х1.5', 1, 1, 1);

INSERT INTO manufacturetest.public.arrival
    (doc, count, date, product_id, price, user_id)
VALUES ('1111111', 50, '2021-02-12', 1, 0.5, 3)
     , ('2222222', 50, '2021-02-22', 1, 0.5, 3)
     , ('3333333', 50, '2021-03-12', 1, 0.5, 3)
     , ('4444444', 50, '2021-03-22', 1, 0.5, 3)
     , ('5555555', 300, '2021-02-01', 2, 1, 3)
     , ('6666666', 300, '2021-02-11', 2, 1, 3)
     , ('7777777', 300, '2021-03-01', 2, 1, 3)
     , ('8888888', 300, '2021-03-11', 2, 1, 3);

INSERT INTO manufacturetest.public.need
    (month, product_id, count, destination_id)
VALUES ('2021.02.01', 1, 90, 1)
     , ('2021.02.01', 2, 600, 1)
     , ('2021.03.01', 1, 90, 1)
     , ('2021.03.01', 2, 500, 1);

INSERT INTO manufacturetest.public.consumption
    (count, date, destination_id, product_id)
VALUES (50, '2021-02-14', 1, 1)
     , (50, '2021-02-26', 1, 1)
     , (90, '2021-03-23', 1, 1)
     , (100, '2021-02-02', 1, 2)
     , (100, '2021-02-03', 1, 2)
     , (100, '2021-02-04', 1, 2)
     , (300, '2021-02-26', 1, 2)
     , (500, '2021-03-12', 1, 2);
