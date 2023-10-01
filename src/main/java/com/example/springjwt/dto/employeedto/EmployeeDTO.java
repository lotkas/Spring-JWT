package com.example.springjwt.dto.employeedto;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class EmployeeDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Long positionId;

    private Integer age;

    private BigDecimal salary;

    private boolean inJob;

    public EmployeeDTO(@NotNull Long id, @NotNull String firstName,
                       @NotNull String lastName, @NotNull String email,
                       @NotNull Long positionId, @NotNull Integer age,
                       @NotNull BigDecimal salary, boolean inJob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.positionId = positionId;
        this.age = age;
        this.salary = salary;
        this.inJob = inJob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", positionId=" + positionId +
                ", age=" + age +
                ", salary=" + salary +
                ", inJob=" + inJob +
                '}';
    }
}
