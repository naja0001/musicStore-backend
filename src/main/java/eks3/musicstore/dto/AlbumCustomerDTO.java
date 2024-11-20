package eks3.musicstore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import eks3.musicstore.entity.AlbumCustomer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlbumCustomerDTO {
    private Long id;
    private String albumTitle;
    private String customerName;
    private LocalDate reservationDate;
    private String status;
}
