package eks3.musicstore.api;

import eks3.musicstore.service.AlbumCustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import eks3.musicstore.dto.AlbumCustomerDTO;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class AlbumCustomerController {

    private final AlbumCustomerService albumCustomerService;

    public AlbumCustomerController(AlbumCustomerService albumCustomerService) {
        this.albumCustomerService = albumCustomerService;
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<AlbumCustomerDTO>> getReservationsForCustomerById(@PathVariable Long id) {
        List<AlbumCustomerDTO> reservations = albumCustomerService.getReservationsForCustomerById(id);
        return ResponseEntity.ok(reservations);
    }

}
