package com.Sampleproject.LogiPage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Sampleproject.LogiPage.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{

}
