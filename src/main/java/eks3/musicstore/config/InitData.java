package eks3.musicstore.config;

import eks3.musicstore.dto.AlbumDTO;
import eks3.musicstore.entity.Album;
import eks3.musicstore.enums.Genre;
import eks3.musicstore.mapper.AlbumMapper;
import eks3.musicstore.repository.AlbumRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InitData implements ApplicationRunner {

    private final AlbumRepository albumRepository;

    public InitData(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("Creating initial album data:");
        createAndSaveAlbums();
        System.out.println("Initial album data loaded successfully.");
    }

    private void createAndSaveAlbums() {
        // Step 1: Create DTOs
        List<AlbumDTO> albumDTOs = List.of(
                new AlbumDTO(1L, "Thriller", "Michael Jackson", Genre.POP, true),
                new AlbumDTO(2L, "Back in Black", "AC/DC", Genre.ROCK, true),
                new AlbumDTO(3L, "The Dark Side of the Moon", "Pink Floyd", Genre.ROCK, false),
                new AlbumDTO(4L, "Abbey Road", "The Beatles", Genre.ROCK, true),
                new AlbumDTO(5L, "Rumours", "Fleetwood Mac", Genre.ROCK, false),
                new AlbumDTO(6L, "21", "Adele", Genre.POP, true),
                new AlbumDTO(7L, "Kind of Blue", "Miles Davis", Genre.JAZZ, true),
                new AlbumDTO(8L, "Led Zeppelin IV", "Led Zeppelin", Genre.ROCK, true),
                new AlbumDTO(9L, "Nevermind", "Nirvana", Genre.GRUNGE, true),
                new AlbumDTO(10L, "The Wall", "Pink Floyd", Genre.ROCK, false)
        );

        // Step 2: Map DTOs to Entities using the Mapper
        List<Album> albums = albumDTOs.stream()
                .map(AlbumMapper::mapToAlbum)
                .collect(Collectors.toList());

        // Step 3: Save to Database
        albumRepository.saveAll(albums);
    }
}
