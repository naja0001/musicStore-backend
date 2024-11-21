package eks3.musicstore.dto;

import eks3.musicstore.entity.AlbumCustomer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AlbumCustomerDTO {
    private Long id;
    private String albumTitle;
    private String customerEmail;
    private LocalDate reservationDate;
    private boolean isActive;

    public AlbumCustomerDTO(AlbumCustomer albumCustomer) {
        this.id = albumCustomer.getId();
        this.albumTitle = albumCustomer.getAlbum().getTitle();
        this.customerEmail = albumCustomer.getCustomer().getEmail();
        this.reservationDate = albumCustomer.getReservationDate();
        this.isActive = albumCustomer.isActive();
    }


    public AlbumCustomerDTO(Long id, String title, String email, LocalDate reservationDate, boolean active) {
        this.id = id;
        this.albumTitle = title;
        this.customerEmail = email;
        this.reservationDate = reservationDate;
        this.isActive = active;
    }
}
