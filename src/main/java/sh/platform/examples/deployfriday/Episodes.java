package sh.platform.examples.deployfriday;


import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Schema(name = "Episodes", description = "The collection of episodes")
public class Episodes {

    @Schema(required = true)
    private List<Episode> episodes;

    public Episodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
}
