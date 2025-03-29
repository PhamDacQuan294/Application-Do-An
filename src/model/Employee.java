package model;

import java.time.LocalDate;

public class Employee {
    private String employeeId;
    private String password;
    private String role;
    private String fullName;
    private LocalDate birthDate; 
    private String phoneNumber;

    public Employee(String employeeId, String password, String role, String fullName, LocalDate birthDate, String phoneNumber) {
        this.employeeId = employeeId;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
