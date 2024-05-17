package com.jsp.agro_springboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro_springboot.entity.Equipment;
import com.jsp.agro_springboot.entity.User;
import com.jsp.agro_springboot.repo.EquipmentRepo;

@RestController
public class EquipmentDao {
	@Autowired
	private EquipmentRepo erepo;


	public Equipment saveEquipment(Equipment equipment) {
		return erepo.save(equipment);
		
	}


	public Equipment updateEquipment(Equipment equipment) {
		Optional<Equipment> db = erepo.findById(equipment.getId());
		if(db.isPresent()) {
			Equipment data=db.get();
			if(equipment.getName()==null) {
				equipment.setName(data.getName());
			}
		    if(equipment.getQuantity()==0) {
				equipment.setQuantity(data.getQuantity());
			}
			if(equipment.getCostPerHour()==0) {
				equipment.setCostPerHour(data.getCostPerHour());
			}
			if(equipment.getUser()==null) {
				equipment.setUser(data.getUser());
			}
			return erepo.save(equipment);
		}
		else {
			return null;
		}
	}


	public Equipment deleteEquipment(String id) {
		Optional<Equipment> db = erepo.findById(id);
		if(db.isPresent()) {
			Equipment equipment=new Equipment();
			equipment.setUser(null);
			updateEquipment(equipment);
			erepo.deleteById(id);
			return db.get();
		}
		else {
			return null;
		}
	}


	public Equipment fetchByIdEqp(String id) {
		Optional<Equipment> db = erepo.findById(id);
		if(db!=null) {
			return db.get();
		}
		else {
			return null;
		}
	}


	public Equipment fetchByName(String name) {
		Equipment db = erepo.fetchByname(name);
		if(db!=null) {
			return db;
		}
		else {
			return null;
		}
	}
	
	public List<Equipment> fetchAllEqp() {
		return erepo.findAll();
	}
	
	public List<Equipment> fetchByUserId(User user) {
		List<Equipment> db = erepo.fetchByUserId(user);
		if(db!=null) {
			return db;
		}
		else {
			return null;
		}
		
	}


	public Equipment fetchEquipmentById(int eid) {
		// TODO Auto-generated method stub
		return null;
	}

}
