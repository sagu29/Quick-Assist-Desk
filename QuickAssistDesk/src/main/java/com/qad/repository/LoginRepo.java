package com.qad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qad.entity.Login;

public interface LoginRepo extends JpaRepository<Login,Integer>{

}
