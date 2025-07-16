drop table project cascade;
drop table employee cascade;
drop table education cascade;

CREATE TABLE IF NOT EXISTS Employee
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(30) NOT NULL,
    image    VARCHAR(30) NOT NULL,
    telephon VARCHAR(30) NOT NULL,
    mail     VARCHAR(30) NOT NULL
    );
CREATE TABLE IF NOT EXISTS Project
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(30) NOT NULL,
    description VARCHAR(30) NOT NULL,
    id_employee BIGINT      NOT NULL,
    FOREIGN KEY (id_employee) REFERENCES Employee (id)
    );
CREATE TABLE IF NOT EXISTS Education
(
    id            SERIAL PRIMARY KEY,
    yearStart     INT         NOT NULL,
    yearEnd       INT         NOT NULL,
    nameEducation VARCHAR(30) NOT NULL,
    degree        VARCHAR(30) NOT NULL,
    id_employee  BIGINT      NOT NULL,
    FOREIGN KEY (id_employee) REFERENCES Employee (id)
    );