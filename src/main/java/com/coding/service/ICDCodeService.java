package com.coding.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.coding.entity.ICDCode;
import com.coding.repository.ICDCodeRepository;

import helper.Helper;


@Service
public class ICDCodeService {

	@Autowired
	private ICDCodeRepository icdCodeRepository;
	
	public void save(MultipartFile file) {
		
		try {
			List<ICDCode> icdcode = Helper.convertExcelToListOfICDCode(file.getInputStream());
			this.icdCodeRepository.saveAll(icdcode);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<ICDCode> getAllProducts() {
		return this.icdCodeRepository.findAll();
	}
	
	public ICDCode createCode(ICDCode icdCode) {
		ICDCode save =icdCodeRepository.save(icdCode);
				return save;
	}

	public List<ICDCode> viewAll() {
		List<ICDCode> findAll = icdCodeRepository.findAll();
		return findAll;
	}

	public ICDCode viewCodeById(int codeId) {
		ICDCode findById = icdCodeRepository.findById(codeId);
		return findById;
	}

	public void deleteUser(int id) {
		icdCodeRepository.deleteById(id);
		
	}

	public ICDCode updateCode(int codeId ,ICDCode newCode) {
		ICDCode oldCode=icdCodeRepository.findById(codeId);
		oldCode.setIcd_index(newCode.getIcd_index());
		oldCode.setIcd_code(newCode.getIcd_code());
		oldCode.setDescription(newCode.getDescription());
		
		ICDCode save = icdCodeRepository.save(oldCode);
		return save;
		}		
}
