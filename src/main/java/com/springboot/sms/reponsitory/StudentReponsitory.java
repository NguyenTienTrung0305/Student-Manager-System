package com.springboot.sms.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.sms.entity.Student;

public interface StudentReponsitory extends JpaRepository<Student, Long>{
	// jpareponsitory tự tạo query , yêu cầu đuôi tên hàm phải giống biến 
	List<Student> findByLastName(String lastName);
	List<Student> findByFirstName(String firstName);
	
	// search đuôi email
	List<Student> findByEmailEndingWith(String email);
	List<Student> findByFirstNameAndLastNameAndEmail(String firstName , String lastName , String email);
	
	//Cách truyền tham số là gọi theo thứ tự các tham số của method bên dưới ?1, ?2
	// Student là tên class chứ không phải tên table trong database
//	@Query("select u from Student u where u.email = ?1")
//	List<Student> findByEmail(String email);
}
