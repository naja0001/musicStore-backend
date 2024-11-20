package eks3.musicstore.entity;

import eks3.musicstore.enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String artist;
    private Genre genre;
    private Boolean availability;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AlbumCustomer> reservations = new HashSet<>();

    public Album(Long id, String title, String artist, Genre genre, boolean availability) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.availability = availability;
    }
}
