package com.jsp.agro_springboot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.agro_springboot.entity.Equipment;
import com.jsp.agro_springboot.entity.User;

public interface EquipmentRepo extends JpaRepository<Equipment, String>{
	
	@Query("select a from Equipment a where a.name=:name")
	Equipment fetchByname(String name);
	
	@Query("select a from Equipment a where a.user=?1")
	List<Equipment> fetchByUserId(User user);
	
	
}
