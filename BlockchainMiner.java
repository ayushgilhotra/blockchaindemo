import java.security.MessageDigest;
import java.util.Date;

class Block {// we r defining  below the index,timestamp,data,previousHAsh,hyash,nonce.
    public int index;
    public long timestamp;
    public String data;
    public String previousHash;
    public String hash;
    public int nonce;

    //creating a  Constructor
    public Block(int index, String data, String previousHash) {
        this.index = index;// index no. of the block
        this.timestamp = new Date().getTime();// stores the time nd loc. of the data 
        this.data = data;//store the data of the block
        this.previousHash = previousHash;// unique id  of previous block
        this.nonce = 0;// no. once used 
        this.hash = calculateHash();// unique identity of the block
    }

    // SHA-256 hash generator that helps to convert the string into the 256 bits
    public String calculateHash() {
        String input = index + timestamp + data + previousHash + nonce;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Proof-of-Work: Mine the block
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); // e.g., "0000"
        long startTime = System.currentTimeMillis(); // Start time

        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }

        long endTime = System.currentTimeMillis(); // End time

        System.out.println(" Block mined! Hash: " + hash);
        System.out.println(" Nonce attempts: " + nonce);
        System.out.println(" Time taken: " + (endTime - startTime) + " ms\n");
    }

    public String toString() {
        return "\nBlock " + index +
                "\nData: " + data +
                "\nHash: " + hash +
                "\nPrevious Hash: " + previousHash + "\n";
    }
}

public class BlockchainMiner {
    public static void main(String[] args) {
        int difficulty = 4; // Means hash must start with "0000"

        Block block = new Block(0, "Mining Block Example", "0");

        System.out.println("Mining Block...");
        block.mineBlock(difficulty);

        System.out.println(block);
    }
}
