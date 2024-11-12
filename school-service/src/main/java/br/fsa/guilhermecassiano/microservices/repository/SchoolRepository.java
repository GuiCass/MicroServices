package br.fsa.guilhermecassiano.microservices.repository;

import br.fsa.guilhermecassiano.microservices.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School,Integer> {
}
