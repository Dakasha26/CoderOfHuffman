
package toi.lab2;


public class Tree{
    
    private final FrequencyTable LETTERS_NUMBERS; 
    private Node[] tree;
    
    private boolean leafIsFound;
    
    public Tree(String inputStr, char[] alphabet){
        LETTERS_NUMBERS = new FrequencyTable(alphabet, inputStr);
        
        tree = new Node[alphabet.length];
        for(int i = 0; i < alphabet.length; i++)
            tree[i] = new Node(alphabet[i], LETTERS_NUMBERS.getCountingOfLetter()[i]);
        
        while(tree.length != 1){
            sortTree();
            tree[1] = Node.createRelation(tree[0], tree[1]);
            Node[] buffArray = new Node[tree.length-1];
            System.arraycopy(tree, 1, buffArray, 0, tree.length-1);
            tree = new Node[tree.length-1];
            System.arraycopy(buffArray, 0, tree, 0, buffArray.length);
        }
    }
    
    private void sortTree(){
        Node buffNode;
        for(int i = 0; i < tree.length; i++)
            for(int j = 0; j < tree.length-1; j++)
                if(tree[j].getPriority() > tree[j+1].getPriority()){
                    buffNode = tree[j+1];
                    tree[j+1] = tree[j];
                    tree[j] = buffNode;
                }
    }

    
    public void showInfo(){
        byte[] countingOfLetters = LETTERS_NUMBERS.getCountingOfLetter();
        char[] alphabet = LETTERS_NUMBERS.getAlphabet();
        
        System.out.println("Количество символов в сообщении: ");
        for(int i = 0; i < countingOfLetters.length; i++)
            System.out.println("\t" + alphabet[i] + ": " + countingOfLetters[i]);        
        
        System.out.println("Полученные коды символов: ");
        for(int i = 0; i < alphabet.length; i++)
            System.out.println("\t" + alphabet[i] + ": " + findLettersCode(alphabet[i]));        
    }
    
    private String findLettersCode(char letter){
        String code = new String();
        Node root = tree[0]; // корень дерева
        
        leafIsFound = false;
        code = checkTree(root, letter, code);
        return code;
    }
    
    private String checkTree(Node node, char letter, String code){
        if((node.getLetter() != letter) && (leafIsFound == false)){
            if(node.getLeftNeighbor() != null){
                code += "0";
                code = checkTree(node.getLeftNeighbor(), letter, code);
            } else{ 
                if(code.charAt(code.length()-1) == '0')
                    code = code.substring(0, code.length()-1);
                else
                    code = code.substring(0, code.length()-2);
                return code;
            }

            if((node.getRightNeighbor() != null) && (leafIsFound == false)){
                code += "1";
                code = checkTree(node.getRightNeighbor(), letter, code);
            }
        } else{
            leafIsFound = true;
        }
        return code;
    }
    
    public String getEncoding(String inputStr){
        String translation = inputStr;
        char[] alphabet = LETTERS_NUMBERS.getAlphabet();
        
        for(int i = 0; i < alphabet.length; i++){
            String oldChar = Character.toString(alphabet[i]);
            String newCode = findLettersCode(alphabet[i]);
            translation = translation.replace(oldChar, newCode);
        }
        return translation;
    }
    
    public String getDecoding(String encodedStr){
        String translation = new String();
        char[] alphabet = LETTERS_NUMBERS.getAlphabet();
        String lettersCode;

        while(encodedStr.length() != 0)
            for(int i = 0; i < alphabet.length; i++){
                lettersCode = findLettersCode(alphabet[i]);
                if(encodedStr.indexOf(lettersCode) == 0){
                    translation += alphabet[i];
                    encodedStr = encodedStr.substring(lettersCode.length());
                    break;
                }
            }
        
        return translation;     
    }  
}
