package eks3.musicstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class AlbumCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Album album;

    @ManyToOne
    private Customer customer;

    private LocalDate reservationDate;

    private boolean isActive; // To manage cancellations

    public AlbumCustomer(Album album, Customer customer, LocalDate reservationDate) {
        this.album = album;
        this.customer = customer;
        this.reservationDate = reservationDate;
        this.isActive = true;
    }

}
