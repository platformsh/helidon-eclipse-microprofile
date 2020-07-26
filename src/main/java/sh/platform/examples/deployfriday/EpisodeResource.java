package sh.platform.examples.deployfriday;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.logging.Logger;

@Path("episodes")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EpisodeResource {

    private static final Logger LOGGER = Logger.getLogger(EpisodeResource.class.getName());

    @Inject
    private EpisodeRepository repository;

    @GET
    @Operation(summary = "Get all episodes", description = "Returns all available episodes")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @APIResponse(responseCode = "200", description = "The episodes")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    @APIResponse(description = "The episodes",
            responseCode = "200",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = Episodes.class,
                            readOnly = true,
                            description = "the episodes",
                            required = true,
                            name = "Episodes")))
    public Episodes getAll() {
        return new Episodes(repository.findAll());
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Find an episode by id", description = "Find an episode by id")
    @APIResponse(responseCode = "200", description = "The episodes")
    @APIResponse(responseCode = "404", description = "When the id does not exist")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    @APIResponse(description = "The episode",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = Episode.class)))
    public Episode findById(@Parameter(description = "The Episode ID", required = true,
            example = "1", schema = @Schema(type = SchemaType.INTEGER)) @PathParam("id") Long id) {

        LOGGER.info("Finds episode by id: " + id);
        final Optional<Episode> episode = repository.findById(id);
        return episode.orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }


    @POST
    @Operation(summary = "Insert an Episode", description = "Insert an Episode")
    @APIResponse(responseCode = "201", description = "When creates an episode")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    public void insert(@RequestBody(description = "Create a new Episode.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Episode.class))) Episode episode) {
        LOGGER.info("Episode: " + episode);
        repository.save(episode);
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete an episode by ID", description = "Delete an episode by ID")
    @APIResponse(responseCode = "200", description = "When deletes the episode")
    @APIResponse(responseCode = "404", description = "When the id does not exist")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    @APIResponse(description = "The episode",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = Episode.class)))
    public void delete(@Parameter(description = "The Episode ID", required = true,
            example = "1", schema = @Schema(type = SchemaType.INTEGER))
                       @PathParam("id") Long id) {
        LOGGER.info("Deletes episode by id: " + id);
        repository.deleteById(id);
    }
}
