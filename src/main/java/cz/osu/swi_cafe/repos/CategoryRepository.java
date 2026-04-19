package cz.osu.swi_cafe.repos;

import cz.osu.swi_cafe.tables.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface CategoryRepository extends JpaRepository<Category, Long> {
    }

