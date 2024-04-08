package org.money.depensemicroservice.services;

import org.money.depensemicroservice.dtos.FactureDto;
import org.money.depensemicroservice.entities.Facture;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FactureService {
    public List<FactureDto> obtenirFactures();
    public FactureDto obtenirFactureParId(Long id);
    public FactureDto ajouterFacture(FactureDto factureDTO);
    public FactureDto modifierFacture(Long id, FactureDto factureDTO);
    public void supprimerFacture(Long id);

    String uploadFile(File file, String fileName) throws IOException;

    File convertToFile(MultipartFile multipartFile, String fileName) throws IOException;

    String getExtension(String fileName);

    FactureDto importerFacture(MultipartFile multipartFile, FactureDto factureDto);
}