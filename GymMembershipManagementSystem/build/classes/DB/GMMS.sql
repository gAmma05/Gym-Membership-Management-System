-- Drop the database if it exists
IF EXISTS (SELECT * FROM sys.databases WHERE name = 'GMMS')
BEGIN
    DROP DATABASE GMMS;
END
GO

-- Create the database
CREATE DATABASE GMMS;
GO

-- Use the newly created database
USE GMMS;
GO


-- Drop tables if they exist to avoid conflicts
IF OBJECT_ID('dbo.Users', 'U') IS NOT NULL DROP TABLE dbo.Users;
IF OBJECT_ID('dbo.Membership_Plan', 'U') IS NOT NULL DROP TABLE dbo.Membership_Plan;
IF OBJECT_ID('dbo.Admin', 'U') IS NOT NULL DROP TABLE dbo.Admin;
IF OBJECT_ID('dbo.Member', 'U') IS NOT NULL DROP TABLE dbo.Member;
IF OBJECT_ID('dbo.Trainer', 'U') IS NOT NULL DROP TABLE dbo.Trainer;
IF OBJECT_ID('dbo.TrainingSession', 'U') IS NOT NULL DROP TABLE dbo.TrainingSession;
IF OBJECT_ID('dbo.MemberProgress', 'U') IS NOT NULL DROP TABLE dbo.MemberProgress;
IF OBJECT_ID('dbo.Payment', 'U') IS NOT NULL DROP TABLE dbo.Payment;
GO


-- Create Users table
CREATE TABLE Users (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    salt NVARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(20) NOT NULL,
    phoneNumber NVARCHAR(20),
    gender NVARCHAR(10) CHECK (gender IN ('Male', 'Female'))
);
GO

-- Create MembershipPlan table
CREATE TABLE MembershipPlan (
    membershipID INT IDENTITY(1,1) PRIMARY KEY,
    membershipName NVARCHAR(10),
    durationMonths INT CHECK (durationMonths > 0),
    price INT CHECK (price > 0),
    benefit VARCHAR(512)
);
GO

-- Create Admin table
CREATE TABLE Admin (
    adminID INT PRIMARY KEY,
    FOREIGN KEY(adminID) REFERENCES Users(id)
);
GO

-- Create Member table
CREATE TABLE Member (
    memberID INT PRIMARY KEY,
    FOREIGN KEY(memberID) REFERENCES Users(id),
    msID INT FOREIGN KEY REFERENCES MembershipPlan(membershipID),
    joinDate DATE
);
GO

-- Create Trainer table
CREATE TABLE Trainer (
    trainerID INT PRIMARY KEY,
    FOREIGN KEY(trainerID) REFERENCES Users(id),
    expYear INT
);
GO

-- Create TrainingSession table
CREATE TABLE TrainingSession (
    sessionID INT PRIMARY KEY IDENTITY(1,1),
    trainerID INT FOREIGN KEY REFERENCES Trainer(trainerID),
    memberID INT FOREIGN KEY REFERENCES Member(memberID),
    sessionTime DATETIME,
    location VARCHAR(50),
    durationByMinutes INT
);
GO

-- Create MemberProgress table
CREATE TABLE MemberProgress (
    progressID INT PRIMARY KEY IDENTITY(1,1),
    memberID INT FOREIGN KEY REFERENCES Member(memberID),
    dateCreated DATE,
    workoutHistory TEXT,
    healthMetrics VARCHAR(255)
);
GO

-- Create Payment table
CREATE TABLE Payment (
    paymentID INT,
    memberID INT,
    FOREIGN KEY(memberID) REFERENCES Member(memberID) ON DELETE CASCADE,
    paymentDate DATE DEFAULT GETDATE(),
    renewalDate DATE
);
GO
