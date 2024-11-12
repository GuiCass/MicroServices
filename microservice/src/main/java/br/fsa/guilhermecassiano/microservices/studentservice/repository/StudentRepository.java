package br.fsa.guilhermecassiano.microservices.studentservice.repository;

import br.fsa.guilhermecassiano.microservices.studentservice.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
}