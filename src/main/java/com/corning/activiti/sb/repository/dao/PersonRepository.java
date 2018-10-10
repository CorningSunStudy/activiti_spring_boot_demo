package com.corning.activiti.sb.repository.dao;

import com.corning.activiti.sb.repository.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByUsername(String username);
}
