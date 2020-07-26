package sh.platform.examples.deployfriday;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.logging.Logger;

@Path("episodes")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EpisodeResource implements EpisodeAPI {

    private static final Logger LOGGER = Logger.getLogger(EpisodeResource.class.getName());


    @Override
    public Episodes getAll() {
        return new Episodes(Collections.emptyList());
    }

    @Override
    public Episode findById(Long id) {
        return new Episode();
    }


    @Override
    public void insert(Episode episode) {
        LOGGER.info("Episode: " + episode);
    }

    @Override
    public void delete(Long id) {
    }
}
