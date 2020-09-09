
package toi.lab2;


public class ToILab2 {
    private static final char[] src_A = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З'};
    private static Tree tree;
    private static String encodedStr; 
    
    public static void main(String[] args){
        String inputStr;

        do{
           inputStr = Interface.runMenu();
           
           switch(inputStr){
                case "1":
                    inputStr = Interface.getStrForEncoding();
                    try{
                        tree = new Tree(inputStr, checkString(inputStr));
                        encodedStr = tree.getEncoding(inputStr);
                        System.out.println("Закодированное сообщение: " + encodedStr);
                        // tree.showInfo();  //Для показа дополнительной информации
                    } catch(Exception e){
                        System.out.println("Некорректный ввод: наличие недопустимых символов.\n");
                    }
                    break;
                case "2":
                    if(encodedStr != null){
                        System.out.println("Для декодирования используется сохранённая в памяти таблица частот использованных символов.");
                        System.out.println("Декодированное сообщение: " + tree.getDecoding(encodedStr));
                    } else{
                        System.out.println("Ошибка: Закодированная строка отстутствует.");
                    }
                    break;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Некорректный ввод: возможен ввод только цифр: 1, 2 или 3");
                    break;
           }
        } while(true);
    }
    
    private static char[] checkString(String str) throws Exception{
        byte counter = 0;
        for(int i = 0; i < str.length(); i++)
            if(isContains(str.charAt(i), src_A) == false)
                throw new Exception();
            else
                counter += 1;
        
        char[] alphabet_A = new char[counter];
        counter = 0;
        for(int i = 0; i < str.length(); i++)
            if(isContains(str.charAt(i), alphabet_A) == false){
                alphabet_A[counter] = str.charAt(i);
                counter += 1;
            }
        return alphabet_A;
    }
    
    private static boolean isContains(char chr, char[] alphabet){
        for(int i = 0; i < alphabet.length; i++){
            if(alphabet[i] == chr)
                return true;
        }
        return false;
    }
}
