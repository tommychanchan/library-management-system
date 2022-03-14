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

-- Table: UserType
CREATE TABLE usertype (
    type_id INT AUTO_INCREMENT,
    type_name VARCHAR(20) NOT NULL,
    max_books_borrow INT NOT NULL,
    max_days_borrow INT NOT NULL,
    debt_each_day DECIMAL(3, 1),
    PRIMARY KEY (type_id)
);

-- Table: UserInfo
CREATE TABLE userinfo (
    HKID VARCHAR(9) NOT NULL,
    type_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(90),
    phone CHAR(8),
    gender CHAR(1) NOT NULL,
    address VARCHAR(300) NOT NULL,
    FOREIGN KEY (type_id) REFERENCES usertype(type_id),
    PRIMARY KEY (HKID)
);

-- Table: Transaction
CREATE TABLE transaction (
    transaction_id INT AUTO_INCREMENT,
    HKID VARCHAR(9) NOT NULL,
    borrow_date DATE NOT NULL,
    paid BOOLEAN NOT NULL,
    FOREIGN KEY (HKID) REFERENCES userinfo(HKID),
    PRIMARY KEY (transaction_id)
);

-- Table: TransactionDetail
CREATE TABLE transactiondetail (
    detail_id INT AUTO_INCREMENT,
    transaction_id INT NOT NULL,
    ISBN VARCHAR(13) NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    FOREIGN KEY (ISBN) REFERENCES bookinfo(ISBN),
    FOREIGN KEY (transaction_id) REFERENCES transaction(transaction_id),
    PRIMARY KEY (detail_id)
);



-- Initial data: BookInfo
INSERT INTO bookinfo VALUES ('9789865024338', 'A Tour of C++ 中文版', '碁峰資訊股份有限公司', 2, 160, 3);
INSERT INTO bookinfo VALUES ('9789863475705', 'Java SE 8與Android 5.x程式設計範例教本（附Java和Android範例專案/附光碟）', '碁峰資訊股份有限公司', 1, 180, 2);
INSERT INTO bookinfo VALUES ('9789888242931', 'HKDSE CHEMISTRY A Modern View 4A', 'Aristo Educational Press Ltd.', 2, 258.5, 1);
INSERT INTO bookinfo VALUES ('9789888540129', 'HKDSE CHEMISTRY A Modern View 5 (Reprinted with minor amendments 2019)', 'Aristo Educational Press Ltd.', 2, 258.5, 4);
INSERT INTO bookinfo VALUES ('9789888540143', 'HKDSE CHEMISTRY A Modern View 7 (Reprinted with minor amendments 2019)', 'Aristo Educational Press Ltd.', 2, 258.5, 5);
INSERT INTO bookinfo VALUES ('9780195489576', '牛津進階英漢雙解詞典 Oxford Intermediate Learner\'s English-Chinese Dictionary', 'Oxford University Press', 4, 268, 3);
INSERT INTO bookinfo VALUES ('9789622880184', '承教小記', '華漢文化事業公司', 1, 42, 4);
INSERT INTO bookinfo VALUES ('9781401398033', 'Five People You Meet in Heaven (US)', 'Warner Book Incorporation', 1, 144, 3);
INSERT INTO bookinfo VALUES ('962070133X', '商務新詞典(縮印本)', '商務印書館(香港)有限公司', 1, 112, 2);
INSERT INTO bookinfo VALUES ('9789882054226', 'Handy Guide for HKDSE English (Papers 1, 2, 3 & 4)', 'Pilot Publishing Co. Ltd.', 1, 113, 2);
INSERT INTO bookinfo VALUES ('9789882119437', '目送', '天地圖書有限公司', 1, 128, 5);
INSERT INTO bookinfo VALUES ('9789620703973', 'The Little Prince 小王子', '商務印書館(香港)有限公司', 1, 78, 2);
INSERT INTO bookinfo VALUES ('9789888467464', 'HKDSE Exam Series Mathematics (Extended Part) Mock Exam Papers M2 (18/19)', 'Pan Lloyds Publishers Ltd.', 1, 100, 0);
INSERT INTO bookinfo VALUES ('9789882364523', 'New Horizon Liberal Studies Energy Technology and the Environment', '香港教育圖書有限公司', 3, 271, 1);
INSERT INTO bookinfo VALUES ('9789881250919', '透視文言文 練習', 'HK Joint-Us Press', 1, 106, 23);
INSERT INTO bookinfo VALUES ('9789882398085', 'Junior Secondary Mathematics in Action 2A (Modular)', 'Pearson Education Limited', 1, 248, 12);
INSERT INTO bookinfo VALUES ('9789627452485', 'Integrated Music 1', 'Hong Kong Music', 2, 194, 20);
INSERT INTO bookinfo VALUES ('9789627452492', 'Integrated Music 2', 'Hong Kong Music', 2, 194, 18);
INSERT INTO bookinfo VALUES ('9789627452263', 'Integrated Music 3', 'Hong Kong Music', 1, 194, 0);

-- Initial data: BookAuthor
INSERT INTO bookauthor VALUES ('9789865024338', 'Bjarne Stroustrup');
INSERT INTO bookauthor VALUES ('9789863475705', '陳會安');
INSERT INTO bookauthor VALUES ('9789888242931', 'E. Cheng');
INSERT INTO bookauthor VALUES ('9789888242931', 'J. Chow');
INSERT INTO bookauthor VALUES ('9789888242931', 'Y.F. Chow');
INSERT INTO bookauthor VALUES ('9789888242931', 'A. Kai');
INSERT INTO bookauthor VALUES ('9789888242931', 'S.L. Lee');
INSERT INTO bookauthor VALUES ('9789888242931', 'W.H. Wong');
INSERT INTO bookauthor VALUES ('9789888540129', 'E. Cheng');
INSERT INTO bookauthor VALUES ('9789888540129', 'J. Chow');
INSERT INTO bookauthor VALUES ('9789888540129', 'Y.F. Chow');
INSERT INTO bookauthor VALUES ('9789888540129', 'A. Kai');
INSERT INTO bookauthor VALUES ('9789888540129', 'S.L. Lee');
INSERT INTO bookauthor VALUES ('9789888540129', 'W.H. Wong');
INSERT INTO bookauthor VALUES ('9789888540143', 'E. Cheng');
INSERT INTO bookauthor VALUES ('9789888540143', 'J. Chow');
INSERT INTO bookauthor VALUES ('9789888540143', 'Y.F. Chow');
INSERT INTO bookauthor VALUES ('9789888540143', 'A. Kai');
INSERT INTO bookauthor VALUES ('9789888540143', 'S.L. Lee');
INSERT INTO bookauthor VALUES ('9789888540143', 'W.H. Wong');
INSERT INTO bookauthor VALUES ('9780195489576', 'Joanna Turnbull');
INSERT INTO bookauthor VALUES ('9780195489576', 'Alison Waters');
INSERT INTO bookauthor VALUES ('9789622880184', '小思');
INSERT INTO bookauthor VALUES ('9781401398033', 'Mitch Albom');
INSERT INTO bookauthor VALUES ('962070133X', '黃港生');
INSERT INTO bookauthor VALUES ('9789882119437', '龍應台');
INSERT INTO bookauthor VALUES ('9789620703973', 'A. de Saint-Exupéry');
INSERT INTO bookauthor VALUES ('9789888467464', 'W.S. Yeung');
INSERT INTO bookauthor VALUES ('9789882364523', 'Victor Yeung');
INSERT INTO bookauthor VALUES ('9789882364523', 'Wong Chi Fai');
INSERT INTO bookauthor VALUES ('9789881250919', '吳田各');
INSERT INTO bookauthor VALUES ('9789881250919', '袁思惠');
INSERT INTO bookauthor VALUES ('9789881250919', '黃澤榕');
INSERT INTO bookauthor VALUES ('9789881250919', '葉安沂');
INSERT INTO bookauthor VALUES ('9789882398085', 'C.M. Yeung');
INSERT INTO bookauthor VALUES ('9789882398085', 'K.H. Yeung');
INSERT INTO bookauthor VALUES ('9789882398085', 'K.Y. Tsui');
INSERT INTO bookauthor VALUES ('9789882398085', 'K.C. Chan');
INSERT INTO bookauthor VALUES ('9789882398085', 'F.C. Tong');
INSERT INTO bookauthor VALUES ('9789882398085', 'M.K. Lui');
INSERT INTO bookauthor VALUES ('9789882398085', 'M.N. Wong');

-- Initial data: UserType
INSERT INTO usertype (type_name, max_books_borrow, max_days_borrow, debt_each_day) VALUES ('基本客戶', 8, 14, 1.5);
INSERT INTO usertype (type_name, max_books_borrow, max_days_borrow, debt_each_day) VALUES ('VIP', 12, 21, 1);

-- Initial data: UserInfo
INSERT INTO userinfo VALUES ('A1234563', 2, 'Tommy Chan', 's207885@hsu.edu.hk', '98765432', 'M', 'Hang Shin Link, Siu Lek Yuen, Sha Tin, Hong Kong.');
INSERT INTO userinfo VALUES ('E3620003', 1, '陳大文', 'chantaiman@gmail.com', NULL, 'M', 'Hang Shin Link, Siu Lek Yuen, Sha Tin, Hong Kong.');
INSERT INTO userinfo VALUES ('N1016774', 2, '林子祥', NULL, NULL, 'M', '花街70號');
INSERT INTO userinfo VALUES ('Y1481892', 1, 'Sue', NULL, '62201234', 'F', '九龍慈雲山慈樂邨樂安樓');
INSERT INTO userinfo VALUES ('P2233790', 1, '陳一心', NULL, '36288588', 'F', '九龍新蒲崗爵祿街17號');
INSERT INTO userinfo VALUES ('G8333881', 2, 'Ryan', NULL, '23456789', 'M', '香港九龍紅磡育才道11號');
INSERT INTO userinfo VALUES ('XA1234568', 1, 'Chris Wong', 'chriswong@yahoo.com.hk', '36288588', 'M', '九龍新蒲崗爵祿街17號');
INSERT INTO userinfo VALUES ('C1413161', 1, '獨孤允行', NULL, NULL, 'M', '新界屯門蝴蝶邨蝶影樓');

-- Initial data: Transaction
INSERT INTO transaction (HKID, borrow_date, paid) VALUES ('A1234563', '2022-02-07', true);
INSERT INTO transaction (HKID, borrow_date, paid) VALUES ('N1016774', '2022-02-09', false);
INSERT INTO transaction (HKID, borrow_date, paid) VALUES ('G8333881', '2022-02-15', true);
INSERT INTO transaction (HKID, borrow_date, paid) VALUES ('G8333881', '2022-02-16', true);
INSERT INTO transaction (HKID, borrow_date, paid) VALUES ('A1234563', '2022-02-16', true);
INSERT INTO transaction (HKID, borrow_date, paid) VALUES ('G8333881', '2022-02-18', false);
INSERT INTO transaction (HKID, borrow_date, paid) VALUES ('A1234563', '2022-02-19', false);
INSERT INTO transaction (HKID, borrow_date, paid) VALUES ('Y1481892', '2022-02-19', false);
INSERT INTO transaction (HKID, borrow_date, paid) VALUES ('E3620003', '2022-02-20', false);
INSERT INTO transaction (HKID, borrow_date, paid) VALUES ('N1016774', '2022-02-20', false);
INSERT INTO transaction (HKID, borrow_date, paid) VALUES ('P2233790', '2022-02-21', true);
INSERT INTO transaction (HKID, borrow_date, paid) VALUES ('XA1234568', '2022-02-23', false);
INSERT INTO transaction (HKID, borrow_date, paid) VALUES ('C1413161', '2022-02-23', false);

-- Initial data: TransactionDetail
INSERT INTO transactiondetail (transaction_id, ISBN, due_date, return_date) VALUES (1, '9789622880184', '2022-02-21', '2022-02-18');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date, return_date) VALUES (1, '962070133X', '2022-02-21', '2022-03-03');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date, return_date) VALUES (2, '9789620703973', '2022-02-23', '2022-02-20');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date, return_date) VALUES (3, '9789865024338', '2022-03-01', '2022-02-28');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date, return_date) VALUES (4, '9789620703973', '2022-03-02', '2022-03-01');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date, return_date) VALUES (4, '9789863475705', '2022-03-02', '2022-03-11');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date, return_date) VALUES (5, '9789865024338', '2022-03-02', '2022-03-03');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date, return_date) VALUES (5, '9789865024338', '2022-03-02', '2022-02-20');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date, return_date) VALUES (6, '9789865024338', '2022-03-04', '2022-03-06');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date, return_date) VALUES (7, '9789865024338', '2022-03-05', '2022-03-04');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date, return_date) VALUES (8, '9789882119437', '2022-03-05', '2022-03-01');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date, return_date) VALUES (8, '9789622880184', '2022-03-05', '2022-03-01');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date, return_date) VALUES (8, '9789881250919', '2022-03-05', '2022-03-01');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date, return_date) VALUES (9, '9789882364523', '2022-03-06', '2022-03-06');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date, return_date) VALUES (9, '9789888467464', '2022-03-06', '2022-03-06');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date, return_date) VALUES (10, '9789627452485', '2022-03-06', '2022-03-02');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date, return_date) VALUES (10, '9789627452492', '2022-03-06', '2022-03-02');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date, return_date) VALUES (11, '9789881250919', '2022-03-07', '2022-03-12');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date, return_date) VALUES (12, '9780195489576', '2022-03-09', '2022-03-01');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date, return_date) VALUES (12, '9789882054226', '2022-03-09', '2022-03-01');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date) VALUES (13, '9789888540129', '2022-03-09');
INSERT INTO transactiondetail (transaction_id, ISBN, due_date) VALUES (13, '9789888540143', '2022-03-09');
