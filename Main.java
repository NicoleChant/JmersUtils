import java.util.HashMap;
import java.util.Map;

public class Main {
 
  public static HashMap<String, Integer> countKmers(String sequence, int kmerLength){

    HashMap<String, Integer> kmerCounts = new HashMap<>();
    String kmerSlice;
    for(int i=0; i < sequence.length() - kmerLength + 1; ++i){
      kmerSlice = sequence.substring(i, i + kmerLength);
      kmerCounts.put(kmerSlice, kmerCounts.getOrDefault(kmerSlice, 0) + 1);
    }

    return kmerCounts;
  }

   public static final int encode(char character) {
        switch (character) {
            case 'A':
                return 0b00;
            case 'T':
                return 0b01;
            case 'G':
                return 0b11;
            case 'C':
                return 0b10;
            default:
                return -1; // (sentinel value)
        }
    }

   public static final String decode(Integer encodedCharacter){
      
            HashMap<Integer, Character> decodingMap = new HashMap<>()
            {
              {
                  put(0b00, 'A');
                  put(0b01, 'T');
                  put(0b11, 'G');
                  put(0b10, 'C');
              }
            };

            int slice;
            String decodedKmer = "";
            
            while(encodedCharacter > 1){
                slice = encodedCharacter & 0b11;
                decodedKmer += decodingMap.get(slice);
                encodedCharacter = encodedCharacter >> 2;
            }

            return decodedKmer;
   }

   public static HashMap<Integer, Integer> countBinaryKmers(String sequence, int kmerLength){
     HashMap<Integer, Integer> kmerCounts = new HashMap<>();
     String kmerSlice;
     int kmerSliceBinarized;
     int encoding;

     for(int i = 0; i < sequence.length() - kmerLength + 1; ++i){
        kmerSlice = sequence.substring(i, i + kmerLength);

        kmerSliceBinarized = 0b1;
        for(char nucleotide: kmerSlice.toCharArray()){
          kmerSliceBinarized = kmerSliceBinarized << 2 | encode(nucleotide);
        }
        kmerCounts.put(kmerSliceBinarized, kmerCounts.getOrDefault(kmerSliceBinarized, 0) + 1);    
     }

     return kmerCounts;
   }

  
  public static void main(String args[]){
    System.out.println("Welcome to Kmer-World!");
      
    String sequence = "AGCGAGAGA";
    int kmerLength = 2;

    if(args.length > 0){
      kmerLength = Integer.parseInt(args[0]);
    }



    HashMap<Integer, Integer> countKmers = countBinaryKmers(sequence, kmerLength);

    for(var entry: countKmers.entrySet()){
      var key = decode(entry.getKey());
      Integer value = entry.getValue();
      System.out.print("Key: " + key + ", Value: " + value + "\n");
    }

  }


}
