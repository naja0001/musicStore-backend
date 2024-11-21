package eks3.musicstore.dto;

import eks3.musicstore.entity.Album;
import eks3.musicstore.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDTO {
    private Long id;
    private String title;
    private String artist;
    private Genre genre;
    private Boolean availability;
   // private Store store; Store more details

    public AlbumDTO(Album album) {
        this.id = album.getId();
        this.title = album.getTitle();
        this.artist = album.getArtist();
        this.genre = album.getGenre();
        this.availability = album.getAvailability();
    }
}
