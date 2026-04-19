package cz.osu.swi_cafe.services;

import cz.osu.swi_cafe.dto.MenuItemDTO;
import cz.osu.swi_cafe.repos.MenuRepository;
import cz.osu.swi_cafe.tables.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class MenuService {
    @Autowired
    MenuRepository menuRepository;

    public List<MenuItemDTO> getAllItems() {

        List<MenuItem> items = menuRepository.findAll();

        return items.stream()
                .map(item -> new MenuItemDTO(
                        item.getItemId(),
                        item.getItemName(),
                        item.getItemPrice()
                ))
                .collect(Collectors.toList());
    }
}
