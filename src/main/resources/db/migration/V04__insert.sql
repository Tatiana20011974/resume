INSERT INTO Employee (id, name, image, telephon, mail)
VALUES
    (1,'Tanya','/images/image.jpg',3742934435,'rsfhgg@mail.ru' ),
    (2,'Masha','/images/image.jpg',293742465,'sfghg@mail.ru '),
    (3,'Anna','/images/image.jpg',2937346777,'seg@mail.ru' ),
    (4,'Ira','/images/image.jpg',29374294565,'cnvneg@mail.ru' );

INSERT INTO Project(id, name, description, id_employee)
VALUES
    (1,'Masha','Java Developer',1);

INSERT INTO Education (id, yearStart, yearEnd, nameEducation, degree, id_employee)
VALUES
    (1,2005,2012,'BGU','BACALAVR',2),
    (2,2004,2016,'BGU','BACALAVR',1),
    (3,2007,2018,'BGUIR','PROFESSOR',1);
