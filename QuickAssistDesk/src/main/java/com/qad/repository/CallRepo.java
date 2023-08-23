package com.qad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qad.entity.Call;

public interface CallRepo extends JpaRepository<Call,Integer>{

}
