import java.util.Scanner;

import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.InputMismatchException;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;


public class menu extends Exception
{

    
    private static void imprimirmenu(){
          System.out.println("------------------Java Fatura------------------");
        System.out.println("Opçoes");
        System.out.println("1-Adicionar Faturar");
        System.out.println("2-Adicionar Empresa");
        
        System.out.println("3-Guardar Ficheiro");
        System.out.println("4-Importar Ficheiro");
        System.out.println("5-Imprimir BD");
        System.out.println("6-Sair");
    }
    // instance variables - replace the example below with your own
    public static void menu()
    {
        Scanner ac = new Scanner(System.in);
        imprimirmenu();
        boolean flag = true;
        int choice= -1;
        BDgeral bd = new BDgeral(); 
        int int1,int2,int3,int4,int5 = 0;
        long long1,long2 = 0;
        double double1, double2 = 0;
        String string1,string2 ,string3= null;
        Fatura faturaaux = null;
               Set<Integer> faturas = new TreeSet<>();
        Set<String> setores = new TreeSet<>();
        Set<Integer> agregados = new TreeSet<>();
        CIndividual c1 = new CIndividual(1,"","","","",4,agregados,1.32,setores,faturas);
        setores.add("educação");
        agregados.add(123);
        agregados.add(12);
        CIndividual c2 = new CIndividual(12,"","","","",3,agregados,5,setores,faturas);
        
        setores.add("las");
        Empresa e1 = new Empresa(54,"","","","",setores,1.5,faturas);
        setores.add("filmes");
        Empresa e2 = new Empresa(23,"","","","",setores,1,faturas);
        
        bd.addIndividual(c1);
        bd.addIndividual(c2);
        bd.addEmpresa(e1);
        bd.addEmpresa(e2);
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

                    try{
                         System.out.println("Id da Fatura : ");
                         int1=ac.nextInt();
                         System.out.println("Nome do Emitente: ");
                         string1=ac.next();
                         System.out.println("NIF cliente: ");
                         int2=ac.nextInt();
                         System.out.println("Descricao : ");
                         string2=ac.next();
                         System.out.println("Categoria : ");
                         string3=ac.next();
                         System.out.println("Valor : ");
                         double1 = ac.nextDouble();
                         
                    }
                    catch(InputMismatchException e){
                        System.out.println("Failed");
                        ac.next();
                        break;
                    }
                    
                    faturaaux = new Fatura(int1,string1,LocalDateTime.now(),int2,string2,string3,double1);
                    bd.addFatura(faturaaux);
                    System.out.println("Inserido com sucesso ");
                    break;
                case 2:
                    break;
                case 3:
                    try{
                        System.out.println("Nome Ficheiro : ");
                        string1=ac.next();
                        bd.guardaEstado(string1);                        
                    }
                    catch(InputMismatchException e){
                        System.out.println("Failed");
                        ac.next();
                        break;
                    }
                    catch(FileNotFoundException e){
                        System.out.println("Ficheiro nao encontrado");
                        ac.next();
                        break;  
                    }
                    catch(IOException e){
                        System.out.println("Erro ao aceder ao ficheiro");
                        ac.next();
                        break;  
                    }
                    System.out.println("Gravado com sucesso");
                    break;
                case 4:
                    try{
                        System.out.println("Nome Ficheiro : ");
                        string1=ac.next();
                        bd.carregaEstado(string1);                        
                    }
                    catch(InputMismatchException e){
                        System.out.println("Failed");
                        ac.next();
                        break;
                    }
                    catch(FileNotFoundException e){
                        System.out.println("Ficheiro nao encontrado");
                        ac.next();
                        break;  
                    }
                    catch(IOException e){
                        System.out.println("Erro ao aceder ao ficheiro");
                        ac.next();
                        break;  
                    }
                    catch(ClassNotFoundException e){
                        System.out.println("Erro ao aceder ao ficheiro");
                        ac.next();
                        break;  
                    }
                    System.out.println("Lido com sucesso");
                    break;
               
                case 5:
                    System.out.print(bd.toString());
                    break;
                case 6:
                    System.out.println("A sair");
                    flag = false;
                    break;
                    
                case 0:
                    imprimirmenu();
                    break;
                
                default:
                    System.out.println("Opçao Invalida");
                }
        
            }
        
        System.out.println("Saiu com sucesso");
    }
    
}
