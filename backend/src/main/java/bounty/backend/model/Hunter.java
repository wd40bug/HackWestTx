package bounty.backend.model;

import org.springframework.data.annotation.Id;

public record Hunter(@Id String id, String name, String location, String pic, double skill) {

}
