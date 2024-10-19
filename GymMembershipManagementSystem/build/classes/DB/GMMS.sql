CREATE TABLE account_authenciation(
	id INT IDENTITY(1,1) PRIMARY KEY,
	username VARCHAR(255) NOT NULL UNIQUE,
	password VARCHAR(255) NOT NULL,
	salt NVARCHAR(255) NOT NULL
);


CREATE TABLE membership_plan(
	membership_ID INT IDENTITY(1,1) PRIMARY KEY,
	membershipName NVARCHAR(10) CHECK (membershipName IN ('Bronze', 'Silver', 'Gold', 'Diamond')),
	durationMonths INT CHECK (durationMonths IN ('1 Month', '3 month', '12 month', '24 month')),
	price DECIMAL(10,2) NOT NULL CHECK (price >= 0),
	benefit VARCHAR(512)
);

CREATE TABLE Admin(
	id INT IDENTITY(1,1) PRIMARY KEY,
	account_ID INT,
	FOREIGN KEY(account_ID) REFERENCES account_authenciation(id) on DELETE CASCADE,

	admin_name VARCHAR(255) NOT NULL
);


CREATE TABLE Member(
	id INT IDENTITY(1,1) PRIMARY KEY,
	account_ID INT,
	FOREIGN KEY(account_ID) REFERENCES account_authenciation(id) on DELETE CASCADE,

	name VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL UNIQUE,
	address VARCHAR(255) NOT NULL,
	city VARCHAR(255) NOT NULL,
	birth DATE NOT NULL,
	phoneNumber NVARCHAR(9) NOT NULL,

	gender NVARCHAR(10) CHECK (gender IN ('Male', 'Female')),	
	joinDate DATE DEFAULT GETDATE(),
	membership_ID INT, 
	
	CONSTRAINT FK_Member_membershipID FOREIGN KEY(membership_ID) REFERENCES membership_plan(membership_ID)
);


CREATE TABLE Trainer(
	id INT IDENTITY(1,1) PRIMARY KEY,
	account_ID INT,
	FOREIGN KEY(account_ID) REFERENCES account_authenciation(id) on DELETE CASCADE,

	name VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL UNIQUE,
	address VARCHAR(255) NOT NULL,
	city VARCHAR(255) NOT NULL,
	birth DATE NOT NULL,
	phoneNumber NVARCHAR(9) NOT NULL,

	gender NVARCHAR(10) CHECK (gender IN ('Male', 'Female')),
);

CREATE TABLE Payment(
	payment_Option NVARCHAR(10) CHECK (payment_Option IN ('Cash', 'Credit Card')),
	payment_ID INT,
	member_ID INT,
	FOREIGN KEY(member_ID) REFERENCES Member(id) on DELETE CASCADE,
	payment_Date DATE DEFAULT GETDATE()
);