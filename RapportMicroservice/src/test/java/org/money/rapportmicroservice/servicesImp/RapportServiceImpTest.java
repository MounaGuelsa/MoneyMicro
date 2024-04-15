package org.money.rapportmicroservice.servicesImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.money.rapportmicroservice.Depense.DepenseClient;
import org.money.rapportmicroservice.Revenue.RevenueClient;
import org.money.rapportmicroservice.dtos.RapportDto;
import org.money.rapportmicroservice.entities.Rapport;
import org.money.rapportmicroservice.mappers.RapportMapper;
import org.money.rapportmicroservice.repositories.RapportRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RapportServiceImpTest {
    @Mock
    private RapportRepository rapportRepository;
    @Mock
    private DepenseClient depenseClient;
    @Mock
    private RevenueClient revenueClient;

    @Mock
    private RapportMapper rapportMapper;

    @InjectMocks
    private RapportServiceImp rapportService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void generateRapports() {
        when(depenseClient.totalDepensesParMois()).thenReturn(100.0);
        when(revenueClient.totalRevenuesParMois()).thenReturn(200.0);

        Rapport rapport = new Rapport();
        rapport.setMoisAnnee(LocalDate.now().withDayOfMonth(1));
        rapport.setDepenses(100.0);
        rapport.setRevenus(200.0);
        rapport.setBalance(100.0);

        rapportService.generateRapports();

        verify(rapportRepository, times(1)).save(rapport);
    }
    @Test
    void obtenirRapports() {
        List<Rapport> rapports = new ArrayList<>();
        rapports.add(new Rapport());
        rapports.add(new Rapport());

        when(rapportRepository.findAll()).thenReturn(rapports);
        when(rapportMapper.toDTO(any(Rapport.class))).thenReturn(new RapportDto());

        List<RapportDto> result = rapportService.obtenirRapports();

        assertEquals(2, result.size());
    }

    @Test
    void obtenirRapportsParMois() {
        LocalDate dateDebut = LocalDate.of(2024, 4, 1);
        LocalDate dateFin = dateDebut.plusMonths(1).minusDays(1);

        List<Rapport> rapports = new ArrayList<>();
        rapports.add(new Rapport());
        rapports.add(new Rapport());

        when(rapportRepository.findByMoisAnneeBetween(dateDebut, dateFin)).thenReturn(rapports);
        when(rapportMapper.toDTO(any(Rapport.class))).thenReturn(new RapportDto());

        List<RapportDto> result = rapportService.obtenirRapportsParMois(4, 2024);

        assertEquals(2, result.size());
    }

    @Test
    void obtenirListeRapports() {
        List<Rapport> rapports = new ArrayList<>();
        rapports.add(new Rapport());
        rapports.add(new Rapport());

        when(rapportRepository.findAll()).thenReturn(rapports);
        when(rapportMapper.toDTO(any(Rapport.class))).thenReturn(new RapportDto());

        List<RapportDto> result = rapportService.obtenirListeRapports();

        assertEquals(2, result.size());
    }

    @Test
    void supprimerRapport() {
        Long id = 1L;

        rapportService.supprimerRapport(id);

        verify(rapportRepository, times(1)).deleteById(id);
    }
}
