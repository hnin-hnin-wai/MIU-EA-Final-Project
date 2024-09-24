package edu.miu.cs.cs544.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs.cs544.domain.Location;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends BaseRepository<Location, Integer> {
}
