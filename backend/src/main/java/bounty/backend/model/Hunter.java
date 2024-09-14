package bounty.backend.model;

import org.springframework.data.annotation.Id;

public record Hunter(@Id String id, String name, String pic, int skill, Location location, double speed,
    double draw_speed, int discernment, HunterClass type) {
}
