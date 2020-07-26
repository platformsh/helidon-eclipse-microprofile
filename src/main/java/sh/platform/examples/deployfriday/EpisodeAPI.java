package sh.platform.examples.deployfriday;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

public interface EpisodeAPI {


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
    Episodes getAll();

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
    Episode findById(@Parameter(description = "The Episode ID", required = true,
            example = "1", schema = @Schema(type = SchemaType.INTEGER)) @PathParam("id") Long id);
    @POST
    @Operation(summary = "Insert an Episode", description = "Insert an Episode")
    @APIResponse(responseCode = "201", description = "When creates an episode")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    void insert(  @RequestBody(description = "Create a new Episode.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Episode.class))) Episode episode);


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
    void delete(@Parameter(description = "The Episode ID", required = true,
            example = "1", schema = @Schema(type = SchemaType.INTEGER))
                       @PathParam("id") Long id);
}
