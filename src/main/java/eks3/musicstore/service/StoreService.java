package eks3.musicstore.service;

import eks3.musicstore.entity.Album;
import eks3.musicstore.entity.Store;
import eks3.musicstore.repository.AlbumRepository;
import eks3.musicstore.repository.StoreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final AlbumRepository albumRepository;

    public StoreService(StoreRepository storeRepository, AlbumRepository albumRepository) {
        this.storeRepository = storeRepository;
        this.albumRepository = albumRepository;
    }

    public Store assignAlbumToStore(Long storeId, Long albumId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found with ID: " + storeId));

        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found with ID: " + albumId));

        album.setStore(store);
        albumRepository.save(album);

        return store;
    }

    public Store getStoreDetails(Long storeId) {
        return storeRepository.findById(storeId).orElseThrow();
    }

}
