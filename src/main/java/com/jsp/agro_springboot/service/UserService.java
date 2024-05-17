package com.jsp.agro_springboot.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.jsp.agro_springboot.dao.UserDao;
import com.jsp.agro_springboot.entity.User;
import com.jsp.agro_springboot.exception.EmailAlreadyRegisteredException;
import com.jsp.agro_springboot.exception.EmailNotSendException;
import com.jsp.agro_springboot.exception.PasswordMismatchException;
import com.jsp.agro_springboot.exception.UserNotFound;
import com.jsp.agro_springboot.repo.UserRepo;
import com.jsp.agro_springboot.util.ResponseStructure;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class UserService {
	@Autowired
	private UserDao dao;
	@Autowired
	private UserRepo repo;
	
	
	@Autowired
	private JavaMailSender mailSender;

	public ResponseEntity<ResponseStructure<User>> registerUser(User user) {
		ResponseStructure<User> m = new ResponseStructure<User>();
		User db1 = dao.fetchByEmail(user.getEmail());
		if (db1 == null) {
			User db = dao.saveUser(user);
			m.setData(db);
			m.setMsg("User details saved successfully..");
			m.setStatus(HttpStatus.CREATED.value());

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("farmerfriendteam05@gmail.com");
			message.setTo(user.getEmail());
			message.setSubject("Email Registration");
			message.setText("Dear " + db.getFirstName() + " " + db.getLastName() + " \""
					+ "Welcome to Farmer Friend! We are thrilled to have you join our community of farmers dedicated to sustainable agriculture and growth.\\n\\n\" \r\n"
					+ "Your registration is the first step towards a journey of discovery, empowerment, and support. Whether you're seeking valuable insights, connecting with fellow farmers, or exploring innovative solutions, Farmer Friend is here to assist you every step of the way.\\n\\n\" \r\n"
					+ "As you embark on this exciting adventure, remember that you're not alone. Our team is committed to providing you with the resources, guidance, and encouragement you need to thrive in your agricultural endeavors.\\n\\n\" \r\n"
					+ "Feel free to explore our platform, engage with other members, and share your experiences. Together, we can cultivate a brighter future for agriculture.\\n\\n\" \r\n"
					+ "Once again, welcome aboard!" + "/Best regards" + "/Team - 05" + "/Farmer Friend\"😊😊😊😊");
			mailSender.send(message);
			return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.CREATED);
		} else {
			throw new EmailAlreadyRegisteredException("Email is already registerd");
		}
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		User db = dao.fetchById(user.getId());
		if (db != null) {
			ResponseStructure<User> rs = new ResponseStructure<User>();
			rs.setData(dao.updateUser(user));
			rs.setMsg("User Updated Successfully..");
			rs.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.FOUND);
		} else {
			throw new UserNotFound();
		}
	}

	public ResponseEntity<ResponseStructure<User>> deleteUser(String id) {
		User db = dao.fetchById(id);
		if (db != null) {
			ResponseStructure<User> rs = new ResponseStructure<User>();
			rs.setData(dao.deleteUser(id));
			rs.setMsg("student is found");
			rs.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(HttpStatus.NO_CONTENT);
		} else {
			throw new UserNotFound();
		}
	}
	
	public ResponseEntity<ResponseStructure<User>> fetchById(String id) {
		User db = dao.fetchById(id);
		if (db != null) {
			ResponseStructure<User> rs = new ResponseStructure<User>();
			rs.setData(db);
			rs.setMsg("Student found successfully..");
			rs.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.FOUND);
		} else {
			throw new UserNotFound();
		}
	}

	public ResponseEntity<ResponseStructure<List<User>>> fetchAll() {
		List<User> db = dao.fetchAllUser();
		if(db!=null) {
			ResponseStructure<List<User>> rs=new ResponseStructure<List<User>>();
			rs.setData(db);
			rs.setMsg("Student found successfully..");
			rs.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<User>>>(rs,HttpStatus.FOUND);
		}
		else {
			throw new UserNotFound();
		}
	}

	public ResponseEntity<ResponseStructure<User>> loginUser(String email, String password) {
		User db = dao.fetchByEmail(email);
		if (db != null) {
			ResponseStructure<User> rs = new ResponseStructure<User>();
			if (db.getPwd().equals(password)) {
				rs.setData(db);
				rs.setMsg("Login Successfull!");
				rs.setStatus(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.ACCEPTED);
			} else {
				throw new PasswordMismatchException(
						"Incorrect Password! Please make sure you have entered the correct password. If you've forgotten your password, you can reset it using the 'Forgot Password' option on the login page.");
			}
		} else {
			throw new UserNotFound();
		}
	}

	public ResponseEntity<ResponseStructure<Integer>> sendOtp(String email) {
		User db = dao.fetchByEmail(email);
		if (db != null) {
			Random random = new Random();
			int otp = 100000 + random.nextInt(900000);
			ResponseStructure<Integer> rs = new ResponseStructure<Integer>();
			rs.setData(otp);
			rs.setMsg("OTP sent successfully");
			rs.setStatus(HttpStatus.FOUND.value());
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper;

			try {
				helper = new MimeMessageHelper(message, true);
				helper.setFrom("farmerfriendteam05@gmail.com");
				helper.setTo(email);
				helper.setSubject("Forgot Password OTP Verification!");
				String emailContent = "<html><body><div style='text-align: center;'><img src='cid:fpBanner' alt='Forgot Password Banner' style='display: block; margin: 0 auto;'> </div><br>\n\n"
						+ "<p>Dear <strong><em>" + db.getFirstName() + " " + db.getLastName()
						+ ",</em></strong></p>\n\n"
						+ "<p>You have requested to reset your password for Farmer Friend. To proceed, please use the following OTP verification code:</p>\n\n"
						+ "<p><strong>OTP:</strong> " + otp + " </p>\n\n"
						+ "<p>Please enter this OTP on the password reset page to verify your identity and continue with the password reset process. This OTP is valid for a single use and will expire shortly.</p>\n\n"
						+ "<p>If you did not request this password reset or have any concerns, please disregard this message.</p>\n\n"
						+ "<p>Thank you for using Farmer Friend.</p>\n\n"
						+ "<p><strong>Best regards,</strong><br>Team - 05<br>Farmer Friend</p> </body></html>";
				helper.setText(emailContent, true);
				ClassPathResource banner = new ClassPathResource("image/img1.jpg");
				helper.addInline("fpBanner", banner);
				mailSender.send(message);
				return new ResponseEntity<ResponseStructure<Integer>>(rs, HttpStatus.FOUND);
			} catch (javax.mail.MessagingException e) {
				System.out.println(e);
				throw new EmailNotSendException("failed to send the email");
			}

		}
		throw new UserNotFound();
	}

}
