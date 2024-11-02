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
    phoneNumber NVARCHAR(9),
    gender NVARCHAR(10) CHECK (gender IN ('Male', 'Female'))
);
GO

-- Create Membership_Plan table
CREATE TABLE Membership_Plan (
    membership_ID INT IDENTITY(1,1) PRIMARY KEY,
    membershipName NVARCHAR(10),
    durationMonths INT CHECK (durationMonths > 0),
    price INT CHECK (price > 0),
    benefit VARCHAR(512)
);
GO

-- Create Admin table
CREATE TABLE Admin (
    admin_id INT PRIMARY KEY,
    FOREIGN KEY(admin_id) REFERENCES Users(id)
);
GO

-- Create Member table
CREATE TABLE Member (
    member_id INT PRIMARY KEY,
    msID INT FOREIGN KEY REFERENCES Membership_Plan(membership_ID),
    joinDate DATE,
    expiredDate DATE
);
GO

-- Create Trainer table
CREATE TABLE Trainer (
    trainer_id INT PRIMARY KEY,
    expYear INT
);
GO

-- Create TrainingSession table
CREATE TABLE TrainingSession (
    sessionID INT PRIMARY KEY IDENTITY(1,1),
    TrainerID INT FOREIGN KEY REFERENCES Trainer(trainer_id),
    MemberID INT FOREIGN KEY REFERENCES Member(member_id),
    session_time DATETIME,
    location VARCHAR(50),
    durationByMinutes INT
);
GO

-- Create MemberProgress table
CREATE TABLE MemberProgress (
    ProgressID INT PRIMARY KEY IDENTITY(1,1),
    MemberID INT FOREIGN KEY REFERENCES Member(member_id),
    Date DATE,
    WorkoutHistory TEXT,
    HealthMetrics VARCHAR(255)
);
GO

-- Create Payment table
CREATE TABLE Payment (
    payment_ID INT,
    Member_ID INT,
    FOREIGN KEY(Member_ID) REFERENCES Member(member_id) ON DELETE CASCADE,
    payment_Date DATE DEFAULT GETDATE(),
    renewalDate DATE
);
GO
