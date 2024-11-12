package br.fsa.guilhermecassiano.microservices.studentservice.controller;

import br.fsa.guilhermecassiano.microservices.studentservice.model.Student;
import br.fsa.guilhermecassiano.microservices.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/{id}")
    public ResponseEntity<?> fetchStudentById(@PathVariable String id){
        return studentService.fetchStudentById(id);
    }
    @GetMapping
    public ResponseEntity<?> fetchStudents(){
        return studentService.fetchStudents();
    }
    @PostMapping("/add")
    public ResponseEntity<?> createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

}