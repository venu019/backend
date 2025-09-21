package com.student.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Student;
import com.student.repository.StudentRepository;

@CrossOrigin(origins = "https://lavanays.netlify.app")
@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentRepository repo;

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return repo.save(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student req) {
        Optional<Student> studentOpt = repo.findById(id);
        if (studentOpt.isPresent()) {
            Student stu = studentOpt.get();
            stu.setName(req.getName());
            stu.setEmail(req.getEmail());
            stu.setCourse(req.getCourse());
            return ResponseEntity.ok(repo.save(stu));
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        repo.deleteById(id);
    }
}



