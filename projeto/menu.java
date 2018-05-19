import java.util.Scanner;
import java.util.InputMismatchException;


public class menu extends Exception 
{

    // instance variables - replace the example below with your own
    public static void menu()
    {
        Scanner ac = new Scanner(System.in);
        System.out.println("------------------Java Fatura------------------");
        System.out.println("Opçoes");
        System.out.println("1-Menu User");
        System.out.println("2-Menu Empresa");
        System.out.println("3-Guardar Ficheiro");
        System.out.println("4-Importar Ficheiro");
        System.out.println("5-Sair");
        boolean flag = true;
        int choice= -1;
        while(flag){
            try{
                choice=ac.nextInt(); 
            }
            catch(InputMismatchException e){ 
                System.out.println("Insere digitos apenas");
                ac.next();
               
                continue;
            }
                
            switch(choice){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4: 
                    break;
                case 5:
                    System.out.println("A sair");
                    flag = false;
                    break;
                
                default:
                    System.out.println("Opçao Invalida");
                }
        
            }
        
        System.out.println("Saiu com sucesso");
    }
    
}
