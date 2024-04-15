package org.money.depensemicroservice.servicesImp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.money.depensemicroservice.entities.Depense;
import org.money.depensemicroservice.exceptions.CustomException;
import org.money.depensemicroservice.repositories.DepenseRepository;
import org.money.depensemicroservice.mappers.DepenseMapper;
import org.money.depensemicroservice.dtos.DepenseDto;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class DepenseServiceImpTest {

    @Mock
    private DepenseMapper depenseMapper;

    @Mock
    private DepenseRepository depenseRepository;

    @InjectMocks
    private DepenseServiceImp depenseService;

    @Test
     void testObtenirDepenses() {
        // Given
        Depense depense = new Depense();
        depense.setIdDepense(1L);
        depense.setMontant(50.0);

        DepenseDto depenseDto = new DepenseDto();
        depenseDto.setIdDepense(1L);
        depenseDto.setMontant(50.0);

        when(depenseRepository.findAll()).thenReturn(Collections.singletonList(depense));
        when(depenseMapper.toDTO(depense)).thenReturn(depenseDto);

        // When
        var result = depenseService.obtenirDepenses();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(depenseDto, result.get(0));
        verify(depenseRepository, times(1)).findAll();
        verify(depenseMapper, times(1)).toDTO(depense);
    }

    @Test
     void testObtenirDepenseParId() {
        // Given
        Long id = 1L;
        Depense depense = new Depense();
        depense.setIdDepense(id);

        DepenseDto depenseDto = new DepenseDto();
        depenseDto.setIdDepense(id);

        when(depenseRepository.findById(id)).thenReturn(Optional.of(depense));
        when(depenseMapper.toDTO(depense)).thenReturn(depenseDto);

        // When
        var result = depenseService.obtenirDepenseParId(id);

        // Then
        assertNotNull(result);
        assertEquals(depenseDto, result);
        verify(depenseRepository, times(1)).findById(id);
        verify(depenseMapper, times(1)).toDTO(depense);
    }

    @Test
     void testAjouterDepense() {
        // Given
        DepenseDto depenseDto = new DepenseDto();
        depenseDto.setMontant(100.0);

        Depense depense = new Depense();
        depense.setMontant(100.0);

        when(depenseMapper.toEntity(depenseDto)).thenReturn(depense);
        when(depenseRepository.save(depense)).thenReturn(depense);
        when(depenseMapper.toDTO(depense)).thenReturn(depenseDto);

        // When
        var result = depenseService.ajouterDepense(depenseDto);

        // Then
        assertNotNull(result);
        assertEquals(depenseDto, result);
        verify(depenseMapper, times(1)).toEntity(depenseDto);
        verify(depenseRepository, times(1)).save(depense);
        verify(depenseMapper, times(1)).toDTO(depense);
    }

    @Test
     void testModifierDepense() {
        // Given
        Long id = 1L;
        DepenseDto depenseDto = new DepenseDto();
        depenseDto.setMontant(100.0);

        Depense depense = new Depense();
        depense.setIdDepense(id);
        depense.setMontant(50.0);

        when(depenseRepository.findById(id)).thenReturn(Optional.of(depense));
        when(depenseRepository.save(depense)).thenReturn(depense);
        when(depenseMapper.toDTO(depense)).thenReturn(depenseDto);

        // When
        var result = depenseService.modifierDepense(id, depenseDto);

        // Then
        assertNotNull(result);
        assertEquals(depenseDto, result);
        assertEquals(depenseDto.getMontant(), depense.getMontant());
        verify(depenseRepository, times(1)).findById(id);
        verify(depenseRepository, times(1)).save(depense);
        verify(depenseMapper, times(1)).toDTO(depense);
    }

    @Test
     void testModifierDepense_WhenDepenseNotFound() {
        // Given
        Long id = 1L;
        DepenseDto depenseDto = new DepenseDto();

        when(depenseRepository.findById(id)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(CustomException.class, () -> depenseService.modifierDepense(id, depenseDto));
        verify(depenseRepository, times(1)).findById(id);
    }

    @Test
     void testSupprimerDepense() {
        // Given
        Long id = 1L;

        // When
        depenseService.supprimerDepense(id);

        // Then
        verify(depenseRepository, times(1)).deleteById(id);
    }

    @Test
     void testTotalDepensesParMois() {
        // Given
        Depense depense1 = new Depense();
        depense1.setMontant(50.0);

        Depense depense2 = new Depense();
        depense2.setMontant(100.0);
        when(depenseRepository.findDepensesByCurrentMonth()).thenReturn(Collections.singletonList(depense1));
        // When
        var result = depenseService.totalDepensesParMois();
        // Then
        assertEquals(50.0, result);
        verify(depenseRepository, times(1)).findDepensesByCurrentMonth();
    }
}
