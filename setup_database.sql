-- database name: lms

flush privileges;

CREATE USER IF NOT EXISTS david@localhost IDENTIFIED BY '123';
CREATE USER IF NOT EXISTS s207885@localhost IDENTIFIED BY '123';
CREATE USER IF NOT EXISTS s207886@localhost IDENTIFIED BY '123';
CREATE USER IF NOT EXISTS s207898@localhost IDENTIFIED BY '123';
CREATE USER IF NOT EXISTS s208153@localhost IDENTIFIED BY '123';

GRANT ALL PRIVILEGES ON lms.* TO david@localhost;
GRANT ALL PRIVILEGES ON lms.* TO s207885@localhost;
GRANT ALL PRIVILEGES ON lms.* TO s207886@localhost;
GRANT ALL PRIVILEGES ON lms.* TO s207898@localhost;
GRANT ALL PRIVILEGES ON lms.* TO s208153@localhost;


use lms;

-- Table: BookInfo
CREATE TABLE bookinfo (
    ISBN VARCHAR(13) NOT NULL,
    title VARCHAR(200) NOT NULL,
    publisher VARCHAR(100) NOT NULL,
    edition INT NOT NULL,
    cost DECIMAL(7, 2) NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (ISBN)
);

-- Table: BookAuthor
CREATE TABLE bookauthor (
    ISBN VARCHAR(13) NOT NULL,
    author VARCHAR(100) NOT NULL,
    FOREIGN KEY (ISBN) REFERENCES bookinfo(ISBN),
    PRIMARY KEY (ISBN, author)
);

-- Table: UserInfo
CREATE TABLE userinfo (
    HKID VARCHAR(9) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    gender CHAR(1) NOT NULL,
    address VARCHAR(300) NOT NULL,
    PRIMARY KEY (HKID)
);

-- Table: Transaction
CREATE TABLE transaction (
    transaction_id INT NOT NULL AUTO_INCREMENT,
    date DATE NOT NULL,
    HKID VARCHAR(9) NOT NULL,
    FOREIGN KEY (HKID) REFERENCES userinfo(HKID),
    PRIMARY KEY (transaction_id)
);


-- Table: TransactionDetail
CREATE TABLE transactiondetail (
    detail_id INT NOT NULL AUTO_INCREMENT,
    transaction_id INT NOT NULL,
    ISBN VARCHAR(13) NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    renewed_times INT NOT NULL,
    FOREIGN KEY (ISBN) REFERENCES bookinfo(ISBN),
    FOREIGN KEY (transaction_id) REFERENCES transaction(transaction_id),
    PRIMARY KEY (detail_id)
);



-- Initial data: BookInfo
INSERT INTO bookinfo VALUES ('9789865024338', 'A Tour of C++ 中文版', '碁峰資訊股份有限公司', 2, 160, 1);
INSERT INTO bookinfo VALUES ('9789863475705', 'Java SE 8與Android 5.x程式設計範例教本（附Java和Android範例專案/附光碟）', '碁峰資訊股份有限公司', 1, 180, 1);
INSERT INTO bookinfo VALUES ('9789888242931', 'HKDSE CHEMISTRY A Modern View 4A', 'Aristo Educational Press Ltd.', 2, 258.5, 1);

-- Initial data: BookAuthor
INSERT INTO bookauthor VALUES ('9789865024338', 'Bjarne Stroustrup');
INSERT INTO bookauthor VALUES ('9789863475705', '陳會安');
INSERT INTO bookauthor VALUES ('9789888242931', 'E. Cheng');
INSERT INTO bookauthor VALUES ('9789888242931', 'J. Chow');
INSERT INTO bookauthor VALUES ('9789888242931', 'Y.F. Chow');
INSERT INTO bookauthor VALUES ('9789888242931', 'A. Kai');
INSERT INTO bookauthor VALUES ('9789888242931', 'S.L. Lee');
INSERT INTO bookauthor VALUES ('9789888242931', 'W.H. Wong');
