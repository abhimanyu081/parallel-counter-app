use test;
DROP TABLE IF EXISTS number;
CREATE TABLE Number ( id INT NOT NULL PRIMARY KEY,
count integer not null default 0);
INSERT INTO Number (id,count) values(1,0);