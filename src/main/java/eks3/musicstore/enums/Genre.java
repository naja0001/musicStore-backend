package eks3.musicstore.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Genre {
    ROCK,
    POP,
    JAZZ,
    CLASSICAL,
    GRUNGE;

    @Override
    @JsonValue
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
