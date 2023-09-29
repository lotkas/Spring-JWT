package com.example.springjwt.dto.employeedto;

import com.example.springjwt.models.Position;

import java.math.BigDecimal;

public class EmployeeUpdateByAdminDTO {

    private String firstName;

    private String lastName;

    private String email;

    private int age;

    private Position positionId;

    private BigDecimal salary;

    private boolean inJob;

    public EmployeeUpdateByAdminDTO(String firstName, String lastName, String email, int age, Position positionId, BigDecimal salary, boolean inJob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.positionId = positionId;
        this.salary = salary;
        this.inJob = inJob;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Position getPositionId() {
        return positionId;
    }

    public void setPositionId(Position positionId) {
        this.positionId = positionId;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public boolean isInJob() {
        return inJob;
    }

    public void setInJob(boolean inJob) {
        this.inJob = inJob;
    }
}
