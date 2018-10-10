package com.corning.activiti.sb.repository.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Person {

    public Person(String username, String firstName, String lastName, Date birthDate) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private Date birthDate;
}
