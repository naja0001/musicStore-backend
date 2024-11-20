package eks3.musicstore.controller;

import eks3.musicstore.dto.AlbumDTO;
import eks3.musicstore.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    // Get all Albums
    @GetMapping
    public List<AlbumDTO> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    // Get Album by id
    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable Long id) {
        AlbumDTO album = albumService.getAlbumById(id);
        return ResponseEntity.ok(album);
    }

    //Add Album
    @PostMapping
    public AlbumDTO addAlbum(@RequestBody AlbumDTO request) {
        return albumService.addAlbum(request);
    }

    //Edit Album
    @PutMapping(path = "/{id}")
    public AlbumDTO editAlbum(@RequestBody AlbumDTO request, @PathVariable Long id) {
        return albumService.editAlbum(request, id);
    }

    //Delete Album
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        return albumService.deleteAlbum(id);
    }

}
