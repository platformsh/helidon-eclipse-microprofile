package sh.platform.examples.deployfriday;

import jakarta.nosql.mapping.Repository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface EpisodeRepository extends Repository<Episode, Long> {
}
