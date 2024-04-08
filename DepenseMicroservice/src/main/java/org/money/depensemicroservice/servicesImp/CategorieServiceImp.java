package org.money.depensemicroservice.servicesImp;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.money.depensemicroservice.dtos.CategorieDto;
import org.money.depensemicroservice.entities.Categorie;
import org.money.depensemicroservice.exceptions.CustomException;
import org.money.depensemicroservice.mappers.CategorieMapper;
import org.money.depensemicroservice.repositories.CategorieRepository;
import org.money.depensemicroservice.services.CategorieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategorieServiceImp implements CategorieService {

    private final CategorieMapper categorieMapper;
    private final CategorieRepository categorieRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CategorieServiceImp.class);

    @Override
    public CategorieDto modifierCategorie(Long id, CategorieDto categorieDTO) {
        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.warn("La catégorie avec l'ID {} est introuvable.", id);
                    return new EntityNotFoundException();
                });

        categorie.setNom(categorieDTO.getNom());
        categorie.setIcone(categorieDTO.getIcone());


        return categorieMapper.toDTO(categorieRepository.save(categorie));
    }
    @Override
    public List<CategorieDto> obtenirCategories() {
        try {
            List<Categorie> categories = categorieRepository.findAll();
            return categories.stream()
                    .map(categorieMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la récupération des catégories : {}", e.getMessage());
            throw new CustomException("Erreur lors de la récupération des catégories", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CategorieDto obtenirCategorieParId(Long id) {
        try {
            Categorie categorie = categorieRepository.findById(id)
                    .orElseThrow(() -> {
                        LOGGER.warn("La catégorie avec l'ID {} est introuvable.", id);
                        return new EntityNotFoundException();
                    });
            return categorieMapper.toDTO(categorie);
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la récupération de la catégorie avec l'ID {} : {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la récupération de la catégorie par ID", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CategorieDto ajouterCategorie(CategorieDto categorieDTO) {
        try {
            Categorie categorie = categorieMapper.toEntity(categorieDTO);
            categorie = categorieRepository.save(categorie);
            return categorieMapper.toDTO(categorie);
        } catch (Exception e) {
            LOGGER.error("Erreur lors de l'ajout de la catégorie : {}", e.getMessage());
            throw new CustomException("Erreur lors de l'ajout de la catégorie", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void supprimerCategorie(Long id) {

            Optional<Categorie> categorieOptional = categorieRepository.findById(id);
            if (categorieOptional.isPresent()) {
                categorieRepository.delete(categorieOptional.get());
            } else {
                LOGGER.warn("La catégorie avec l'ID {} est introuvable.", id);
                throw new CustomException("La catégorie avec l'ID " + id + " est introuvable.", HttpStatus.NOT_FOUND);
            }

    }

}
