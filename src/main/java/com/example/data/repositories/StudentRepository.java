package com.example.data.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.data.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{
	
	public Student findByEmail(final String email);
	
	@Query("select s from Student s where s.email = :param")
	public Student mySelect2(@Param("param") final String email);

}