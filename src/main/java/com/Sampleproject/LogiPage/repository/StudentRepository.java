package com.Sampleproject.LogiPage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Sampleproject.LogiPage.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
