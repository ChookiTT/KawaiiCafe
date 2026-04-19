package cz.osu.swi_cafe.repos;

import cz.osu.swi_cafe.tables.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}