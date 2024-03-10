package org.money.depensemicroservice.servicesImp;

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
            Optional<Categorie> categorieOptional = categorieRepository.findById(id);
            return categorieOptional.map(categorieMapper::toDTO).orElse(null);
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
    public CategorieDto modifierCategorie(Long id, CategorieDto categorieDTO) {
        try {
            Optional<Categorie> categorieOptional = categorieRepository.findById(id);
            if (categorieOptional.isPresent()) {
                Categorie categorie = categorieOptional.get();
                categorie.setNom(categorieDTO.getNom());
                //categorie.setBudget(categorieDTO.getBudgetDTO());
                return categorieMapper.toDTO(categorieRepository.save(categorie));

            } else {
                LOGGER.warn("La catégorie avec l'ID {} est introuvable.", id);
                throw new CustomException("La catégorie avec l'ID " + id + " est introuvable.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la modification de la catégorie avec l'ID {} : {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la modification de la catégorie", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void supprimerCategorie(Long id) {
        try {
            Optional<Categorie> categorieOptional = categorieRepository.findById(id);
            if (categorieOptional.isPresent()) {
                categorieRepository.delete(categorieOptional.get());
            } else {
                LOGGER.warn("La catégorie avec l'ID {} est introuvable.", id);
                throw new CustomException("La catégorie avec l'ID " + id + " est introuvable.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la suppression de la catégorie avec l'ID {} : {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la suppression de la catégorie", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
