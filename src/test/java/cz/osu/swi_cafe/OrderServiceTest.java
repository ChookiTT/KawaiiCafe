package cz.osu.swi_cafe;

import cz.osu.swi_cafe.repos.MenuRepository;
import cz.osu.swi_cafe.repos.OrderRepository;
import cz.osu.swi_cafe.repos.UserRepository;
import cz.osu.swi_cafe.services.OrderService;
import cz.osu.swi_cafe.tables.MenuItem;
import cz.osu.swi_cafe.tables.Order;
import cz.osu.swi_cafe.tables.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private MenuRepository menuRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void placeOrder_ShouldCalculatePriceAndSaveOrder() {

        Long userId = 1L;
        List<Long> itemIds = List.of(10L, 20L);

        User testUser = new User();
        testUser.setUserId(userId);
        testUser.setUsername("testUser");

        MenuItem coffee = new MenuItem();
        coffee.setItemId(10L);
        coffee.setItemPrice(50.0);

        MenuItem cake = new MenuItem();
        cake.setItemId(20L);
        cake.setItemPrice(80.0);
        Order order = new Order();
        order.setUser(testUser);
        order.setItems(List.of(coffee, cake));

        orderService.placeOrder(order);

        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(orderCaptor.capture());

        Order savedOrder = orderCaptor.getValue();

        assertEquals(testUser, savedOrder.getUser());
        assertEquals("testUser", savedOrder.getUser().getUsername());
        assertEquals(130.0, savedOrder.getOrderPrice());
        assertEquals(2, savedOrder.getItems().size());

        verify(userRepository, times(1)).findById(userId);
        verify(menuRepository, times(1)).findAllById(itemIds);
    }
}