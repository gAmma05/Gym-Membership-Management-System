CREATE TABLE Users (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    salt NVARCHAR(255) NOT NULL,
    -- Field cho tất cả users:
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    address VARCHAR(255),
    city VARCHAR(255),
    birth DATE,
    phoneNumber NVARCHAR(9),
    gender NVARCHAR(10) CHECK (gender IN ('Male', 'Female')),
    
    -- Field cho Member:
    joinDate DATE,
    membership_ID INT,
    FOREIGN KEY(membership_ID) REFERENCES membership_plan(membership_ID),
    
    -- Field cho Trainer:
    availableSessions VARCHAR(512)
);


CREATE TABLE membership_plan(
	membership_ID INT IDENTITY(1,1) PRIMARY KEY,
	membershipName NVARCHAR(10) CHECK (membershipName IN ('Bronze', 'Silver', 'Gold', 'Diamond')),
	durationMonths INT CHECK (durationMonths IN ('1 Month', '3 month', '12 month', '24 month')),
	price DECIMAL(10,2) NOT NULL CHECK (price >= 0),
	benefit VARCHAR(512)
);

CREATE TABLE Admin (
    admin_id INT PRIMARY KEY,
    FOREIGN KEY(admin_id) REFERENCES Users(id),
);

CREATE TABLE Member (
    member_id INT PRIMARY KEY,
    FOREIGN KEY(member_id) REFERENCES Users(id),
);

CREATE TABLE Trainer (
    trainer_id INT PRIMARY KEY,
    FOREIGN KEY(trainer_id) REFERENCES Users(id),
);

CREATE TABLE Payment(
	payment_Option NVARCHAR(10) CHECK (payment_Option IN ('Cash', 'Credit Card')),
	payment_ID INT,
	member_ID INT,
	FOREIGN KEY(member_ID) REFERENCES Member(id) on DELETE CASCADE,
	payment_Date DATE DEFAULT GETDATE()
);