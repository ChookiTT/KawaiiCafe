package cz.osu.swi_cafe.repos;

import cz.osu.swi_cafe.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}