package cz.osu.swi_cafe;

import cz.osu.swi_cafe.dto.MenuItemDTO;
import cz.osu.swi_cafe.repos.MenuRepository;
import cz.osu.swi_cafe.services.MenuService;
import cz.osu.swi_cafe.tables.MenuItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private MenuService menuService;

    @Test
    void getAllMenuItems_ShouldReturnList() {
        MenuItem item1 = new MenuItem();
        item1.setItemName("Espresso");
        item1.setItemPrice(55.0);

        MenuItem item2 = new MenuItem();
        item2.setItemName("Cappuccino");
        item2.setItemPrice(75.0);
        when(menuRepository.findAll()).thenReturn(List.of(item1, item2));

        List<MenuItemDTO> result = menuService.getAllItems();

        assertEquals(2, result.size());
        assertEquals("Espresso", result.get(0).getItemName());
        assertEquals(75.0, result.get(1).getItemPrice());
    }
}
