package org.money.rapportmicroservice.servicesImp;

import lombok.RequiredArgsConstructor;
import org.money.rapportmicroservice.dtos.RapportDto;
import org.money.rapportmicroservice.entities.Rapport;
import org.money.rapportmicroservice.exceptions.CustomException;
import org.money.rapportmicroservice.mappers.RapportMapper;
import org.money.rapportmicroservice.repositories.RapportRepository;
import org.money.rapportmicroservice.services.RapporService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RapportServiceImp implements RapporService {
    private RapportMapper rapportMapper;
    private final RapportRepository rapportRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(RapportServiceImp.class);

    @Override
    public Map<String, Double> genererRapportMensuel(String titre, String moisAnnee) {
        return null;
    }

    @Override
    public List<RapportDto> getAllRapports() {
        try {
            return rapportRepository.findAll().stream().map(rapportMapper::toDTO).collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la récupération des projets: {}", e.getMessage());
            throw new CustomException("Erreur lors de la récupération des projets", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
