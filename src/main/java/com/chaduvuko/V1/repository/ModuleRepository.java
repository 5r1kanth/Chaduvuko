package com.chaduvuko.V1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chaduvuko.V1.model.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {

}
