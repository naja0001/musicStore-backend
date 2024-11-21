package eks3.musicstore.api;

import eks3.musicstore.entity.Album;
import eks3.musicstore.entity.Store;
import eks3.musicstore.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/{storeId}/{albumId}")
    public ResponseEntity<Store> assignAlbumToStore(@PathVariable Long storeId, @PathVariable Long albumId) {
        Store store = storeService.assignAlbumToStore(storeId, albumId);
        return ResponseEntity.ok(store);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreDetails(@PathVariable Long id) {
        Store store = storeService.getStoreDetails(id);
        return ResponseEntity.ok(store);
    }


}
