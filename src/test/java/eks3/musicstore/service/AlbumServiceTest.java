package eks3.musicstore.service;

import eks3.musicstore.dto.AlbumDTO;
import eks3.musicstore.entity.Album;
import eks3.musicstore.enums.Genre;
import eks3.musicstore.repository.AlbumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlbumServiceTest {

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private AlbumService albumService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAlbumById_ShouldThrowException_WhenAlbumDoesNotExist() {
        // Arrange
        Long albumId = 99L;
        when(albumRepository.findById(albumId)).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> albumService.getAlbumById(albumId));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void addAlbum_ShouldThrowException_WhenIdIsProvided() {
        // Arrange
        AlbumDTO request = new AlbumDTO(1L, "Thriller", "Michael Jackson", Genre.POP, true);

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> albumService.addAlbum(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("You cannot provide the ID for a new album", exception.getReason());
    }

    @Test
    void editAlbum_ShouldThrowException_WhenAlbumNotFound() {
        // Arrange
        Long albumId = 99L;
        AlbumDTO request = new AlbumDTO(albumId, "Unknown Album", "Unknown Artist", Genre.POP, true);
        when(albumRepository.findById(albumId)).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> albumService.editAlbum(request, albumId));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Album not found", exception.getReason());
    }

    // DELETE ALBUM TESTS
    @Test
    void deleteAlbum_ShouldDeleteAlbum_WhenAlbumExists() {
        // Arrange
        Long albumId = 1L;
        Album existingAlbum = new Album(albumId, "Thriller", "Michael Jackson", Genre.POP, true);

        when(albumRepository.findById(albumId)).thenReturn(Optional.of(existingAlbum));
        doNothing().when(albumRepository).delete(existingAlbum);

        // Act
        albumService.deleteAlbum(albumId);

        // Assert
        verify(albumRepository, times(1)).findById(albumId);
        verify(albumRepository, times(1)).delete(existingAlbum);
    }

    @Test
    void deleteAlbum_ShouldThrowException_WhenAlbumNotFound() {
        // Arrange
        Long albumId = 99L;
        when(albumRepository.findById(albumId)).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> albumService.deleteAlbum(albumId));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Album not found", exception.getReason());
    }
}




