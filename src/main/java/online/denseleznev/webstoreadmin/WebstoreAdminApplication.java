package online.denseleznev.webstoreadmin;

import java.util.HashSet;
import java.util.Set;

import online.denseleznev.webstoreadmin.domain.User;
import online.denseleznev.webstoreadmin.domain.security.Role;
import online.denseleznev.webstoreadmin.domain.security.UserRole;
import online.denseleznev.webstoreadmin.service.UserService;
import online.denseleznev.webstoreadmin.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class WebstoreAdminApplication implements CommandLineRunner{
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(WebstoreAdminApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setUsername("admin");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("adminadminadmin"));
		user1.setEmail("mail@denseleznev.online");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1= new Role();
		role1.setRoleId(0);
		role1.setName("ROLE_ADMIN");
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
	}
}
