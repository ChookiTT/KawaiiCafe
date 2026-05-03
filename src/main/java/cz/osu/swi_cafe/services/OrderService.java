package cz.osu.swi_cafe.services;

import cz.osu.swi_cafe.repos.MenuRepository;
import cz.osu.swi_cafe.repos.OrderRepository;
import cz.osu.swi_cafe.repos.UserRepository;
import cz.osu.swi_cafe.tables.MenuItem;
import cz.osu.swi_cafe.tables.Order;
import cz.osu.swi_cafe.tables.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional

    public void placeOrder(Order order) {
        User managedUser = entityManager.merge(order.getUser());
        order.setUser(managedUser);
        List<Long> ids = order.getItems().stream()
                .map(MenuItem::getItemId)
                .toList();
        List<MenuItem> managedItems = menuRepository.findAllById(ids);

        Order newOrder = new Order();
        newOrder.setUser(managedUser);
        newOrder.setItems(managedItems);
        newOrder.setOrderNote(order.getOrderNote());
        newOrder.setOrderDate(LocalDateTime.now());

        double orderPrice = managedItems.stream().mapToDouble(MenuItem::getItemPrice).sum();
        newOrder.setOrderPrice(orderPrice);
        orderRepository.save(newOrder);


    }
}