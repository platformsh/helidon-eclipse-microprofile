package sh.platform.examples.deployfriday;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbVisibility;
import java.util.Set;

@Schema(name = "Episode", description = "The entity that represents Episode")
@Entity
@JsonbVisibility(FieldVisibility.class)
public class Episode {

    @Schema(required = true, name = "id", description = "The episode ID", example = "1")
    @Id
    private Long id;

    @Column
    @Schema(required = true, name = "url", description = "The episode URL", example = "https://www.youtube.com/playlist?list=PLn5EpEMtxTCmLsbLgaN3djvEkRdp-YmlE")
    private String url;

    @Schema(required = true, description = "the hosts")
    @Column
    private Set<String> hosts;

    @Schema(required = true, description = "the guests")
    @Column
    private Set<Guest> guests;


    @Override
    public String toString() {
        return "Episode{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", hosts=" + hosts +
                ", guests=" + guests +
                '}';
    }
}
