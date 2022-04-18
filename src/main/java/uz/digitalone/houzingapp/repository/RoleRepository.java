package uz.digitalone.houzingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.digitalone.houzingapp.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
