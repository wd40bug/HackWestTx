package bounty.backend.algorithm;

import bounty.backend.model.*;



public class Compatability {
  private static int speed_const = 10;
  private static int draw_const = 10;
  private static int disc_const = 10;
  private static int better_class_const = 10;

  public static int compatability(Hunter hunter, Bounty bounty) {
    int compatability = 0;
    
    compatability += (hunter.speed() - bounty.speed()) * speed_const;
    compatability += (hunter.draw_speed() - bounty.draw_speed()) * draw_const;
    compatability += (hunter.discernment() - bounty.slipperiness()) * disc_const;

    if (hunter.type() == HunterClass.Gunslinger && bounty.crime() == Crime.Bandit){
      compatability += better_class_const;
    }else if (hunter.type() == HunterClass.Sniper && bounty.crime() == Crime.Murderer){
      compatability += better_class_const;
    }else if (hunter.type() == HunterClass.Cowboy && bounty.crime() == Crime.Rustler){
      compatability += better_class_const;
    }else if (hunter.type() == HunterClass.Trickster && bounty.crime() == Crime.Hustler){
      compatability += better_class_const;
    }else if (hunter.type() == HunterClass.Sheriff && bounty.crime() == Crime.Vandal){
      compatability += better_class_const;
    }

    return compatability;
  }
}
