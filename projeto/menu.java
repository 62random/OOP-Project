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
        System.out.println("3-Adicionar Contribuinte Individual");
        
        System.out.println("4-Guardar Ficheiro");
        System.out.println("5-Importar Ficheiro");
        System.out.println("6-Imprimir BD");
        System.out.println("7-Sair");
    }
    
    private static void inserirFatura(BDgeral bd) throws Erros{
        int int_1,int_2,int_3,int_4, int_5, int_6 = 0;
        double double_1=0;
        String string_1, string_2, string_3 = null;
        Scanner ac = new Scanner(System.in).useDelimiter("\\n");
        try{
                         System.out.println("Id da Fatura : ");
                         int_1=ac.nextInt();
                         System.out.println("Nome do Emitente: ");
                         string_1=ac.next();
                         System.out.println("NIF cliente: ");
                         int_2=ac.nextInt();
                         System.out.println("Descricao : ");
                         string_2=ac.next();
                         System.out.println("Categoria : ");
                         string_3=ac.next();
                         System.out.println("Valor : ");
                         double_1 = ac.nextDouble();
                         
                         System.out.println("----Data---- ");
                         System.out.println("Dia: ");
                         int_4=ac.nextInt();
                         System.out.println("Mes: ");
                         int_5=ac.nextInt();
                         System.out.println("Ano: ");
                         int_6=ac.nextInt();
                         
        }
           catch(InputMismatchException e){
           throw new Erros("Falha ao inserir");
        }
        Fatura faturaaux = null;            
        faturaaux = new Fatura(int_1,string_1,LocalDate.of(int_6,int_5,int_4),int_2,string_2,string_3,double_1);
        bd.addFatura(faturaaux);
    }
    
    private static void inserirEmpresa(BDgeral bd) throws Erros{
        int int_1,int_2,int_3,int_4, int_5, int_6 = 0;
        double double_1=0;
        String string_1, string_2, string_3, string_4 = null;
        Scanner ac = new Scanner(System.in).useDelimiter("\\n");
        Set<Integer> faturas = new TreeSet<>();
        Set<String> setores = new TreeSet<>();
        Set<Integer> agregados = new TreeSet<>();
        try{
                         System.out.println("NIF da empresa : ");
                         int_1 = ac.nextInt();
                         System.out.println("Email: ");
                         string_1 = ac.next();
                         System.out.println("Nome: ");
                         string_2 = ac.next();
                         System.out.println("Morada: ");
                         string_3 = ac.next();
                         System.out.println("Password: ");
                         string_4 = ac.next();
                         System.out.println("Coeficiente de decução fiscal: ");
                         double_1 = ac.nextDouble();
                         
        }
           catch(InputMismatchException e){
           throw new Erros("Falha ao inserir");
        }
        Empresa empresa_aux = null;            
        empresa_aux = new Empresa(int_1, string_1, string_2, string_3, string_4, setores,double_1,faturas); 
        bd.addEmpresa(empresa_aux);
    }
    
    private static void inserirCIndi(BDgeral bd) throws Erros{
        int int_1,int_2,int_3,int_4, int_5, int_6 = 0;
        double double_1=0;
        String string_1, string_2, string_3, string_4 = null;
        Scanner ac = new Scanner(System.in).useDelimiter("\\n");
        Set<Integer> faturas = new TreeSet<>();
        Set<String> setores = new TreeSet<>();
        Set<Integer> agregados = new TreeSet<>();
        try{            
                         System.out.println("NIF : ");
                         int_1 = ac.nextInt();
                         System.out.println("Email: ");
                         string_1 = ac.next();
                         System.out.println("Nome: ");
                         string_2 = ac.next();
                         System.out.println("Morada: ");
                         string_3 = ac.next();
                         System.out.println("Password: ");
                         string_4 = ac.next();
                         System.out.println("Numero de elementos do agregado: ");
                         int_2 = ac.nextInt();
                         System.out.println("Coeficiente de decução fiscal: ");
                         double_1 = ac.nextDouble();
                         
                         
                         
           }
           catch(InputMismatchException e){
           throw new Erros("Falha ao inserir");
        }
        CIndividual individual_aux = null;            
        individual_aux = new CIndividual(int_1,string_1,string_2,string_3,string_4,int_2, agregados, double_1, setores, faturas);
        bd.addIndividual(individual_aux);
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
                        inserirFatura(bd);
                    }
                    catch(Erros e){
                    System.out.println("Falha ao inserir");
                    break;
                    }
                    System.out.println("Inserido com sucesso");
                    break;
                    
                case 2:
                    try{
                        inserirEmpresa(bd);
                    }
                    catch(Erros e){
                    System.out.println("Falha ao inserir");
                    break;
                    }
                    System.out.println("Inserido com sucesso");
                    break;
                    
                case 3:
                    try{
                        inserirCIndi(bd);
                    }
                    catch(Erros e){
                    System.out.println("Falha ao inserir");
                    break;
                    }
                    System.out.println("Inserido com sucesso");
                    break;
                    
                case 4:
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
                case 5:
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
               
                case 6:
                    System.out.print(bd.toString());
                    break;
                case 7:
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
