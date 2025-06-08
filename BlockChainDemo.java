import java.security.MessageDigest;
import java.util.Date;

class Block {
    public int index;
    public long timestamp;
    public String data;
    public String previousHash;
    public String hash;
    public int nonce;

    // Constructor
    public Block(int index, String data, String previousHash) {
        this.index = index;
        this.timestamp = new Date().getTime();
        this.data = data;
        this.previousHash = previousHash;
        this.nonce = 0;
        this.hash = calculateHash();
    }

    // SHA-256 hash generator
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

    // To display block information
    public String toString() {
        return "\nBlock " + index + 
               "\nData: " + data + 
               "\nHash: " + hash + 
               "\nPrevious Hash: " + previousHash + "\n";
    }
}

public class Practice {
    public static void main(String[] args) {
        // Step 1: Create blocks and link them
        Block genesisBlock = new Block(0, "Genesis Block", "0");
        Block block1 = new Block(1, "Block 1 Data", genesisBlock.hash);
        Block block2 = new Block(2, "Block 2 Data", block1.hash);

        // Step 2: Print original blockchain
        System.out.println(" Original Blockchain:");
        System.out.println(genesisBlock);
        System.out.println(block1);
        System.out.println(block2);

        // Step 3: Tamper with Block 1
        block1.data = "Tampered Block 1";
        block1.hash = block1.calculateHash();

        System.out.println(" After Tampering Block 1: ");
        System.out.println();
    }}
