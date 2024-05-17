package com.jsp.agro_springboot.enums;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum UserType {
	@Enumerated(EnumType.STRING)
	FARMER,
	VENDOR,
	SPECIALIST
}
