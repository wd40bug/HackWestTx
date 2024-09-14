package bounty.backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import bounty.backend.model.Bounty;
import bounty.backend.repository.BountyRepository;

@SpringBootApplication
@EnableMongoRepositories
public class BackendApplication implements CommandLineRunner {

  @Autowired
  BountyRepository bountyRepo;

  public static void main(String[] args) {
    SpringApplication.run(BackendApplication.class, args);
  }

  public void run(String... args) {
    System.out.println("HERE");
    Bounty item = bountyRepo.get_by_id("66e5c80fd2824c21ce4fca72");
    System.out.println(item);
  }

  public BountyRepository getBountyRepo() {
    return bountyRepo;
  }

  public void setBountyRepo(BountyRepository bountyRepo) {
    this.bountyRepo = bountyRepo;
  }
}
