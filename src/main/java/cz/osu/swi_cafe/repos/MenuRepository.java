package cz.osu.swi_cafe.repos;

import cz.osu.swi_cafe.tables.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface MenuRepository extends JpaRepository<MenuItem, Long> {
    }
