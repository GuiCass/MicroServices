package br.fsa.guilhermecassiano.microservices.studentservice.service;

import br.fsa.guilhermecassiano.microservices.studentservice.dto.School;
import br.fsa.guilhermecassiano.microservices.studentservice.dto.StudentResponse;
import br.fsa.guilhermecassiano.microservices.studentservice.model.Student;
import br.fsa.guilhermecassiano.microservices.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<?> createStudent(Student student){
        try{
            return new ResponseEntity<Student>(studentRepository.save(student), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> fetchStudentById(String id){
        Optional<Student> student =  studentRepository.findById(id);
        if(student.isPresent()){
            School school = restTemplate.getForObject("http://school-service/school/" + student.get().getSchoolId(), School.class);
            StudentResponse studentResponse = new StudentResponse(
                    student.get().getId(),
                    student.get().getName(),
                    student.get().getAge(),
                    student.get().getGender(),
                    school
            );
            return new ResponseEntity<>(studentResponse, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Student Found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> fetchStudents(){
        List<Student> students = studentRepository.findAll();
        if(students.size() > 0){
            return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("No Students",HttpStatus.NOT_FOUND);
        }
    }

}