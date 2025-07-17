# PRG_Project_M2

---

## Features

### Appointment Management
- Book new appointments by selecting a counselor, date, and time
- View all upcoming appointments
- Update appointment details (e.g., reschedule)
- Cancel appointments

### Counselor Management
- Add new counselors with name, specialization, and availability
- View list of all counselors
- Update counselor details
- Remove counselors

### Feedback Management
- Submit feedback with rating (1–5) and comments
- View feedback history
- Edit or delete feedback entries

---

## Technologies Used

- **Java (Core & OOP Concepts)**
  - Inheritance, Polymorphism, Abstraction, Encapsulation
  - Exception Handling
  - Java Collections for dynamic data storage

- **Swing**
  - Custom GUI with tabs/menus
  - Forms with input validation
  - Confirmation & error dialogs

- **JavaDB (Derby)**
  - Embedded database integration
  - Tables: `Appointments`, `Counselors`, `Feedback`
  - Full CRUD support for all entities

- **MVC Architecture**
  - Clean separation of UI, business logic, and data access

---

## Functional Requirements

### Appointments
- Create, read, update, delete appointment records
- Relational fields: student name, counselor, date, time, status

### Counselors
- Manage counselor profiles with specialization and availability status

### Feedback
- Allow users to rate and comment on counseling services
- Edit/delete existing feedback entries

---

## Input Validation & Error Handling
- Prevents submission of empty or invalid fields
- Prompts confirmation for delete operations
- Displays friendly error messages for database or system issues

---

## Setup Instructions

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/WellnessManagementSystem.git
