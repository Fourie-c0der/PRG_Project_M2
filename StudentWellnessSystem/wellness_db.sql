-- Counselors Table
CREATE TABLE Counselors (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(100),
    specialization VARCHAR(100),
    availability VARCHAR(50)
);

-- Appointments Table
CREATE TABLE Appointments (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    studentName VARCHAR(100),
    counselorId INT,
    appointmentDate DATE,
    appointmentTime TIME,
    status VARCHAR(50),
    FOREIGN KEY (counselorId) REFERENCES Counselors(id)
);

-- Feedback Table
CREATE TABLE Feedback (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    studentName VARCHAR(100),
    rating INT,
    comments VARCHAR(255)
);


