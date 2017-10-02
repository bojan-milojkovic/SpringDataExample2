package com.example.data.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.data.entities.Student;
import com.example.data.repositories.StudentRepository;
import com.example.data.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository dao;
	
	public List<Student> getAllStudents(){
		return (List<Student>) dao.findAll();
	}
	
	public Student getStudentById(Long id){
		return dao.findOne(id);
	}
	
	public void createNewStudent(Student s1){
		dao.save(s1);
	}
}
