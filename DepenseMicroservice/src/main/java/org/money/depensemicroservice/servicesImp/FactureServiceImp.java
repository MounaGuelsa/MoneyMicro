package org.money.depensemicroservice.servicesImp;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.RequiredArgsConstructor;
import org.money.depensemicroservice.dtos.FactureDto;
import org.money.depensemicroservice.entities.Facture;
import org.money.depensemicroservice.exceptions.CustomException;
import org.money.depensemicroservice.mappers.FactureMapper;
import org.money.depensemicroservice.repositories.FactureRepository;
import org.money.depensemicroservice.services.FactureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FactureServiceImp implements FactureService {

    private final FactureMapper factureMapper;
    private final FactureRepository factureRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(FactureServiceImp.class);

    @Override
    public List<FactureDto> obtenirFactures() {
        try {
            List<Facture> factures = factureRepository.findAll();
            return factures.stream()
                    .map(factureMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la récupération des factures : {}", e.getMessage());
            throw new CustomException("Erreur lors de la récupération des factures", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public FactureDto obtenirFactureParId(Long id) {
        try {
            Optional<Facture> factureOptional = factureRepository.findById(id);
            return factureOptional.map(factureMapper::toDTO).orElse(null);
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la récupération de la facture avec l'ID {} : {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la récupération de la facture par ID", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public FactureDto modifierFacture(Long id, FactureDto factureDTO) {
        try {
            Optional<Facture> factureOptional = factureRepository.findById(id);
            if (factureOptional.isPresent()) {
                Facture facture = factureOptional.get();
                facture.setUrl(facture.getUrl());
                facture.setNomFacture(factureDTO.getNomFacture());
                facture = factureRepository.save(facture);
                return factureMapper.toDTO(facture);
            } else {
                LOGGER.warn("La facture avec l'ID {} est introuvable.", id);
                throw new CustomException("La facture avec l'ID " + id + " est introuvable.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la modification de la facture avec l'ID {} : {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la modification de la facture", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void supprimerFacture(Long id) {
        try {
            factureRepository.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la suppression de la facture avec l'ID {} : {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la suppression de la facture", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   @Override
   public String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("facture-e8737.appspot.com", fileName); // Replace with your bucker name
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        InputStream inputStream = FactureService.class.getClassLoader().getResourceAsStream("facture-e8737-firebase-adminsdk-jww5j-065f90bcee.json"); // change the file name with your one
        Credentials credentials = GoogleCredentials.fromStream(inputStream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/<facture-e8737.appspot.com>/o/%s?alt=media";
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }
@Override
    public File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }
    @Override
    public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

@Override
    public FactureDto importerFacture(MultipartFile multipartFile, FactureDto factureDto) {
        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  // to generated random string values for file name.

            File file = this.convertToFile(multipartFile, fileName);
            String URL = this.uploadFile(file, fileName);
            Facture facture = new Facture();
           facture.setNumeroFacture(factureDto.getNumeroFacture());
           facture.setNomFacture(factureDto.getNomFacture());
            facture.setUrl(URL);

           factureRepository.save(facture);
            file.delete();
            return factureMapper.toDTO(facture);
        }catch (Exception e) {
            LOGGER.error("Erreur lors de l'ajout de la facture : {}", e.getMessage());
            throw new CustomException("Erreur lors de l'ajout de la facture", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
