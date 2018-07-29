package au.com.wytn.centus.domains.core.users;

import au.com.wytn.centus.domains.Activeable;
import au.com.wytn.centus.domains.Archiveable;
import au.com.wytn.centus.domains.Model;
import au.com.wytn.centus.domains.Nameable;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


//@CompoundIndexes({
//        @CompoundIndex(name = "email_age", def = "{'email.id' : 1, 'age': 1}")
//})
@Document(collection = "usersRoles")
public class UserRole implements Model, Activeable, Nameable, Archiveable<UserRole> {

    @Id
    private ObjectId id;

    @Indexed
    private String name;

    @Indexed
    private Boolean active = false;

    @Indexed
    private LocalDateTime archivedAt;

    public ObjectId getId() {
        return id;
    }

    public UserRole setId(ObjectId id) {
        this.id = id;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    public UserRole setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Boolean isActive() {
        return active;
    }

    public UserRole setActive(Boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public LocalDateTime getArchivedAt() {
        return archivedAt;
    }

    @Override
    public UserRole setArchivedAt(LocalDateTime archivedAt) {
        this.archivedAt = archivedAt;
        return this;
    }
}
