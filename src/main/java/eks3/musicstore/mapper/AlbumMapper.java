package eks3.musicstore.mapper;

import eks3.musicstore.dto.AlbumDTO;
import eks3.musicstore.entity.Album;
import eks3.musicstore.entity.Store;

public class AlbumMapper {

    public static AlbumDTO mapToAlbumDto(Album album) {
        return new AlbumDTO(
                album.getId(),
                album.getTitle(),
                album.getArtist(),
                album.getGenre(),
                album.getAvailability());
    }

    public static Album mapToAlbum(AlbumDTO albumDto) {
        return new Album(
                albumDto.getId(),
                albumDto.getTitle(),
                albumDto.getArtist(),
                albumDto.getGenre(),
                albumDto.getAvailability()
        );
    }
}
