package cz.osu.swi_cafe.repos;

import cz.osu.swi_cafe.tables.Alergen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlergenRepository extends JpaRepository<Alergen, Long> {
}