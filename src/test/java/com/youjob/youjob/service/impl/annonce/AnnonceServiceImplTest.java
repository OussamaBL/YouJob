package com.youjob.youjob.service.impl.annonce;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.Enum.AnnonceStatus;
import com.youjob.youjob.domain.Enum.UserRole;
import com.youjob.youjob.domain.User;
import com.youjob.youjob.exception.NullVarException;
import com.youjob.youjob.exception.annonce.AnnonceNotExistException;
import com.youjob.youjob.exception.annonce.InvalidAnnonceException;
import com.youjob.youjob.exception.auth.UserNotExistException;
import com.youjob.youjob.repository.AnnonceRepository;
import com.youjob.youjob.repository.UserRepository;
import com.youjob.youjob.repository.impl.AnnonceRepositoryImpl;
import com.youjob.youjob.service.AnnonceService;
import com.youjob.youjob.service.impl.AnnonceServiceImpl;
import com.youjob.youjob.web.vm.annonce.SearchDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AnnonceServiceImplTest {

    @Mock
    private AnnonceRepository annonceRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AnnonceServiceImpl annonceServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // test for getting archived annonces history for a user
    @Test
    void testGetHistoryAnnonce_Success() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);
        user.setRole(UserRole.CUSTOMER);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Create a sample Page with one Annonce
        Page<Annonce> pageResult = new PageImpl<>(Arrays.asList(new Annonce()));
        when(annonceRepository.getAllByCreatedByIdAndStatus(eq(userId), eq(AnnonceStatus.ARCHIVED), any(PageRequest.class)))
                .thenReturn(pageResult);

        Page<Annonce> result = annonceServiceImpl.getHistory_Annonce(userId, 0, 10);
        assertNotNull(result);
        verify(userRepository).findById(userId);
    }

    @Test
    void testGetHistoryAnnonce_UserNotFound() {
        UUID userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotExistException.class, () -> annonceServiceImpl.getHistory_Annonce(userId, 0, 10));
    }

    // test for creating an annonce
    @Test
    void testCreateAnnonce_Success() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);
        user.setRole(UserRole.CUSTOMER);
        Annonce annonce = new Annonce();
        annonce.setCreatedBy(user);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(annonceRepository.save(any(Annonce.class))).thenReturn(annonce);

        Annonce createdAnnonce = annonceServiceImpl.CreateAnnonce(annonce);
        assertNotNull(createdAnnonce);
        verify(userRepository).findById(userId);
        verify(annonceRepository).save(annonce);
    }


    @Test
    void testCreateAnnonce_HandymanCannotPost() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);
        user.setRole(UserRole.HANDYMAN);
        Annonce annonce = new Annonce();
        annonce.setCreatedBy(user);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        assertThrows(InvalidAnnonceException.class, () -> annonceServiceImpl.CreateAnnonce(annonce));
    }

    @Test
    void testCreateAnnonce_UserNotFound() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);
        user.setRole(UserRole.CUSTOMER);
        Annonce annonce = new Annonce();
        annonce.setCreatedBy(user);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotExistException.class, () -> annonceServiceImpl.CreateAnnonce(annonce));
    }

    // test for deleting an annonce
    @Test
    void testDeleteAnnonce_Success() {
        UUID annonceId = UUID.randomUUID();
        Annonce annonce = new Annonce();
        annonce.setId(annonceId);
        annonce.setStatus(AnnonceStatus.ACTIVE);

        when(annonceRepository.findById(annonceId)).thenReturn(Optional.of(annonce));
        annonceServiceImpl.DeleteAnnonce(annonceId);
        verify(annonceRepository).delete(annonce);
    }

    @Test
    void testDeleteAnnonce_NotFound() {
        UUID annonceId = UUID.randomUUID();
        when(annonceRepository.findById(annonceId)).thenReturn(Optional.empty());
        assertThrows(AnnonceNotExistException.class, () -> annonceServiceImpl.DeleteAnnonce(annonceId));
    }

    @Test
    void testDeleteAnnonce_NotActive() {
        UUID annonceId = UUID.randomUUID();
        Annonce annonce = new Annonce();
        annonce.setId(annonceId);
        annonce.setStatus(AnnonceStatus.ARCHIVED);  // Not ACTIVE
        when(annonceRepository.findById(annonceId)).thenReturn(Optional.of(annonce));

        annonceServiceImpl.DeleteAnnonce(annonceId);
        verify(annonceRepository, never()).delete(annonce);
    }

    @Test
    void testUpdateAnnonce_AnnonceNotFound() {
        UUID annonceId = UUID.randomUUID();
        Annonce updatedAnnonce = new Annonce();
        updatedAnnonce.setId(annonceId);
        when(annonceRepository.findById(annonceId)).thenReturn(Optional.empty());
        assertThrows(AnnonceNotExistException.class, () -> annonceServiceImpl.updateAnnonce(updatedAnnonce));
    }

    @Test
    void testUpdateAnnonce_ArchivedCannotUpdate() {
        UUID annonceId = UUID.randomUUID();
        Annonce existingAnnonce = new Annonce();
        existingAnnonce.setId(annonceId);
        existingAnnonce.setStatus(AnnonceStatus.ARCHIVED);

        Annonce updatedAnnonce = new Annonce();
        updatedAnnonce.setId(annonceId);

        when(annonceRepository.findById(annonceId)).thenReturn(Optional.of(existingAnnonce));
        assertThrows(InvalidAnnonceException.class, () -> annonceServiceImpl.updateAnnonce(updatedAnnonce));
    }

}
