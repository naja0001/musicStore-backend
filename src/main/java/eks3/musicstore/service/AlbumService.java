package eks3.musicstore.service;

import eks3.musicstore.dto.AlbumDTO;
import eks3.musicstore.entity.Album;
import eks3.musicstore.enums.Genre;
import eks3.musicstore.mapper.AlbumMapper;
import eks3.musicstore.repository.AlbumRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    // Retrieve all albums and convert to DTOs
    public List<AlbumDTO> getAllAlbums() {
        return albumRepository.findAll().stream()
                .map(AlbumDTO::new)
                .collect(Collectors.toList());
    }

    // Get an album by ID
    public AlbumDTO getAlbumById(Long id) {
        Album album = albumRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));
        return AlbumMapper.mapToAlbumDto(album);
    }


    public AlbumDTO addAlbum(AlbumDTO request) {
        // Ensures the ID is not provided for a new album
        if (request.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot provide the ID for a new album");
        }

        // Checks if the genre is valid
        if (request.getGenre() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Genre must be provided");
        }

        // Creates a new Album entity and maps the data from the DTO
        Album newAlbum = new Album();
        newAlbum.setTitle(request.getTitle());
        newAlbum.setArtist(request.getArtist());
        newAlbum.setGenre(request.getGenre());
        newAlbum.setAvailability(request.getAvailability());

        // Save the album to the repository
        albumRepository.save(newAlbum);

        // Returns the saved album as a DTO
        return new AlbumDTO(newAlbum);
    }

    private void updateAlbum(Album original, AlbumDTO updatedAlbumDTO) {
        original.setTitle(updatedAlbumDTO.getTitle());
        original.setArtist(updatedAlbumDTO.getArtist());
        original.setGenre(updatedAlbumDTO.getGenre());
        original.setAvailability(updatedAlbumDTO.getAvailability());
    }

    public AlbumDTO editAlbum(AlbumDTO request, Long id) {
        // Ensures the provided ID in the request matches the path ID
        if (!request.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot change the ID of an existing album");
        }

        // Validates genre using a utility method
        if (!isValidGenre(request.getGenre())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only existing genres are allowed");
        }

        // Finds the album to edit
        Album albumToEdit = albumRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));

        // Updates the album fields
        updateAlbum(albumToEdit, request);

        // Saves the updated album
        albumRepository.save(albumToEdit);

        // Returns the updated album as a DTO
        return new AlbumDTO(albumToEdit);
    }

    //utility method isValidGenre
    private boolean isValidGenre(Genre genre) {
        // Ensures genre is not null and exists in the Genre enum
        if (genre == null) {
            return false;
        }
        for (Genre validGenre : Genre.values()) {
            if (validGenre.equals(genre)) {
                return true;
            }
        }
        return false;
    }

    public ResponseEntity<Void> deleteAlbum(Long id) {
        // Finds the album by ID, throws exception if not found
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));

        // Deletes the album
        albumRepository.delete(album);

        // Returns a response indicating successful deletion
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
