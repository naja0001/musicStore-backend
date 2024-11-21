package eks3.musicstore.service;

import eks3.musicstore.dto.AlbumCustomerDTO;
import eks3.musicstore.entity.Album;
import eks3.musicstore.entity.AlbumCustomer;
import eks3.musicstore.entity.Customer;
import eks3.musicstore.repository.AlbumCustomerRepository;
import eks3.musicstore.repository.AlbumRepository;
import eks3.musicstore.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AlbumCustomerService {

    private final AlbumRepository albumRepository;
    private final CustomerRepository customerRepository;
    private final AlbumCustomerRepository albumCustomerRepository;

    public AlbumCustomerService(AlbumRepository albumRepository, CustomerRepository customerRepository, AlbumCustomerRepository albumCustomerRepository) {
        this.albumRepository = albumRepository;
        this.customerRepository = customerRepository;
        this.albumCustomerRepository = albumCustomerRepository;
    }

    public List<AlbumCustomerDTO> getReservationsForCustomerById(Long customerId) {
        return albumCustomerRepository.findByCustomer_Id(customerId)
                .stream()
                .map(AlbumCustomerDTO::new)
                .collect(Collectors.toList());
    }



}
