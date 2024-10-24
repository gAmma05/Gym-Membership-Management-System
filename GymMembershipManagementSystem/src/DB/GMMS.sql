CREATE TABLE Users (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    salt NVARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(20) NOT NULL,
    phoneNumber NVARCHAR(9),
    gender NVARCHAR(10) CHECK (gender IN ('Male', 'Female')),
);


CREATE TABLE Membership_Plan(
	membership_ID INT IDENTITY(1,1) PRIMARY KEY,
	membershipName NVARCHAR(10) CHECK (membershipName IN ('Bronze', 'Silver', 'Gold', 'Platinum')),
	durationMonths INT CHECK (durationMonths > 0),
	price INT CHECK (price > 0),
	benefit VARCHAR(512)

);

CREATE TABLE Admin (
    admin_id INT PRIMARY KEY,
    FOREIGN KEY(admin_id) REFERENCES Users(id),

);

CREATE TABLE Member (
    member_id INT PRIMARY KEY,
    FOREIGN KEY(member_id) REFERENCES Users(id),
	msID INT FOREIGN KEY REFERENCES Membership_Plan(membership_ID),
	joinDate DATE,
	expiredDate DATE,

);

CREATE TABLE Trainer (
    trainer_id INT PRIMARY KEY,
    FOREIGN KEY(trainer_id) REFERENCES Users(id),
	expYear INT

);

CREATE TABLE TrainingSession (
	sessionID INT PRIMARY KEY IDENTITY(1,1),
	TrainerID INT FOREIGN KEY REFERENCES Trainer(trainer_ID),
    MemberID INT FOREIGN KEY REFERENCES Member(member_ID),
	session_time DATETIME,
	location VARCHAR(50),
	durationByMinutes INT

);

CREATE TABLE MemberProgress (
    ProgressID INT PRIMARY KEY IDENTITY(1,1),
    MemberID INT FOREIGN KEY REFERENCES Member(member_ID),
    Date DATE,
    WorkoutHistory TEXT,
    HealthMetrics VARCHAR(255)

);


CREATE TABLE Payment(
	payment_ID INT,
	Member_ID INT,
	FOREIGN KEY(Member_ID) REFERENCES Member(member_id) on DELETE CASCADE,
	payment_Date DATE DEFAULT GETDATE(),
	renewalDate DATE
);