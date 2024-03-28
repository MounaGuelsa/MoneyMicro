package org.money.rapportmicroservice.services;

import org.money.rapportmicroservice.dtos.RapportDto;
import org.money.rapportmicroservice.entities.Rapport;

import java.util.List;
import java.util.Map;

public interface RapporService {

    Map<String, Double> genererRapportMensuel(String titre, String moisAnnee);
    List<RapportDto> getAllRapports();
}
