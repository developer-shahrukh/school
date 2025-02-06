DROP DATABASE IF EXISTS schooldb;
CREATE DATABASE schooldb;
USE schooldb;

DROP USER IF EXISTS 'school_user'@'localhost';
CREATE USER 'school_user'@'localhost' IDENTIFIED BY 'school_user';

GRANT ALL PRIVILEGES ON schooldb.* TO 'school_user'@'localhost';
FLUSH PRIVILEGES;

DROP TABLE IF EXISTS teacher_classes;
DROP TABLE IF EXISTS teacher_subjects;
DROP TABLE IF EXISTS announcements;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS attendances;
DROP TABLE IF EXISTS results;
DROP TABLE IF EXISTS assignments;
DROP TABLE IF EXISTS exams;
DROP TABLE IF EXISTS lessons;
DROP TABLE IF EXISTS subjects;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS teachers;
DROP TABLE IF EXISTS classes;
DROP TABLE IF EXISTS grades;
DROP TABLE IF EXISTS parents;
DROP TABLE IF EXISTS admins;

CREATE TABLE admins (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE parents (
    parent_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    phone VARCHAR(255) UNIQUE NOT NULL,
    address TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE grades (
    grade_id INT PRIMARY KEY AUTO_INCREMENT,
    level INT UNIQUE NOT NULL
);

CREATE TABLE teachers (
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

CREATE TABLE classes (
    class_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE NOT NULL,
    capacity INT NOT NULL,
    supervisor_id INT,
    grade_id INT,
    FOREIGN KEY (supervisor_id) REFERENCES teachers(teacher_id) ON DELETE SET NULL,
    FOREIGN KEY (grade_id) REFERENCES grades(grade_id) ON DELETE CASCADE
);

CREATE TABLE students (
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
    FOREIGN KEY (parent_id) REFERENCES parents(parent_id) ON DELETE CASCADE,
    FOREIGN KEY (class_id) REFERENCES classes(class_id) ON DELETE CASCADE,
    FOREIGN KEY (grade_id) REFERENCES grades(grade_id) ON DELETE CASCADE
);

CREATE TABLE subjects (
    subject_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE lessons (
    lesson_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    day VARCHAR(10) NOT NULL,  -- Changed from ENUM to VARCHAR for compatibility
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    subject_id INT,
    class_id INT,
    teacher_id INT,
    FOREIGN KEY (subject_id) REFERENCES subjects(subject_id) ON DELETE CASCADE,
    FOREIGN KEY (class_id) REFERENCES classes(class_id) ON DELETE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES teachers(teacher_id) ON DELETE CASCADE
);

CREATE TABLE exams (
    exam_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    lesson_id INT,
    FOREIGN KEY (lesson_id) REFERENCES lessons(lesson_id) ON DELETE CASCADE
);

CREATE TABLE assignments (
    assignment_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    start_date TIMESTAMP NOT NULL,
    due_date TIMESTAMP NOT NULL,
    lesson_id INT,
    FOREIGN KEY (lesson_id) REFERENCES lessons(lesson_id) ON DELETE CASCADE
);

CREATE TABLE results (
    result_id INT PRIMARY KEY AUTO_INCREMENT,
    score INT NOT NULL,
    exam_id INT,
    assignment_id INT,
    student_id INT,
    FOREIGN KEY (exam_id) REFERENCES exams(exam_id) ON DELETE SET NULL,
    FOREIGN KEY (assignment_id) REFERENCES assignments(assignment_id) ON DELETE SET NULL,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE
);

CREATE TABLE attendances (
    attendance_id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL,
    present BOOLEAN NOT NULL,
    student_id INT,
    lesson_id INT,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (lesson_id) REFERENCES lessons(lesson_id) ON DELETE CASCADE
);

CREATE TABLE events (
    event_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    class_id INT,
    FOREIGN KEY (class_id) REFERENCES classes(class_id) ON DELETE SET NULL
);

CREATE TABLE announcements (
    announcement_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    date TIMESTAMP NOT NULL,
    class_id INT,
    FOREIGN KEY (class_id) REFERENCES classes(class_id) ON DELETE SET NULL
);

CREATE TABLE teacher_subjects (
    teacher_id INT,
    subject_id INT,
    PRIMARY KEY (teacher_id, subject_id),
    FOREIGN KEY (teacher_id) REFERENCES teachers(teacher_id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subjects(subject_id) ON DELETE CASCADE
);

CREATE TABLE teacher_classes (
    teacher_id INT,
    class_id INT,
    PRIMARY KEY (teacher_id, class_id),
    FOREIGN KEY (teacher_id) REFERENCES teachers(teacher_id) ON DELETE CASCADE,
    FOREIGN KEY (class_id) REFERENCES classes(class_id) ON DELETE CASCADE
);

-- Insert into admins
INSERT INTO admins (username) VALUES ('admin1'), ('admin2');

-- Insert into parents
INSERT INTO parents (username, name, surname, email, phone, address)
VALUES
    ('parent1', 'John', 'Doe', 'john.doe@example.com', '1234567890', '123 Main St'),
    ('parent2', 'Jane', 'Smith', 'jane.smith@example.com', '0987654321', '456 Oak St');

-- Insert into grades
INSERT INTO grades (level) VALUES (1), (2);

-- Insert into classes
INSERT INTO classes (name, capacity, grade_id)
VALUES
    ('Class A', 30, 1),
    ('Class B', 25, 2);

-- Insert into teachers
INSERT INTO teachers (username, name, surname, email, phone, address, img, blood_type, gender, birthday)
VALUES
    ('teacher1', 'Alice', 'Brown', 'alice.brown@example.com', '5551112222', '789 Pine St', NULL, 'O+', 'FEMALE', '1985-06-15'),
    ('teacher2', 'Bob', 'White', 'bob.white@example.com', '5553334444', '101 Maple St', NULL, 'A-', 'MALE', '1980-09-22');

-- Insert into students
INSERT INTO students (username, name, surname, email, phone, address, img, blood_type, gender, parent_id, class_id, grade_id, birthday)
VALUES
    ('student1', 'Emma', 'Davis', 'emma.davis@example.com', '6661112222', '111 Birch St', NULL, 'B+', 'FEMALE', 1, 1, 1, '2010-05-10'),
    ('student2', 'Liam', 'Johnson', 'liam.johnson@example.com', '6663334444', '222 Elm St', NULL, 'AB-', 'MALE', 2, 2, 2, '2011-07-20');

-- Insert into subjects
INSERT INTO subjects (name) VALUES ('Mathematics'), ('Science');

-- Insert into lessons
INSERT INTO lessons (name, day, start_time, end_time, subject_id, class_id, teacher_id)
VALUES
    ('Algebra Basics', 'MONDAY', '08:00:00', '09:00:00', 1, 1, 1),
    ('Physics Introduction', 'TUESDAY', '09:00:00', '10:00:00', 2, 2, 2);

-- Insert into exams
INSERT INTO exams (title, start_time, end_time)
VALUES
    ('Math Midterm', '2025-03-15 10:00:00', '2025-03-15 12:00:00'),
    ('Science Quiz', '2025-04-10 14:00:00', '2025-04-10 15:00:00');

-- Insert into assignments
INSERT INTO assignments (title, start_date, due_date)
VALUES
    ('Math Homework 1', '2025-02-01', '2025-02-07'),
    ('Science Project', '2025-02-10', '2025-02-20');

-- Insert into results
INSERT INTO results (score, exam_id, assignment_id, student_id)
VALUES
    (85, 1, NULL, 1),
    (90, NULL, 2, 2);

-- Insert into attendances
INSERT INTO attendances (date, present, student_id)
VALUES
    ('2025-02-01', TRUE, 1),
    ('2025-02-02', FALSE, 2);

-- Insert into events
INSERT INTO events (title, description, start_time, end_time, class_id)
VALUES
    ('Annual Day', 'School-wide annual celebration', '2025-06-01 10:00:00', '2025-06-01 16:00:00', 1),
    ('Sports Day', 'Inter-class sports competition', '2025-07-15 09:00:00', '2025-07-15 17:00:00', 2);

-- Insert into announcements
INSERT INTO announcements (title, description, date, class_id)
VALUES
    ('Exam Schedule', 'Upcoming exam schedule for all grades.', '2025-02-01', 1),
    ('Parent-Teacher Meeting', 'Meeting to discuss student progress.', '2025-02-10', 2);

-- Insert into teacher_subjects
INSERT INTO teacher_subjects (teacher_id, subject_id)
VALUES
    (1, 1),
    (2, 2);

-- Insert into teacher_classes
INSERT INTO teacher_classes (teacher_id, class_id)
VALUES
    (1, 1),
    (2, 2);
