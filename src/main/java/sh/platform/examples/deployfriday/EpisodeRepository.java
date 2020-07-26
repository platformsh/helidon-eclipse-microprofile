package sh.platform.examples.deployfriday;

import jakarta.nosql.mapping.Repository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface EpisodeRepository extends Repository<Episode, Long> {

    List<Episode> findAll();
}
