

package toi.lab2;

import java.util.Scanner;


public class Interface{
    private static Scanner input;
    
    public static String runMenu(){
        System.out.println("Выберите действие:\n" +
                              "\t1.Закодировать строку.\n" +
                              "\t2.Декодировавть строку (доступно только при наличии закодированной ранее строки).\n" +
                              "\t3.Завершить работу программы.");
        System.out.print(">> ");
        input = new Scanner(System.in, "CP1251"); 
        
        return input.nextLine();
    }
    
    public static String getStrForEncoding(){
        System.out.print("Введите строку: ");
        input = new Scanner(System.in, "CP1251"); 
        
        return input.nextLine();
    }
}
