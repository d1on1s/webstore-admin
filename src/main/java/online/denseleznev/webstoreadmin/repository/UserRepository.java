package online.denseleznev.webstoreadmin.repository;

import online.denseleznev.webstoreadmin.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
