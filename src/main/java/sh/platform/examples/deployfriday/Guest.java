package sh.platform.examples.deployfriday;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbVisibility;
import java.util.Objects;

@Schema(name = "Guest", description = "The entity that represents Guest")
@Entity
@JsonbVisibility(FieldVisibility.class)
public class Guest {

    @Schema(required = true, description = "The guest name", example = "Ada Lovelace")
    @Column
    private String name;

    @Schema(required = true, description = "The guest twitter handle", example = "adalovelace")
    @Column
    private String twitter;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Guest guest = (Guest) o;
        return Objects.equals(name, guest.name) &&
                Objects.equals(twitter, guest.twitter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, twitter);
    }

    @Override
    public String toString() {
        return "Guest{" +
                "name='" + name + '\'' +
                ", twitter='" + twitter + '\'' +
                '}';
    }
}
