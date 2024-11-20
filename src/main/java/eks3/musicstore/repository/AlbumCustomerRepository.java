package eks3.musicstore.repository;

import eks3.musicstore.entity.AlbumCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumCustomerRepository extends JpaRepository<AlbumCustomer, Long> {
}
