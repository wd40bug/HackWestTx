package bounty.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("dummy")
public record Bounty(@Id String id, String name, String pic, Location last_known, double reward, double danger,
    String client, String crime) {

}

record Location(double lat, double lon) {
}
