package spring.security.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.security.dao.entities.Authority;

@Repository
public interface RoleRepository extends CrudRepository<Authority, Long> {
}
