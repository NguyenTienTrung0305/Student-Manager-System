package com.springboot.sms.controller;


import org.springframework.stereotype.Controller;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.sms.entity.Student;
import com.springboot.sms.reponsitory.StudentReponsitory;
import com.springboot.sms.service.StudentService;

@Controller
public class StudentController {
	//@Autowired
	private StudentService studentService;
	private StudentReponsitory reponsitory;

	public StudentController(StudentService studentService , StudentReponsitory reponsitory) {
		super();
		this.studentService = studentService;
		this.reponsitory = reponsitory;
	}
	
	// handler method to handle list students and return model and view
	@GetMapping("/students")
	public String listStudent(Model model) {
		// model luu tru thong tin duoi dang key-value , html se anh xa den key de render value ra web
		// key : students
		// value : studentService.getAllStudents()
		model.addAttribute("students" , studentService.getAllStudents());
		return "students";
	}
	
	// add student
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		// create student object to hold student from data
		Student student = new Student();
		model.addAttribute("student",student);
		// value sẽ được lấy thông qua key student , cụ thể th:object="${student}"
		// goi den create_student.html
		return "create_student"; 
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		// luu student xuong database
		studentService.saveStudent(student);
		return "redirect:/students"; // sau khi save xong student thi tro lai menu list student
	}
	
	
	// edit student
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student" , studentService.findStudentById(id));
		return "edit_student";
	}
	
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id , @ModelAttribute("student") Student student , Model model) {
		
		// get student from database and update
		Student oldStudent = studentService.findStudentById(id);
		oldStudent.setId(id);
		oldStudent.setFirstName(student.getFirstName());
		oldStudent.setLastName(student.getLastName());
		oldStudent.setEmail(student.getEmail());
		
		// save update student into database
		studentService.updateStudent(oldStudent);
		return "redirect:/students";
		
	}
	
	// delete student
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}
	
	
	// search info
	@GetMapping("/students/search")
	public String SearchInfoStudent(Model model) {
		Student student = new Student();
		model.addAttribute("student",student);
		return "searching";
	}
	
	@PostMapping("students/info")
	public String ShowInfoStudent(@ModelAttribute("student") Student student , Model model) {
		model.addAttribute("students" , 
				reponsitory.findByFirstNameAndLastNameAndEmail
				(student.getFirstName() , student.getLastName() , student.getEmail()));
		return "showinfo";
	}
	
	// back to menu
	@GetMapping("/students/backtomenu")
	public String backMenu() {
		return "redirect:/students";
	}
	
}
 