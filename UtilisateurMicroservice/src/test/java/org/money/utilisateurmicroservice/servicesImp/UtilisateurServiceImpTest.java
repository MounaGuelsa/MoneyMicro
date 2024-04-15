package org.money.utilisateurmicroservice.servicesImp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.money.utilisateurmicroservice.dtos.UtilisateurDto;
import org.money.utilisateurmicroservice.entities.Role;
import org.money.utilisateurmicroservice.entities.Utilisateur;
import org.money.utilisateurmicroservice.exceptions.UtilisateurNotFound;
import org.money.utilisateurmicroservice.mappers.UtilisateurMapper;
import org.money.utilisateurmicroservice.repositories.UtilisateurRepository;
import org.money.utilisateurmicroservice.services.KeycloakService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UtilisateurServiceImpTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private UtilisateurMapper utilisateurMapper;

    @Mock
    private KeycloakService keycloakService;

    @InjectMocks
    private UtilisateurServiceImp utilisateurService;

    private UtilisateurDto utilisateurDto;

    @BeforeEach
    void setUp() {
        utilisateurDto = new UtilisateurDto();
        utilisateurDto.setNom_utilisateur("utilisateurTest");
        utilisateurDto.setNom("NomTest");
        utilisateurDto.setPrenom("PrenomTest");
        utilisateurDto.setEmail("test@example.com");
        utilisateurDto.setPassword("password");
        utilisateurDto.setRole(Role.USER);
    }

    @Test
    void creerUtilisateur_WhenValidUtilisateurDtoProvided_ReturnsUtilisateurDto() {
        // Arrange
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurMapper.toEntity(utilisateurDto)).thenReturn(utilisateur);
        when(keycloakService.createUserInKeycloak(any())).thenReturn("keycloakId");
        when(utilisateurRepository.save(utilisateur)).thenReturn(utilisateur);

        // Act
        UtilisateurDto createdUtilisateurDto = utilisateurService.creerUtilisateur(utilisateurDto);

        // Assert
        assertNotNull(createdUtilisateurDto);
        assertEquals("keycloakId", createdUtilisateurDto.getIdKeycloak());
    }



    @Test
    void SupprimerUtilisateur_WhenUtilisateurExists_DeletesUtilisateur() {
        // Arrange
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId_Utilisateur(1L);
        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(utilisateur));

        // Act
        utilisateurService.SupprimerUtilisateur(1L);

        // Assert
        verify(utilisateurRepository, times(1)).delete(utilisateur);
    }

    @Test
    void SupprimerUtilisateur_WhenUtilisateurDoesNotExist_ThrowsUtilisateurNotFound() {
        // Arrange
        when(utilisateurRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UtilisateurNotFound.class, () -> utilisateurService.SupprimerUtilisateur(1L));
    }
}
