package eks3.musicstore.mapper;

import eks3.musicstore.dto.AlbumCustomerDTO;
import eks3.musicstore.entity.Album;
import eks3.musicstore.entity.AlbumCustomer;
import eks3.musicstore.entity.Customer;

public class AlbumCustomerMapper {

    // Map AlbumCustomer entity to AlbumCustomerDTO
    public static AlbumCustomerDTO mapToAlbumCustomerDto(AlbumCustomer albumCustomer) {
        return new AlbumCustomerDTO(
                albumCustomer.getId(),
                albumCustomer.getAlbum().getTitle(),
                albumCustomer.getCustomer().getEmail(),
                albumCustomer.getReservationDate(),
                albumCustomer.isActive()
        );
    }

    // Map AlbumCustomerDTO to AlbumCustomer entity
    public static AlbumCustomer mapToAlbumCustomer(AlbumCustomerDTO albumCustomerDto, Album album, Customer customer) {
        AlbumCustomer albumCustomer = new AlbumCustomer();
        albumCustomer.setAlbum(album);
        albumCustomer.setCustomer(customer);
        albumCustomer.setReservationDate(albumCustomerDto.getReservationDate());
        albumCustomer.setActive(albumCustomerDto.isActive());
        return albumCustomer;
    }
}
