package au.com.wytn.centus.domains;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.Optional;

public interface Archiveable<T extends Model> {

    LocalDateTime getArchivedAt();
    T setArchivedAt(LocalDateTime archivedAt);

    @Transient
    default Boolean isArchived() {
        return Optional.ofNullable(getArchivedAt()).isPresent();
    }

    @Transient
    default T archive() {
        return setArchivedAt(LocalDateTime.now());
    }

}
