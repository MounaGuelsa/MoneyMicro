package org.money.revenuemicroservice.servicesImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.money.revenuemicroservice.dtos.RevenueDto;
import org.money.revenuemicroservice.entities.Revenue;
import org.money.revenuemicroservice.exceptions.CustomException;
import org.money.revenuemicroservice.mappers.RevenueMapper;
import org.money.revenuemicroservice.repositories.RevenueRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RevenueServiceImpTest {

    @Mock
    private RevenueMapper revenueMapper;

    @Mock
    private RevenueRepository revenueRepository;

    @InjectMocks
    private RevenueServiceImp revenueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenirRevenues_Success() {
        List<Revenue> revenues = new ArrayList<>();
        revenues.add(new Revenue());
        revenues.add(new Revenue());

        when(revenueRepository.findAll()).thenReturn(revenues);
        when(revenueMapper.toDTO(any(Revenue.class))).thenReturn(new RevenueDto());

        List<RevenueDto> result = revenueService.obtenirRevenues();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void obtenirRevenues_Exception() {
        when(revenueRepository.findAll()).thenThrow(new RuntimeException("Database connection error"));

        assertThrows(CustomException.class, () -> revenueService.obtenirRevenues());
    }



    @Test
    void obtenirRevenueParId_NonExistingId() {
        when(revenueRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> revenueService.obtenirRevenueParId(1L));
    }

    @Test
    void ajouterRevenue_Success() {
        RevenueDto revenueDto = new RevenueDto();
        Revenue revenue = new Revenue();

        when(revenueMapper.toEntity(revenueDto)).thenReturn(revenue);
        when(revenueMapper.toDTO(revenue)).thenReturn(revenueDto);

        RevenueDto result = revenueService.ajouterRevenue(revenueDto);

        assertNotNull(result);
    }

    @Test
    void modifierRevenue_ExistingId() {
        Long id = 1L;
        RevenueDto revenueDto = new RevenueDto();
        revenueDto.setIdRevenue(id);
        Revenue revenue = new Revenue();
        revenue.setIdRevenue(id);

        when(revenueRepository.findById(id)).thenReturn(Optional.of(revenue));
        when(revenueMapper.toDTO(revenue)).thenReturn(revenueDto);

        RevenueDto result = revenueService.modifierRevenue(id, revenueDto);

        assertNotNull(result);
        assertEquals(id, result.getIdRevenue());
    }

    @Test
    void modifierRevenue_NonExistingId() {
        Long id = 1L;
        RevenueDto revenueDto = new RevenueDto();
        revenueDto.setIdRevenue(id);

        when(revenueRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> revenueService.modifierRevenue(id, revenueDto));
    }

    @Test
    void supprimerRevenue_ExistingId() {
        Long id = 1L;
        Revenue revenue = new Revenue();

        when(revenueRepository.findById(id)).thenReturn(Optional.of(revenue));

        revenueService.supprimerRevenue(id);

        verify(revenueRepository, times(1)).deleteById(id);
    }

    @Test
    void supprimerRevenue_NonExistingId() {
        Long id = 1L;

        when(revenueRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> revenueService.supprimerRevenue(id));
    }

    @Test
    void rechercherRevenuesParNom_WithNom() {
        String nom = "Test";
        List<Revenue> revenues = new ArrayList<>();
        revenues.add(new Revenue());
        revenues.add(new Revenue());

        when(revenueRepository.findByNomRevenueContainingIgnoreCase(nom)).thenReturn(revenues);
        when(revenueMapper.toDTO(any(Revenue.class))).thenReturn(new RevenueDto());

        List<RevenueDto> result = revenueService.rechercherRevenuesParNom(nom);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void rechercherRevenuesParNom_WithoutNom() {
        List<Revenue> revenues = new ArrayList<>();
        revenues.add(new Revenue());
        revenues.add(new Revenue());

        when(revenueRepository.findAll()).thenReturn(revenues);
        when(revenueMapper.toDTO(any(Revenue.class))).thenReturn(new RevenueDto());

        List<RevenueDto> result = revenueService.rechercherRevenuesParNom(null);

        assertNotNull(result);
        assertEquals(2, result.size());
    }



    @Test
    void totalRevenuesParMois_EmptyList() {
        when(revenueRepository.findDepensesByCurrentMonth()).thenReturn(new ArrayList<>());

        Double result = revenueService.totalRevenuesParMois();

        assertEquals(0.0, result);
    }

    @Test
    void totalRevenuesParMois_Exception() {
        when(revenueRepository.findDepensesByCurrentMonth()).thenThrow(new RuntimeException("Database connection error"));

        assertThrows(CustomException.class, () -> revenueService.totalRevenuesParMois());
    }
}
