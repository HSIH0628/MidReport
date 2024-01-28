use MidReport;


CREATE TABLE Member (
    ID INT PRIMARY KEY IDENTITY,
    CompanyName VARCHAR(255),
    Address VARCHAR(255),
    Phone VARCHAR(15)
);

select * from MemberTest;

select * from Member;


DROP TABLE Member;
