package online.denseleznev.webstoreadmin.service;

import online.denseleznev.webstoreadmin.domain.User;
import online.denseleznev.webstoreadmin.domain.security.UserRole;

import java.util.Set;

public interface UserService {
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);
}
