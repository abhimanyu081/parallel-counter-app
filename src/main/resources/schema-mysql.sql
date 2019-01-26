use test;
DROP TABLE IF EXISTS number;
CREATE TABLE Number ( --id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
count integer not null default 0);
INSERT INTO Number (count) values(0);