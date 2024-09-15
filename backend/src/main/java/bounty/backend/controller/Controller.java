package bounty.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.PropertySource.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bounty.backend.algorithm.Compatability;
import bounty.backend.model.Bounty;
import bounty.backend.model.Hunter;
import bounty.backend.repository.BountyRepository;

@RestController
public class Controller {
  @Autowired
  BountyRepository repository;

  @GetMapping("/bounty")
  public Optional<Bounty> bounty(@RequestParam(value = "id") String id) {
    var item = repository.findById(id);
    if (!item.isPresent()) {
      System.out.println("Not present");
    }
    return item;
  }

  @GetMapping("/name")
  public Optional<Bounty> bounty_name(@RequestParam(value = "name") String name) {
    System.out.println(name);
    var item = repository.get_by_name(name);
    System.out.println(item);
    return Optional.ofNullable(item);
  }

  @CrossOrigin(origins = "http://localhost:8081")
  @GetMapping("/test")
  public String test() {
    return "Hello Frontend";
  }


  // @RequestMapping(value = "/testPost", method = RequestMethod.POST)
  // public Bounty testPost(@RequestBody Bounty testBounty){
  // System.out.println("HERE");
  // repository.save(testBounty);
  // return null;

  @PostMapping(value = "/dummy_string")
  public void dummy_string(@RequestBody() String hunter) {
    System.out.println(hunter);
  }

  @RequestMapping(value = "/dummy_data", method = RequestMethod.POST)
  public List<Bounty> dummy_data(@RequestBody() Hunter hunter) {
    System.out.println(hunter);
    var bounties = repository.get_lt_danger(hunter.skill() * 10);
    System.out.println(bounties);
    return bounties;
  }

  @GetMapping("/feed")
  public List<Bounty> feed(@RequestParam(name = "hunter") Hunter hunter, @RequestParam(name = "max") int max) {
    List<Bounty> bounties = repository.get_lt_danger(hunter.skill() * 10);


    var sorted = bounties.stream()
        .sorted(java.util.Comparator.comparing(val -> Compatability.compatability(hunter, val)))
        .collect(Collectors.toList());

    return sorted;
  }

}
