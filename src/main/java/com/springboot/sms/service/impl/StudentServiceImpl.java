package com.springboot.sms.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.sms.entity.Student;
import com.springboot.sms.reponsitory.StudentReponsitory;
import com.springboot.sms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	private StudentReponsitory studentReponsitory;
	
	
	
	public StudentServiceImpl(StudentReponsitory studentReponsitory) {
		super();
		this.studentReponsitory = studentReponsitory;
	}



	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentReponsitory.findAll();
	}



	@Override
	public Student saveStudent(Student student) {
		// TODO Auto-generated method stub
		return studentReponsitory.save(student);
	}



	@Override
	public Student findStudentById(Long id) {
		// TODO Auto-generated method stub
		return studentReponsitory.findById(id).get();
	}



	@Override
	public Student updateStudent(Student student) {
		// TODO Auto-generated method stub
		return studentReponsitory.save(student);
	}



	@Override
	public void deleteStudentById(Long id) {
		// TODO Auto-generated method stub
		studentReponsitory.deleteById(id);
	}
	

}
