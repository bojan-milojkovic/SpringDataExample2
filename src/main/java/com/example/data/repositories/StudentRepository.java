package com.example.data.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.data.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{

}