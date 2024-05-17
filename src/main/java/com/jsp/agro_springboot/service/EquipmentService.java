package com.jsp.agro_springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.agro_springboot.dao.EquipmentDao;
import com.jsp.agro_springboot.dao.UserDao;
import com.jsp.agro_springboot.entity.Equipment;
import com.jsp.agro_springboot.entity.User;
import com.jsp.agro_springboot.exception.EquipmentNameNotFound;
import com.jsp.agro_springboot.exception.UserNotFound;
import com.jsp.agro_springboot.util.ResponseStructure;

@Service
public class EquipmentService {
	@Autowired
	private EquipmentDao edao;
	@Autowired
	private UserDao udao;

	public ResponseEntity<ResponseStructure<Equipment>> saveEquipment(String uid, Equipment equipment) {
		User db = udao.fetchById(uid);
		if(db!=null) {
			equipment.setUser(db);
			edao.saveEquipment(equipment);
			ResponseStructure<Equipment> m=new ResponseStructure<Equipment>();
			m.setData(equipment);
			m.setMsg("equipment saved successfully");
			m.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.CREATED);
		}
		else{
			throw new UserNotFound();
		}
	}

	public ResponseEntity<ResponseStructure<Equipment>> updateEquipment(Equipment equipment) {
		Equipment db = edao.updateEquipment(equipment);
		if(db!=null) {
			ResponseStructure<Equipment> m=new ResponseStructure<Equipment>();
			m.setData(db);
			m.setMsg("updated successfully");
			m.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.CREATED);
		}
		else {
			throw new UserNotFound();
		}
	}

	public ResponseEntity<ResponseStructure<Equipment>> deleteEquipment(String id) {
		Equipment db = edao.deleteEquipment(id);
		if(db!=null) {
			ResponseStructure<Equipment> m=new ResponseStructure<Equipment>();
			m.setData(db);
			m.setMsg("deleted successfully");
			m.setStatus(HttpStatus.GONE.value());
			
			return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.GONE);
		}
		else {
			throw new UserNotFound();
		}
	}

	public ResponseEntity<ResponseStructure<Equipment>> fetchByIdEqp(String id) {
		Equipment db =edao.fetchByIdEqp(id);
		if(db!=null) {
			ResponseStructure<Equipment> m=new ResponseStructure<Equipment>();
			m.setData(db);
			m.setMsg("fetched successfully");
			m.setStatus(HttpStatus.FOUND.value());
			 return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.FOUND);
		}
		else {
			return null;
		}
	}

	public ResponseEntity<ResponseStructure<Equipment>> fetchByName(String name) {
		Equipment db = edao.fetchByName(name);
		if(db!=null) {
			ResponseStructure<Equipment> m=new ResponseStructure<>();
			m.setData(db);
			m.setMsg("fetched Equipments Based on name");
			m.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.FOUND);
		}
		else {
			throw new EquipmentNameNotFound();
		}
	}

	public ResponseEntity<ResponseStructure<Equipment>> fetchAllEqp() {
		List<Equipment> db = edao.fetchAllEqp();
		if(db!=null) {
			ResponseStructure<Equipment> m=new ResponseStructure<Equipment>();
			m.setListdata(db);
			m.setMsg("all equipments fetched Successfully");
			m.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.FOUND);
	   }
	   else {
			throw new UserNotFound();
		}
	}

	public ResponseEntity<ResponseStructure<Equipment>> fetchByUserId(String uid) {
		User db = udao.fetchById(uid);
		if(db!=null) {
			List<Equipment> db1=edao.fetchByUserId(db);
			ResponseStructure<Equipment> m=new ResponseStructure<Equipment>();
			m.setListdata(db1);
			m.setMsg("data fetched succesfulyy based on user id");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.FOUND);
		}
		else {
			throw new UserNotFound();
		}
	}

}
