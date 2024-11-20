package eks3.musicstore.mapper;

import eks3.musicstore.dto.StoreDTO;
import eks3.musicstore.entity.Store;

import java.util.stream.Collectors;

public class StoreMapper {

    public static StoreDTO mapToStoreDto(Store store) {
        return new StoreDTO(
                store.getId(),
                store.getName(),
                store.getStreet(),
                store.getCity(),
                store.getZip(),
                store.getAlbums() != null
                        ? store.getAlbums().stream()
                        .map(AlbumMapper::mapToAlbumDto)
                        .collect(Collectors.toList()) // Convert Set to List for DTO
                        : null
        );
    }

    public static Store mapToStore(StoreDTO storeDto) {
        return new Store(
                storeDto.getId(),
                storeDto.getName(),
                storeDto.getStreet(),
                storeDto.getCity(),
                storeDto.getZip(),
                storeDto.getAlbums() != null
                        ? storeDto.getAlbums().stream()
                        .map(AlbumMapper::mapToAlbum)
                        .collect(Collectors.toSet()) // Convert List to Set for Entity
                        : null
        );
    }
}
