package com.example.springjwt.dto.employeedto;

import org.jetbrains.annotations.NotNull;

public class EmployeeUpdateByUserDTO {

    private String firstName;

    private String lastName;

    private String email;

    private Integer age;

    public EmployeeUpdateByUserDTO(@NotNull String firstName, @NotNull String lastName,
                                   @NotNull String email, @NotNull Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
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
}
