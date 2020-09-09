

package toi.lab2;


public class FrequencyTable{
    private byte[] countingOfLetters;
    private final char[] ALPHABET; // Считается, что сортировка не меняет самого массива
    
    public FrequencyTable(char[] alphabet, String inputStr){
        this.ALPHABET = alphabet;
        
        countingOfLetters = countLetters(alphabet, inputStr);
        sortFrequencyTable(); 
    }
        
    // Сортирует в порядке убывания количества включений символа в строку
    private void sortFrequencyTable(){
        byte byteChanger; char charChanger;
        for(int i = 0; i < countingOfLetters.length; i++)
            for(int j = 0; j < countingOfLetters.length - 1; j++)
                if(countingOfLetters[j] > countingOfLetters[j+1]){
                    byteChanger = countingOfLetters[j+1];
                    charChanger = ALPHABET[j+1];
                    countingOfLetters[j+1] = countingOfLetters[j];
                    countingOfLetters[j] = byteChanger;
                    ALPHABET[j+1] = ALPHABET[j];
                    ALPHABET[j] = charChanger;
                }   
    }
    
    private byte[] countLetters(char[] alphabet, String inputStr){
        countingOfLetters = new byte[alphabet.length];
        for(int i = 0; i < alphabet.length; i++){
            byte counter = 0;
            for(int j = 0; j < inputStr.length(); j++)
                if(inputStr.charAt(j) == alphabet[i])
                    counter += 1;
            countingOfLetters[i] = counter;
        }
        return countingOfLetters;
    }
    
    public char[] getAlphabet(){
        return ALPHABET;
    }
    
    public byte[] getCountingOfLetter(){
        return countingOfLetters;
    }
        
}
