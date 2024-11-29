package com.coding.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.coding.entity.ICDCode;
import com.coding.service.ICDCodeService;

import helper.Helper;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/code")
public class ICDCodeController {
	

//	private static final String ICDCodeService = null;
	@Autowired
	private ICDCodeService icdCodeService;
	
	@PostMapping("/create")
	@ResponseBody
	public ICDCode createCode(@RequestBody ICDCode icdCode){
//		String string = UUID.randomUUID().toString(user);
	 ICDCode createCode = icdCodeService.createCode(icdCode);	
		return createCode;
	}
	
	//excel code
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file){
		if(Helper.checkExcelFormat(file))
		{
			this.icdCodeService.save(file);
			
			return ResponseEntity.ok(Map.of("message","File is upload and data is saved to db"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please upload excel file");
	}
	
	
	
	@GetMapping("/view")
	public List<ICDCode>viewAllCode() {
		List<ICDCode> viewAll = icdCodeService.viewAll();
		return viewAll;
	}
	
	@GetMapping("/view/{codeId}")
	public ICDCode viewCodeById(@PathVariable int codeId) {
			ICDCode viewCodeById = icdCodeService.viewCodeById(codeId);
		return viewCodeById;
	}
	
	@DeleteMapping("/delete/{codeId}")
	public void deleteUser(@PathVariable int codeId) {
		icdCodeService.deleteUser(codeId);
	}
	
	@PutMapping("/update/{codeId}")
	public ICDCode updateCode(@PathVariable int codeId, @RequestBody ICDCode newCode) {
		ICDCode updateCode = icdCodeService.updateCode(codeId,newCode);
		return updateCode;
//		return userService.updateUser(user);
	} 
	
}
