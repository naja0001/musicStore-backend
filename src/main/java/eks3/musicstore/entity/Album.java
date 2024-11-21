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

    @Enumerated(EnumType.STRING) // Save as a string in the database
    private Genre genre;

    private Boolean availability;

    @ManyToOne
    @JoinColumn(name = "store_id") // Foreign key column for Store
    private Store store;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AlbumCustomer> reservations = new HashSet<>();


    public Album(String title, String artist, Genre genre, Boolean availability) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.availability = availability;
    }

    public Album(Long id, String title, String artist, Genre genre, Boolean availability) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.availability = availability;
    }

    public boolean isAvailability() {
        return availability;
    }
}
