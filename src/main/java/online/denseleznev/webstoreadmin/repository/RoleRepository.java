package online.denseleznev.webstoreadmin.repository;

import online.denseleznev.webstoreadmin.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByname(String name);
}
