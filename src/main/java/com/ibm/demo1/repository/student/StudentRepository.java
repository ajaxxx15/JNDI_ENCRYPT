package com.ibm.demo1.repository.student;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.demo1.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
