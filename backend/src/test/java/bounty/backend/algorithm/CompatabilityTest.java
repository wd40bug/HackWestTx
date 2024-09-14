package bounty.backend.algorithm;

import bounty.backend.model.Bounty;
import bounty.backend.model.Hunter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompatabilityTest {

    @Test
    public void testCompareHunterSkillWithBountyDanger() {
        // Create instances of Hunter and Bounty
        Hunter hunter = new Hunter("hunter1", "John Doe", "New York", "john_doe.png", 75.0);
        Bounty bounty = new Bounty("bounty1", "Dragon", "dragon.png", "London", 10000, 80.0, "King", "Theft");

        // Call the method from Compatibility class
        int result = Compatibility.compareHunterSkillWithBountyDanger(hunter, bounty);

        // Assert the expected outcome
        // Assuming the difference should be 5 in this case (|75 - 80| = 5)
        assertEquals(5, result, "The absolute difference between skill and danger should be 5.");
    }
}