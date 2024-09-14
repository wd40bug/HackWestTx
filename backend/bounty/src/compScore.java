import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Hunter {
    String name;
    String pic;
    String location;
    double skillLevel;
    List<String> styles;
}

class Bounty {
    String name;
    String pic;
    String lastKnown;
    double danger;
    double reward;
    String client;
    String crime;
    String bountyCondition;
    List<String> styles;
}

class BountyMatch {
    Bounty bounty;
    double compatibilityScore;

    BountyMatch(Bounty bounty, double compatibilityScore) {
        this.bounty = bounty;
        this.compatibilityScore = compatibilityScore;
    }
}

double calculateCompatibilityScore(Hunter hunter, Bounty bounty) {
    double styleMatchScore = 0;
    double dangerDifferenceScore = 0;

    // Calculate style match score (higher if styles match)
    boolean styleMatch = false;
    for (String hunterStyle : hunter.styles) {
        for (String bountyStyle : bounty.styles) {
            if (hunterStyle.equals(bountyStyle)) {
                styleMatch = true;
                break;
            }
        }
        if (styleMatch) break;
    }
    styleMatchScore = styleMatch ? 100.0 : 0.0;

    // Calculate danger difference score (smaller difference gives higher score)
    double dangerDifference = Math.abs(hunter.skillLevel - bounty.danger);
    dangerDifferenceScore = Math.max(0, 100 - dangerDifference); // Assuming a maximum danger difference of 100

    // Combine the scores to get the compatibility score
    return styleMatchScore + dangerDifferenceScore;
}

List<Bounty> sortBountiesByCompatibility(List<BountyMatch> bountyMatches) {
    Collections.sort(bountyMatches, new Comparator<BountyMatch>() {
        @Override
        public int compare(BountyMatch bm1, BountyMatch bm2) {
            return Double.compare(bm2.compatibilityScore, bm1.compatibilityScore); // Higher scores come first
        }
    });

    // Extract sorted bounties from sorted matches
    List<Bounty> sortedBounties = new ArrayList<>();
    for (BountyMatch match : bountyMatches) {
        sortedBounties.add(match.bounty);
    }
    return sortedBounties;
}

List<Bounty> fetchBountiesFromDatabase() {
    List<Bounty> bounties = new ArrayList<>();
    // Database connection details
    String url = "jdbc:your_database_url";
    String user = "your_database_user";
    String password = "your_database_password";

    try (Connection conn = DriverManager.getConnection(url, user, password)) {
        String sql = "SELECT * FROM bounties"; // Modify as needed
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Bounty bounty = new Bounty();
                bounty.name = rs.getString("name");
                bounty.pic = rs.getString("pic");
                bounty.lastKnown = rs.getString("last_known");
                bounty.danger = rs.getDouble("danger");
                bounty.reward = rs.getDouble("reward");
                bounty.client = rs.getString("client");
                bounty.crime = rs.getString("crime");
                bounty.bountyCondition = rs.getString("bounty_condition");
                bounty.styles = Arrays.asList(rs.getString("styles").split(",")); // Adjust based on your database schema
                bounties.add(bounty);
            }
        }
    } catch (Exception e) {
        e.printStackTrace(); // Handle exceptions appropriately
    }

    return bounties;
}

List<Bounty> getSortedBountiesByCompatibility(Hunter hunter, List<Bounty> bounties) {
    List<BountyMatch> bountyMatches = new ArrayList<>();
    for (Bounty bounty : bounties) {
        double compatibilityScore = calculateCompatibilityScore(hunter, bounty);
        bountyMatches.add(new BountyMatch(bounty, compatibilityScore));
    }

    return sortBountiesByCompatibility(bountyMatches);
}

public static void main(String[] args) {
    Hunter hunter = new Hunter();
    hunter.name = "Roy Boone";
    hunter.pic = "https://picsum.photos/400/400.jpg";
    hunter.location = "-30.100558411551567,144.4983791184987";
    hunter.skillLevel = 56.0;
    hunter.styles = Arrays.asList("Dusty Getaway Dramas", "Bank Heist Bonanza");

    // Fetch bounties from database
    List<Bounty> bounties = fetchBountiesFromDatabase();

    // Get sorted bounties by compatibility
    List<Bounty> sortedBounties = getSortedBountiesByCompatibility(hunter, bounties);

    // Output sorted bounties
    for (Bounty bounty : sortedBounties) {
        System.out.println("Bounty: " + bounty.name);
        // Display other bounty details if needed
    }
}