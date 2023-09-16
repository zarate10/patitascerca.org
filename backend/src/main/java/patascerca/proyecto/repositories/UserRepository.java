package patascerca.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import patascerca.proyecto.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
