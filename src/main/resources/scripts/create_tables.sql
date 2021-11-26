CREATE TABLE employee
(
    id          smallint     NOT NULL,
    name        varchar(12)  NOT NULL,
    surname     varchar(20)  NOT NULL,
    patronymic  varchar(25)  NOT NULL,
    role        smallint     NOT NULL,
    description varchar(11)  NOT NULL,
    login       varchar(25)  NOT NULL,
    password    varchar(256) NOT NULL,
    CONSTRAINT PK_employee PRIMARY KEY (id, login)
);
CREATE TABLE product
(
    id              smallint    NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1),
    name            varchar(50) NOT NULL,
    manager_id      smallint    NOT NULL,
    product_type_id smallint    NOT NULL,
    provider_id     smallint    NOT NULL,
    CONSTRAINT PK_product PRIMARY KEY (id),
    CONSTRAINT FK_product_employee FOREIGN KEY (manager_id) REFERENCES employee (id),
    CONSTRAINT FK_product_product_type FOREIGN KEY (product_type_id) REFERENCES product_type (id),
    CONSTRAINT FK_product_provider FOREIGN KEY (provider_id) REFERENCES provider (id)
);
CREATE TABLE arrival
(
    doc        varchar(7) NOT NULL,
    count      smallint   NOT NULL,
    date       date       NOT NULL,
    product_id smallint   NOT NULL,
    price      real       NOT NULL,
    user_id    smallint   NOT NULL,
    CONSTRAINT PK_arrival PRIMARY KEY (doc, product_id),
    CONSTRAINT FK_arrival_product FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT FK_arrival_employee FOREIGN KEY (user_id) REFERENCES employee (id)
);
CREATE TABLE product_type
(
    id   smallint    NOT NULL,
    name varchar(20) NOT NULL,
    CONSTRAINT PK_product_type PRIMARY KEY (id)
);
CREATE TABLE provider
(
    id   smallint    NOT NULL,
    name varchar(50) NOT NULL,
    CONSTRAINT PK_provider PRIMARY KEY (id)
);
CREATE TABLE department
(
    id   smallint    NOT NULL,
    name varchar(10) NOT NULL,
    CONSTRAINT PK_department PRIMARY KEY (id)
);
CREATE TABLE archive
(
    month      date     NOT NULL,
    product_id smallint NOT NULL,
    count      smallint NOT NULL,
    CONSTRAINT PK_archive PRIMARY KEY (month, product_id),
    CONSTRAINT FK_archive_product FOREIGN KEY (product_id) REFERENCES product (id)
);
CREATE TABLE consumption
(
    id             smallint NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1),
    count          smallint NOT NULL,
    date           date     NOT NULL,
    destination_id smallint NOT NULL,
    product_id     smallint NOT NULL,
    CONSTRAINT PK_consumption PRIMARY KEY (id),
    CONSTRAINT FK_consumption_product FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT FK_consumption_department FOREIGN KEY (destination_id) REFERENCES department (id)
);
CREATE TABLE need
(
    month          date     NOT NULL,
    product_id     smallint NOT NULL,
    count          smallint NOT NULL,
    destination_id smallint NOT NULL,
    CONSTRAINT PK_need PRIMARY KEY (month, product_id),
    CONSTRAINT FK_need_product FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT FK_need_department FOREIGN KEY (destination_id) REFERENCES department (id)
);
CREATE TABLE write_off
(
    id         smallint NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1),
    date       date     NOT NULL,
    product_id smallint NOT NULL,
    count      smallint NOT NULL,
    user_id    smallint NOT NULL,
    CONSTRAINT PK_write_off PRIMARY KEY (id),
    CONSTRAINT FK_write_off_product FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT FK_write_off_employee FOREIGN KEY (user_id) REFERENCES employee (id)
);


