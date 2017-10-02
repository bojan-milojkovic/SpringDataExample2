package com.example.data.service;

import java.util.List;

import com.example.data.entities.Student;

public interface StudentService {
	public List<Student> getAllStudents();
	
	public Student getStudentById(Long id);
	
	public void createNewStudent(Student s1);
}
