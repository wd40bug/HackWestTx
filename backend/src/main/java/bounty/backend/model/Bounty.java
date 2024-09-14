package bounty.backend.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("bounties")
public record Bounty(@Id String id, String name, String pic, Location last_known, double reward, double danger,
    String client, Crime crime, double speed, double draw_speed, int slipperiness, Condition bounty_condition,
    List<String> styles) {
}
