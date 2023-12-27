package com.restapi.service;

import com.restapi.dto.TypeDTO;
import com.restapi.exceptions.RMValidateException;
import com.restapi.models.Type;
import com.restapi.repositories.TypeRepository;
import com.restapi.services.TypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TypeServiceTest {

    @Mock
    private TypeRepository typeRepository;

    @InjectMocks
    private TypeService typeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTypesWhenSuccessShouldReturnSuccessResponse() {
        List<Type> types = Arrays.asList(new Type(), new Type());
        when(typeRepository.findAll()).thenReturn(types);
        List<TypeDTO> result = typeService.getAllTypes();
        assertEquals(types.size(), result.size());
    }

    @Test
    void getTypeByIdWhenTypeExistsShouldReturnTypeDTO() {
        UUID id = UUID.randomUUID();
        Type type = new Type();
        when(typeRepository.findById(id)).thenReturn(Optional.of(type));
        TypeDTO result = typeService.getTypeById(id);
        assertNotNull(result);
    }

    @Test
    void getTypeByIdWhenTypeNotFoundShouldThrowException() {
        UUID id = UUID.randomUUID();
        when(typeRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(RMValidateException.class, () -> typeService.getTypeById(id));
    }

    @Test
    void createTypeWhenSuccessShouldReturnTypeDTO() {
        TypeDTO typeDTO = new TypeDTO();
        typeDTO.setType("SomeType");
        TypeDTO result = typeService.createType(typeDTO);
        assertNotNull(result);
        assertEquals(typeDTO.getType(), result.getType());
    }

    @Test
    void saveAllTypeWhenSuccessShouldReturnListOfTypeDTO() {
        TypeDTO typeDTOHot = new TypeDTO(UUID.randomUUID(),"Hot");
        TypeDTO typeDTOCool = new TypeDTO(UUID.randomUUID(),"Cold");
        List<TypeDTO> typeDTOs = Arrays.asList(typeDTOCool,typeDTOHot);
        List<Type> result = typeService.saveAllType(typeDTOs);
        assertEquals(typeDTOs.size(), result.size());
    }

    @Test
    void updateTypeWhenTypeNotFoundShouldThrowException() {
        UUID id = UUID.randomUUID();
        TypeDTO typeDTO = new TypeDTO();
        when(typeRepository.existsById(id)).thenReturn(false);
        assertThrows(RMValidateException.class, () -> typeService.updateType(id, typeDTO));
    }

    @Test
    void deleteTypeWhenTypeNotFoundShouldThrowException() {
        UUID id = UUID.randomUUID();
        when(typeRepository.existsById(id)).thenReturn(false);
        assertThrows(RMValidateException.class, () -> typeService.deleteType(id));
    }
}