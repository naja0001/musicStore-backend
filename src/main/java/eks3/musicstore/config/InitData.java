package eks3.musicstore.config;

import eks3.musicstore.entity.Album;
import eks3.musicstore.entity.Store;
import eks3.musicstore.enums.Genre;
import eks3.musicstore.repository.AlbumRepository;
import eks3.musicstore.repository.StoreRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitData implements ApplicationRunner {

    private final StoreRepository storeRepository;
    private final AlbumRepository albumRepository;

    public InitData(StoreRepository storeRepository, AlbumRepository albumRepository) {
        this.storeRepository = storeRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("Initializing data...");

        createStores();
        createAlbums();
        assignAlbumsToStores();

        System.out.println("Data initialization completed.");
    }

    private void createStores() {
        List<Store> stores = List.of(
                new Store("Downtown Music Store", "123 Main Street", "Springfield", "11111"),
                new Store("Uptown Records", "456 High Street", "Shelbyville", "22222"),
                new Store("Vinyl Haven", "789 Elm Avenue", "Ogdenville", "33333")
        );
        storeRepository.saveAll(stores);
    }


    private void createAlbums() {
        List<Album> albums = List.of(
                new Album("Thriller", "Michael Jackson", Genre.POP, true),
                new Album("Back in Black", "AC/DC", Genre.ROCK, true),
                new Album("The Dark Side of the Moon", "Pink Floyd", Genre.ROCK, true),
                new Album("Abbey Road", "The Beatles", Genre.ROCK, true),
                new Album("Rumours", "Fleetwood Mac", Genre.ROCK, true)
        );
        albumRepository.saveAll(albums);
    }

    private void assignAlbumsToStores() {
        // Fetches stores
        Store store1 = storeRepository.findById(1L).orElseThrow(() -> new RuntimeException("Store not found"));
        Store store2 = storeRepository.findById(2L).orElseThrow(() -> new RuntimeException("Store not found"));

        // Fetches albums
        Album album1 = albumRepository.findById(1L).orElseThrow(() -> new RuntimeException("Album not found"));
        Album album2 = albumRepository.findById(2L).orElseThrow(() -> new RuntimeException("Album not found"));
        Album album3 = albumRepository.findById(3L).orElseThrow(() -> new RuntimeException("Album not found"));

        // Assigns albums to stores
        album1.setStore(store1);
        album2.setStore(store2);
        album3.setStore(store1);

        // Saves updates
        albumRepository.saveAll(List.of(album1, album2, album3));
    }
}
