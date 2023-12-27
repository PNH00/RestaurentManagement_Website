package com.restapi.service;

import com.restapi.dto.MenuDTO;
import com.restapi.dto.TypeDTO;
import com.restapi.exceptions.RMValidateException;
import com.restapi.models.Menu;
import com.restapi.repositories.MenuRepository;
import com.restapi.services.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;
    @InjectMocks
    private MenuService menuService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createMenuWhenTypeIsEmptyShouldThrowException() {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setTypes(Collections.emptyList());
        assertThrows(RMValidateException.class, () -> menuService.createMenu(menuDTO));
    }

    @Test
    void createMenuWhenPriceIsNegativeShouldThrowException() {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setTypes(List.of(new TypeDTO(UUID.randomUUID(),"Type1")));
        menuDTO.setPrice(-10);
        assertThrows(RMValidateException.class, () -> menuService.createMenu(menuDTO));
    }

    @Test
    void getMenuByIdWhenMenuNotFoundShouldThrowException() {
        UUID id = UUID.randomUUID();
        when(menuRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(RMValidateException.class, () -> menuService.getMenuById(id));
    }

    @Test
    void updateMenuWhenMenuNotFoundShouldThrowException() {
        UUID id = UUID.randomUUID();
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setTypes(List.of(new TypeDTO(UUID.randomUUID(),"Type1")));
        when(menuRepository.existsById(id)).thenReturn(false);
        assertThrows(RMValidateException.class, () -> menuService.updateMenu(id, menuDTO));
    }

    @Test
    void deleteMenuWhenMenuNotFoundShouldThrowException() {
        UUID id = UUID.randomUUID();

        when(menuRepository.existsById(id)).thenReturn(false);
        assertThrows(RMValidateException.class, () -> menuService.deleteMenu(id));
    }

    @Test
    void searchMenusWhenKeywordIsNullShouldThrowException() {
        assertThrows(RMValidateException.class, () -> menuService.searchMenus(null));
    }
    @Test
    void searchMenuByNameWhenMenuFoundShouldReturnMenu() {
        String name = "TestMenu";
        Menu menu = new Menu();
        menu.setName(name);

        when(menuRepository.findByNameEquals(name)).thenReturn(menu);
        Menu result = menuService.searchMenuByName(name);
        assertNotNull(result);
        assertEquals(name, result.getName());
    }
}
