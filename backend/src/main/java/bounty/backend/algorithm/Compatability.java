package bounty.backend.algorithm;

import bounty.backend.model.*;

public class Compatability {
  private static int speed_const = 1;
  private static int draw_const = 2;
  private static int disc_const = 1;
  private static int better_class_const = 30;

  public static double compatability(Hunter hunter, Bounty bounty) {
    double compatability = 0;
    
    compatability += (hunter.speed() - bounty.speed()) * speed_const;
    compatability += (hunter.draw_speed() - bounty.draw_speed()) * draw_const;
    compatability += (hunter.discernment() - bounty.slipperiness()) * disc_const;

    if (hunter.type() == HunterClass.Gunslinger && bounty.crime() == Crime.Bandit){
      compatability += better_class_const;
    } else if (hunter.type() == HunterClass.Sniper && bounty.crime() == Crime.Murderer){
      compatability += better_class_const;
    } else if (hunter.type() == HunterClass.Cowboy && bounty.crime() == Crime.Rustler){
      compatability += better_class_const;
    } else if (hunter.type() == HunterClass.Trickster && bounty.crime() == Crime.Hustler){
      compatability += better_class_const;
    } else if (hunter.type() == HunterClass.Sheriff && bounty.crime() == Crime.Vandal){
      compatability += better_class_const;
    } else {
      compatability -= 15;
    }
    
    if (hunter.type() == HunterClass.Gunslinger && bounty.crime() == Crime.Hustler){
      compatability -= better_class_const;
    } else if (hunter.type() == HunterClass.Sniper && bounty.crime() == Crime.Rustler){
      compatability -= better_class_const;
    } else if (hunter.type() == HunterClass.Cowboy && bounty.crime() == Crime.Vandal){
      compatability -= better_class_const;
    } else if (hunter.type() == HunterClass.Trickster && bounty.crime() == Crime.Murderer){
      compatability -= better_class_const;
    } else if (hunter.type() == HunterClass.Sheriff && bounty.crime() == Crime.Bandit){
      compatability -= better_class_const;
    }

    // Adjust the final compatibility score based on the bounty condition
    if (bounty.bounty_condition() == Condition.alive) {
      compatability *= 0.5;
    } else if (bounty.bounty_condition() == Condition.dead) {
      compatability *= 2.0;
    } else if (bounty.bounty_condition() == Condition.dead_or_alive) {
      compatability *= 1.0;
    }

    return compatability;
  }
}