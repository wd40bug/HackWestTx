package bounty.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import bounty.backend.algorithm.Compatability;
import bounty.backend.model.*;

public class CompatabilityTest {

    private static final double DELTA = 1e-6; // Tolerance for floating-point comparisons

    @Test
    public void testCompatabilityAliveCondition() {
        // Arrange
        Hunter hunter = new Hunter(
            "h1",
            "Hunter One",
            "pic_url",
            5,
            new Location(0, 0),
            10.0,
            5.0,
            7,
            HunterClass.Gunslinger
        );

        Bounty bounty = new Bounty(
            "b1",
            "Bounty One",
            "pic_url",
            new Location(0, 0),
            1000.0,
            5.0,
            "Client A",
            Crime.Bandit,
            8.0,
            4.0,
            6,
            Condition.alive,
            Arrays.asList("Stealth")
        );

        // Act
        double result = Compatability.compatability(hunter, bounty);

        // Expected calculation
        double expected = 0;
        expected += (hunter.speed() - bounty.speed()) * 1;                 // (10.0 - 8.0) * 1 = 2.0
        expected += (hunter.draw_speed() - bounty.draw_speed()) * 2;       // (5.0 - 4.0) * 2 = 2.0
        expected += (hunter.discernment() - bounty.slipperiness()) * 1;    // (7 - 6) * 1 = 1.0
        expected += 30; // better_class_const since Gunslinger matches Bandit

        // No negative class match in this case

        expected *= 0.5;  // Condition is alive (now multiplied by 0.5)

        // Assert
        assertEquals(expected, result, DELTA);
    }

    @Test
    public void testCompatabilityDeadCondition() {
        // Arrange
        Hunter hunter = new Hunter(
            "h2",
            "Hunter Two",
            "pic_url",
            6,
            new Location(0, 0),
            9.0,
            6.0,
            8,
            HunterClass.Sniper
        );

        Bounty bounty = new Bounty(
            "b2",
            "Bounty Two",
            "pic_url",
            new Location(0, 0),
            2000.0,
            6.0,
            "Client B",
            Crime.Murderer,
            7.0,
            5.0,
            7,
            Condition.dead,
            Arrays.asList("Aggressive")
        );

        // Act
        double result = Compatability.compatability(hunter, bounty);

        // Expected calculation
        double expected = 0;
        expected += (hunter.speed() - bounty.speed()) * 1;                 // (9.0 - 7.0) * 1 = 2.0
        expected += (hunter.draw_speed() - bounty.draw_speed()) * 2;       // (6.0 - 5.0) * 2 = 2.0
        expected += (hunter.discernment() - bounty.slipperiness()) * 1;    // (8 - 7) * 1 = 1.0
        expected += 30; // better_class_const since Sniper matches Murderer

        // No negative class match in this case

        expected *= 2.0;  // Condition is dead (now multiplied by 2.0)

        // Assert
        assertEquals(expected, result, DELTA);
    }

    @Test
    public void testCompatabilityDeadOrAliveCondition() {
        // Arrange
        Hunter hunter = new Hunter(
            "h3",
            "Hunter Three",
            "pic_url",
            7,
            new Location(0, 0),
            8.0,
            7.0,
            9,
            HunterClass.Cowboy
        );

        Bounty bounty = new Bounty(
            "b3",
            "Bounty Three",
            "pic_url",
            new Location(0, 0),
            1500.0,
            7.0,
            "Client C",
            Crime.Rustler,
            6.0,
            6.0,
            8,
            Condition.dead_or_alive,
            Arrays.asList("Stealth")
        );

        // Act
        double result = Compatability.compatability(hunter, bounty);

        // Expected calculation
        double expected = 0;
        expected += (hunter.speed() - bounty.speed()) * 1;                 // (8.0 - 6.0) * 1 = 2.0
        expected += (hunter.draw_speed() - bounty.draw_speed()) * 2;       // (7.0 - 6.0) * 2 = 2.0
        expected += (hunter.discernment() - bounty.slipperiness()) * 1;    // (9 - 8) * 1 = 1.0
        expected += 30; // better_class_const since Cowboy matches Rustler

        // No negative class match in this case

        expected *= 1.0;  // Condition is dead_or_alive (now multiplied by 1.0)

        // Assert
        assertEquals(expected, result, DELTA);
    }

    @Test
    public void testCompatabilityNoClassMatch() {
        // Arrange
        Hunter hunter = new Hunter(
            "h4",
            "Hunter Four",
            "pic_url",
            5,
            new Location(0, 0),
            7.0,
            4.0,
            6,
            HunterClass.Sheriff
        );

        Bounty bounty = new Bounty(
            "b4",
            "Bounty Four",
            "pic_url",
            new Location(0, 0),
            800.0,
            4.0,
            "Client D",
            Crime.Hustler,
            5.0,
            3.0,
            5,
            Condition.alive,
            Arrays.asList("Stealth")
        );

        // Act
        double result = Compatability.compatability(hunter, bounty);

        // Expected calculation
        double expected = 0;
        expected += (hunter.speed() - bounty.speed()) * 1;                 // (7.0 - 5.0) * 1 = 2.0
        expected += (hunter.draw_speed() - bounty.draw_speed()) * 2;       // (4.0 - 3.0) * 2 = 2.0
        expected += (hunter.discernment() - bounty.slipperiness()) * 1;    // (6 - 5) * 1 = 1.0
        expected -= 15; // No positive class match

        // Negative class match check
        if (hunter.type() == HunterClass.Sheriff && bounty.crime() == Crime.Bandit) {
            expected -= 30;
        }

        // In this case, hunter is Sheriff, bounty crime is Hustler - no negative class match

        expected *= 0.5;  // Condition is alive (now multiplied by 0.5)

        // Assert
        assertEquals(expected, result, DELTA);
    }

    @Test
    public void testCompatabilityNegativeScore() {
        // Arrange
        Hunter hunter = new Hunter(
            "h5",
            "Hunter Five",
            "pic_url",
            3,
            new Location(0, 0),
            5.0,
            2.0,
            4,
            HunterClass.Trickster
        );

        Bounty bounty = new Bounty(
            "b5",
            "Bounty Five",
            "pic_url",
            new Location(0, 0),
            500.0,
            9.0,
            "Client E",
            Crime.Vandal,
            9.0,
            5.0,
            8,
            Condition.dead,
            Arrays.asList("Aggressive")
        );

        // Act
        double result = Compatability.compatability(hunter, bounty);

        // Expected calculation
        double expected = 0;
        expected += (hunter.speed() - bounty.speed()) * 1;                 // (5.0 - 9.0) * 1 = -4.0
        expected += (hunter.draw_speed() - bounty.draw_speed()) * 2;       // (2.0 - 5.0) * 2 = -6.0
        expected += (hunter.discernment() - bounty.slipperiness()) * 1;    // (4 - 8) * 1 = -4.0
        expected -= 15; // No positive class match

        // Negative class match check
        if (hunter.type() == HunterClass.Trickster && bounty.crime() == Crime.Murderer) {
            expected -= 30; // But crime is Vandal, so no negative class match here
        }

        // In this case, no negative class match

        expected *= 2.0;  // Condition is dead (now multiplied by 2.0)

        // Total expected: (-4.0 - 6.0 - 4.0 - 15) * 2.0 = (-29.0) * 2.0 = -58.0

        // Assert
        assertEquals(expected, result, DELTA);
    }

    @Test
    public void testCompatabilityNegativeClassMatch() {
        // Arrange
        Hunter hunter = new Hunter(
            "h6",
            "Hunter Six",
            "pic_url",
            5,
            new Location(0, 0),
            6.0,
            3.0,
            5,
            HunterClass.Sniper
        );

        Bounty bounty = new Bounty(
            "b6",
            "Bounty Six",
            "pic_url",
            new Location(0, 0),
            1000.0,
            5.0,
            "Client F",
            Crime.Rustler,
            5.0,
            3.0,
            5,
            Condition.dead_or_alive,
            Arrays.asList("Stealth")
        );

        // Act
        double result = Compatability.compatability(hunter, bounty);

        // Expected calculation
        double expected = 0;
        expected += (hunter.speed() - bounty.speed()) * 1;                 // (6.0 - 5.0) * 1 = 1.0
        expected += (hunter.draw_speed() - bounty.draw_speed()) * 2;       // (3.0 - 3.0) * 2 = 0.0
        expected += (hunter.discernment() - bounty.slipperiness()) * 1;    // (5 - 5) * 1 = 0.0
        expected -= 15; // No positive class match

        // Negative class match check
        if (hunter.type() == HunterClass.Sniper && bounty.crime() == Crime.Rustler) {
            expected -= 30; // Negative class match
        }

        expected *= 1.0;  // Condition is dead_or_alive (multiplied by 1.0)

        // Total expected: (1.0 + 0.0 + 0.0 -15 -30) * 1.0 = (-44.0)

        // Assert
        assertEquals(expected, result, DELTA);
    }
}