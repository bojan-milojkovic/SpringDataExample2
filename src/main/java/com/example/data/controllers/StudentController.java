package com.example.data.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.data.entities.Student;
import com.example.data.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentServiceImpl;
	
	@RequestMapping(value = "/students", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Student> getAllStudents(){
		return studentServiceImpl.getAllStudents();
	}
	
	@RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
	public @ResponseBody Student getOneParticularStudentPath(@PathVariable("id") Long id){
		return studentServiceImpl.getStudentById(id);
	}

	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public @ResponseBody Student getOneParticularStudentQuery(@RequestParam(value="id", required=true) Long id){
		return studentServiceImpl.getStudentById(id);
	}
	
	@RequestMapping(value = "/students", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void createNewStudent(@RequestBody Student s1){
		studentServiceImpl.createNewStudent(s1);
	}
}