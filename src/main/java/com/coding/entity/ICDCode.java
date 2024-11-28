package com.coding.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="icd_codes")
public class ICDCode {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int codeId;
	private String icd_index;
	private Integer icd_code;
	private String description;
	
		
		
}
