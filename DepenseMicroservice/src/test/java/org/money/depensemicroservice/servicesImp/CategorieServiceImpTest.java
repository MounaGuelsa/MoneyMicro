package org.money.depensemicroservice.servicesImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.money.depensemicroservice.dtos.CategorieDto;
import org.money.depensemicroservice.entities.Categorie;
import org.money.depensemicroservice.mappers.CategorieMapper;
import org.money.depensemicroservice.repositories.CategorieRepository;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class CategorieServiceImpTest {

    @Mock
    private CategorieMapper categorieMapper;

    @Mock
    private CategorieRepository categorieRepository;

    @InjectMocks
    private CategorieServiceImp categorieService;

    private CategorieDto categorieDto;
    private Categorie categorie;

    @BeforeEach
     void setUp() {
        categorieDto = new CategorieDto();
        categorie = new Categorie();
    }

    @Test
     void testModifierCategorie() {
        // Given
        Long id = 1L;
        categorieDto.setNom("Test");
        categorieDto.setIcone("Icon");
        when(categorieRepository.findById(id)).thenReturn(Optional.of(categorie));
        when(categorieMapper.toDTO(null)).thenReturn(categorieDto); // Corrected stubbing

        // When
        CategorieDto result = categorieService.modifierCategorie(id, categorieDto);

        // Then
        assertEquals(categorieDto, result);
        verify(categorieRepository).save(categorie);
    }


    @Test
     void testObtenirCategories() {
        // Given
        when(categorieRepository.findAll()).thenReturn(List.of(categorie));
        when(categorieMapper.toDTO(categorie)).thenReturn(categorieDto);

        // When
        List<CategorieDto> result = categorieService.obtenirCategories();

        // Then
        assertEquals(1, result.size());
        assertEquals(categorieDto, result.get(0));
    }

    @Test
     void testObtenirCategorieParId() {
        // Given
        Long id = 1L;
        when(categorieRepository.findById(id)).thenReturn(Optional.of(categorie));
        when(categorieMapper.toDTO(categorie)).thenReturn(categorieDto);

        // When
        CategorieDto result = categorieService.obtenirCategorieParId(id);

        // Then
        assertEquals(categorieDto, result);
    }

    @Test
     void testAjouterCategorie() {
        // Given
        when(categorieMapper.toEntity(categorieDto)).thenReturn(categorie);
        when(categorieRepository.save(categorie)).thenReturn(categorie);
        when(categorieMapper.toDTO(categorie)).thenReturn(categorieDto);

        // When
        CategorieDto result = categorieService.ajouterCategorie(categorieDto);

        // Then
        assertEquals(categorieDto, result);
    }

    @Test
     void testSupprimerCategorie() {
        // Given
        Long id = 1L;
        when(categorieRepository.findById(id)).thenReturn(Optional.of(categorie));

        // When
        categorieService.supprimerCategorie(id);

        // Then
        verify(categorieRepository).delete(categorie);
    }
}
