package bounty.backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import bounty.backend.model.Bounty;

public interface BountyRepository extends MongoRepository<Bounty, String> {

  @Query("{_id:'?0'}")
  Bounty get_by_id(String id);

  @Query("{name: '?0'}")
  Bounty get_by_name(String name);

  public long count();

}
