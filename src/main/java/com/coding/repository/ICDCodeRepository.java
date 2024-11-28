package com.coding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coding.entity.ICDCode;

public interface ICDCodeRepository extends JpaRepository<ICDCode, Integer>{
	public ICDCode findById(int codeId);
}
