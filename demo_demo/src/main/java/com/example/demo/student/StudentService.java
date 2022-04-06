package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepo;
	
	public List<Student> getStudents() {
		return studentRepo.findAll();
	}
	
	public void addNewStudent(Student student) {
		Optional<Student> studentByEmail = studentRepo.findStudentByEmail(student.getEmail());
		if(studentByEmail.isPresent()) {
			throw new IllegalStateException("email taken");
		}
		studentRepo.save(student);
	}

	public void deleteStudent(Long studentId) {
		boolean exists = studentRepo.existsById(studentId);
		if(!exists) {
			throw new IllegalStateException("Student with the id "+studentId+ " does not exist");
		} else {
			studentRepo.deleteById(studentId);
		}
	}
	
	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		Student student = studentRepo.findById(studentId).orElseThrow(() -> new IllegalStateException("user does not exist"));
		
		if(name != null && !Objects.equals(name, student.getName()) && name.length() > 0) {
			student.setName(name);
		}
		if(email != null && !Objects.equals(email, student.getEmail()) && email.length() > 0) {
			Optional<Student> studentByEmail = studentRepo.findStudentByEmail(email);
			if(studentByEmail.isPresent()) {
				throw new IllegalStateException("email taken");
			}
			student.setEmail(email);
		}
		
	}
}
