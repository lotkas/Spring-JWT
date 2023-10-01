package com.example.springjwt.dto.employeedto;

import com.example.springjwt.models.Position;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class EmployeeUpdateByAdminDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Integer age;

    private Position positionId;

    private BigDecimal salary;

    private boolean inJob;

    public EmployeeUpdateByAdminDTO(@NotNull Long id, @NotNull String firstName,
                                    @NotNull String lastName, @NotNull String email,
                                    @NotNull Integer age, @NotNull Position positionId,
                                    @NotNull BigDecimal salary, boolean inJob) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
