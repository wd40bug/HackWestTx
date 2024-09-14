package bounty.backend.algorithm;

import bounty.backend.model.Bounty;
import bounty.backend.model.Hunter;

public class Compatibility {

    public static int compareHunterSkillWithBountyDanger(Hunter hunter, Bounty bounty) {
        // Retrieve skill and danger values
        double skill = hunter.skill();
        double danger = bounty.danger();
        
        // Round both values to the nearest integer
        int roundedSkill = (int) Math.round(skill);
        int roundedDanger = (int) Math.round(danger);
        
        // Calculate the absolute difference
        int absoluteDifference = Math.abs(roundedSkill - roundedDanger);
        
        return absoluteDifference;
    }
}
