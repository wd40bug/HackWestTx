package bounty.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import bounty.backend.model.Hunter;

public interface HunterRepository extends MongoRepository<Hunter, String> {

  @Query("{_id:'?0'}")
  Hunter get_by_id(String id);

  @Query("{name: '?0'}")
  Hunter get_by_name(String name);

//   @Query("{'danger': {$lt: '?0'}}")
//   List<Bounty> get_lt_danger(int danger);
//   public long count();

}
