import java.util.*;

public class consensus_demo {

    public static void main(String[] args) {
        Random rand = new Random();

        //  Proof of Work (PoW)
        System.out.println("=== Proof of Work (PoW) Simulation ===");
        Map<String, Integer> miner = new HashMap<>();
        miner.put("miner1", rand.nextInt(100));
        miner.put("miner2", rand.nextInt(100));
        miner.put("miner3", rand.nextInt(100));

        for (Map.Entry<String, Integer> entry : miner.entrySet()) {
            System.out.println(entry.getKey() + " has power: " + entry.getValue());
        }

        String selectedPoW = Collections.max(miner.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("Selected Validator (PoW): " + selectedPoW);
        System.out.println("Explanation: In PoW, the miner with the highest computing power is chosen to validate the block.\n");

        // Proof of Stake (PoS) 
        System.out.println("=== Proof of Stake (PoS) Simulation ===");
        Map<String, Integer> staker = new HashMap<>();
        staker.put("staker1", rand.nextInt(100));
        staker.put("staker2", rand.nextInt(100));
        staker.put("staker3", rand.nextInt(100));

        for (Map.Entry<String, Integer> entry : staker.entrySet()) {
            System.out.println(entry.getKey() + " has stake: " + entry.getValue());
        }

        String selectedPoS = Collections.max(staker.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("Selected Validator (PoS): " + selectedPoS);
        System.out.println("Explanation: In PoS, the validator with the highest stake (amount of coins) is selected.\n");

        //  Delegated Proof of Stake (DPoS) 
        System.out.println("=== Delegated Proof of Stake (DPoS) Simulation ===");
        Map<String, Integer> delegateVotes = new HashMap<>();
        delegateVotes.put("delegate1", 0);
        delegateVotes.put("delegate2", 0);
        delegateVotes.put("delegate3", 0);

        String[] voters = {"voter1", "voter2", "voter3"};
        String[] delegates = {"delegate1", "delegate2", "delegate3"};

        for (String voter : voters) {
            String vote = delegates[rand.nextInt(delegates.length)];
            delegateVotes.put(vote, delegateVotes.get(vote) + 1);
            System.out.println(voter + " voted for " + vote);
        }

        System.out.println("Vote count:");
        for (Map.Entry<String, Integer> entry : delegateVotes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " votes");
        }

        int maxVotes = Collections.max(delegateVotes.values());
        List<String> topDelegates = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : delegateVotes.entrySet()) {
            if (entry.getValue() == maxVotes) {
                topDelegates.add(entry.getKey());
            }
        }

        String selectedDPoS = topDelegates.get(rand.nextInt(topDelegates.size()));
        System.out.println("Selected Delegate (DPoS): " + selectedDPoS);
        System.out.println("Explanation: In DPoS, voters choose delegates. The one with the most votes (or randomly among top-voted) is selected.\n");
    }
}
