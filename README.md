# Employee Management System
## Overview
This system is designed to manage employee records stored in XML files. Employee data is organized into two categories: internal and external employees, stored in separate directories. The system provides functionalities to add, find, remove, and modify employee records.

## Features
- Find Employee: Search for an employee based on their type, first name, last name, and mobile number.
- Add New Employee: Add a new employee record to the system.
- Remove Employee: Delete an existing employee record from the system.
- Modify Employee: Update details of an existing employee record.
## Data Structure
Employee records consist of the following fields:

- personId (String): A unique identifier for the employee.
- firstName (String): The employee's first name.
- lastName (String): The employee's last name.
- mobile (String): The employee's mobile phone number.
- email (String): The employee's email address.
- pesel (String): The PESEL number of the employee (Polish national identification number).
## Directory Structure
- External: Contains XML files for external employees.
- Internal: Contains XML files for internal employees.

## Unit Testing
- The system includes unit tests to ensure the reliability of operations such as adding, finding, removing, and modifying employee records. These tests are crucial for maintaining data integrity and system functionality.
