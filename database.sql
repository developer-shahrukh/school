DROP DATABASE IF EXISTS schooldb;
CREATE DATABASE schooldb;
USE schooldb;

DROP USER IF EXISTS 'school_user'@'localhost';
CREATE USER 'school_user'@'localhost' IDENTIFIED BY 'school_user';

GRANT ALL PRIVILEGES ON schooldb.* TO 'school_user'@'localhost';
FLUSH PRIVILEGES;

DROP TABLE IF EXISTS Teacher_classes;
DROP TABLE IF EXISTS Teacher_subjects;
DROP TABLE IF EXISTS Announcements;
DROP TABLE IF EXISTS Events;
DROP TABLE IF EXISTS Attendances;
DROP TABLE IF EXISTS Results;
DROP TABLE IF EXISTS Assignments;
DROP TABLE IF EXISTS Exams;
DROP TABLE IF EXISTS Lessons;
DROP TABLE IF EXISTS Subjects;
DROP TABLE IF EXISTS Students;
DROP TABLE IF EXISTS Teachers;
DROP TABLE IF EXISTS Classes;
DROP TABLE IF EXISTS Grades;
DROP TABLE IF EXISTS Parents;
DROP TABLE IF EXISTS Admins;

CREATE TABLE Admins (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE Parents (
    parent_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    phone VARCHAR(255) UNIQUE NOT NULL,
    address TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Grades (
    grade_id INT PRIMARY KEY AUTO_INCREMENT,
    level INT UNIQUE NOT NULL
);

CREATE TABLE Teachers (
    teacher_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    phone VARCHAR(255) UNIQUE,
    address TEXT NOT NULL,
    img TEXT,
    blood_type VARCHAR(10) NOT NULL,
    gender ENUM('MALE', 'FEMALE') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    birthday DATE NOT NULL
);

CREATE TABLE Classes (
    class_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE NOT NULL,
    capacity INT NOT NULL,
    supervisor_id INT,
    grade_id INT,
    FOREIGN KEY (supervisor_id) REFERENCES Teachers(teacher_id) ON DELETE SET NULL,
    FOREIGN KEY (grade_id) REFERENCES Grades(grade_id) ON DELETE CASCADE
);

CREATE TABLE Students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    phone VARCHAR(255) UNIQUE,
    address TEXT NOT NULL,
    img TEXT,
    blood_type VARCHAR(10) NOT NULL,
    gender ENUM('MALE', 'FEMALE') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    parent_id INT,
    class_id INT,
    grade_id INT,
    birthday DATE NOT NULL,
    FOREIGN KEY (parent_id) REFERENCES Parents(parent_id) ON DELETE CASCADE,
    FOREIGN KEY (class_id) REFERENCES Classes(class_id) ON DELETE CASCADE,
    FOREIGN KEY (grade_id) REFERENCES Grades(grade_id) ON DELETE CASCADE
);

CREATE TABLE Subjects (
    subject_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE Lessons (
    lesson_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    day VARCHAR(10) NOT NULL,  -- Changed from ENUM to VARCHAR for compatibility
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    subject_id INT,
    class_id INT,
    teacher_id INT,
    FOREIGN KEY (subject_id) REFERENCES Subjects(subject_id) ON DELETE CASCADE,
    FOREIGN KEY (class_id) REFERENCES Classes(class_id) ON DELETE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES Teachers(teacher_id) ON DELETE CASCADE
);

CREATE TABLE Exams (
    exam_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    lesson_id INT,
    FOREIGN KEY (lesson_id) REFERENCES Lessons(lesson_id) ON DELETE CASCADE
);

CREATE TABLE Assignments (
    assignment_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    start_date TIMESTAMP NOT NULL,
    due_date TIMESTAMP NOT NULL,
    lesson_id INT,
    FOREIGN KEY (lesson_id) REFERENCES Lessons(lesson_id) ON DELETE CASCADE
);

CREATE TABLE Results (
    result_id INT PRIMARY KEY AUTO_INCREMENT,
    score INT NOT NULL,
    exam_id INT,
    assignment_id INT,
    student_id INT,
    FOREIGN KEY (exam_id) REFERENCES Exams(exam_id) ON DELETE SET NULL,
    FOREIGN KEY (assignment_id) REFERENCES Assignments(assignment_id) ON DELETE SET NULL,
    FOREIGN KEY (student_id) REFERENCES Students(student_id) ON DELETE CASCADE
);

CREATE TABLE Attendances (
    attendance_id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL,
    present BOOLEAN NOT NULL,
    student_id INT,
    lesson_id INT,
    FOREIGN KEY (student_id) REFERENCES Students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (lesson_id) REFERENCES Lessons(lesson_id) ON DELETE CASCADE
);

CREATE TABLE Events (
    event_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    class_id INT,
    FOREIGN KEY (class_id) REFERENCES Classes(class_id) ON DELETE SET NULL
);

CREATE TABLE Announcements (
    announcement_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    date TIMESTAMP NOT NULL,
    class_id INT,
    FOREIGN KEY (class_id) REFERENCES Classes(class_id) ON DELETE SET NULL
);

CREATE TABLE Teacher_subjects (
    teacher_id INT,
    subject_id INT,
    PRIMARY KEY (teacher_id, subject_id),
    FOREIGN KEY (teacher_id) REFERENCES Teachers(teacher_id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES Subjects(subject_id) ON DELETE CASCADE
);

CREATE TABLE Teacher_classes (
    teacher_id INT,
    class_id INT,
    PRIMARY KEY (teacher_id, class_id),
    FOREIGN KEY (teacher_id) REFERENCES Teachers(teacher_id) ON DELETE CASCADE,
    FOREIGN KEY (class_id) REFERENCES Classes(class_id) ON DELETE CASCADE
);

-- Insert into Admins
INSERT INTO Admins (username) VALUES ('admin1'), ('admin2');

-- Insert into Parents
INSERT INTO Parents (username, name, surname, email, phone, address)
VALUES
    ('parent1', 'John', 'Doe', 'john.doe@example.com', '1234567890', '123 Main St'),
    ('parent2', 'Jane', 'Smith', 'jane.smith@example.com', '0987654321', '456 Oak St');

-- Insert into Grades
INSERT INTO Grades (level) VALUES (1), (2);

-- Insert into Classes
INSERT INTO Classes (name, capacity, grade_id)
VALUES
    ('Class A', 30, 1),
    ('Class B', 25, 2);

-- Insert into Teachers
INSERT INTO Teachers (username, name, surname, email, phone, address, img, blood_type, gender, birthday)
VALUES
    ('teacher1', 'Alice', 'Brown', 'alice.brown@example.com', '5551112222', '789 Pine St', NULL, 'O+', 'FEMALE', '1985-06-15'),
    ('teacher2', 'Bob', 'White', 'bob.white@example.com', '5553334444', '101 Maple St', NULL, 'A-', 'MALE', '1980-09-22');

-- Insert into Students
INSERT INTO Students (username, name, surname, email, phone, address, img, blood_type, gender, parent_id, class_id, grade_id, birthday)
VALUES
    ('student1', 'Emma', 'Davis', 'emma.davis@example.com', '6661112222', '111 Birch St', NULL, 'B+', 'FEMALE', 1, 1, 1, '2010-05-10'),
    ('student2', 'Liam', 'Johnson', 'liam.johnson@example.com', '6663334444', '222 Elm St', NULL, 'AB-', 'MALE', 2, 2, 2, '2011-07-20');

-- Insert into Subjects
INSERT INTO Subjects (name) VALUES ('Mathematics'), ('Science');

-- Insert into Lessons
INSERT INTO Lessons (name, day, start_time, end_time, subject_id, class_id, teacher_id)
VALUES
    ('Algebra Basics', 'MONDAY', '08:00:00', '09:00:00', 1, 1, 1),
    ('Physics Introduction', 'TUESDAY', '09:00:00', '10:00:00', 2, 2, 2);

-- Insert into Exams
INSERT INTO Exams (title, start_time, end_time, lesson_id)
VALUES
    ('Math Midterm', '2025-03-15 10:00:00', '2025-03-15 12:00:00', 1),
    ('Science Quiz', '2025-04-10 14:00:00', '2025-04-10 15:00:00', 2);

-- Insert into Assignments
INSERT INTO Assignments (title, start_date, due_date, lesson_id)
VALUES
    ('Math Homework 1', '2025-02-01', '2025-02-07', 1),
    ('Science Project', '2025-02-10', '2025-02-20', 2);

-- Insert into Results
INSERT INTO Results (score, exam_id, assignment_id, student_id)
VALUES
    (85, 1, NULL, 1),
    (90, NULL, 2, 2);

-- Insert into Attendances
INSERT INTO Attendances (date, present, student_id, lesson_id)
VALUES
    ('2025-02-01', TRUE, 1, 1),
    ('2025-02-02', FALSE, 2, 2);

-- Insert into Events
INSERT INTO Events (title, description, start_time, end_time, class_id)
VALUES
    ('Annual Day', 'School-wide annual celebration', '2025-06-01 10:00:00', '2025-06-01 16:00:00', 1),
    ('Sports Day', 'Inter-class sports competition', '2025-07-15 09:00:00', '2025-07-15 17:00:00', 2);

-- Insert into Announcements
INSERT INTO Announcements (title, description, date, class_id)
VALUES
    ('Exam Schedule', 'Upcoming exam schedule for all grades.', '2025-02-01', 1),
    ('Parent-Teacher Meeting', 'Meeting to discuss student progress.', '2025-02-10', 2);

-- Insert into Teacher_subjects
INSERT INTO Teacher_subjects (teacher_id, subject_id)
VALUES
    (1, 1),
    (2, 2);

-- Insert into Teacher_classes
INSERT INTO Teacher_classes (teacher_id, class_id)
VALUES
    (1, 1),
    (2, 2);
