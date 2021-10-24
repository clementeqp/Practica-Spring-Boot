package com.clemen.Ejercicio4.repository;

import com.clemen.Ejercicio4.entities.Labtop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LabtopRepository extends JpaRepository<Labtop, Long> {
}
