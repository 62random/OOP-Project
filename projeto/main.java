
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
import java.util.Scanner;
import java.io.*;

public class main
{
    public static void main(String[] args){
        
        System.out.print("--------------------------------\n");
        BDgeral bd = new BDgeral();
        
        try{
            bd.carregaEstado("/Users/Ambrosiny/Desktop/Universidade/2ano/POO/trabalho_poo/projeto/base");
        }
        catch (FileNotFoundException e){
            System.out.println("file not fount");
        }
        catch(IOException a){
            System.out.println("file sda");
        }
        catch (ClassNotFoundException k){
            System.out.println("file");
        }
        
        Scanner in = new Scanner(System.in);
        
        int i = 0;
        while(i != -1){
            CIndividual a = criaIndividual();
            bd.addIndividual(a);
            
            i = in.nextInt();
            
        }
        try{
            bd.guardaEstado("/Users/Ambrosiny/Desktop/Universidade/2ano/POO/trabalho_poo/projeto/base");
        }
        catch (FileNotFoundException e){
            System.out.println("file not fount");
        }
        catch(IOException a){
            System.out.println("file sda");
        }
        
        /*
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

        

        Fatura f1 = new Fatura(54,"",LocalDate.of(2013,6,2),1,"","",123);
        Fatura f2 = new Fatura(23,"",LocalDate.of(2013,6,21),6,"","",121);
        Fatura f4 = new Fatura(23,"",LocalDate.of(2013,6,2),1,"","",41);
        Fatura f3 = new Fatura(23,"",LocalDate.now(),1,"","",12);
                

        bd.addFatura(f1);
        bd.addFatura(f2);
        bd.addFatura(f3);
        bd.addFatura(f4);
        
        System.out.print("--------------------------------\n");
        
        //System.out.print(bd.toString());
        
        System.out.print(bd.total_faturado(LocalDate.of(0,1,1),LocalDate.of(3000,1,1),23));
        System.out.print(bd.total_faturado(LocalDate.of(0,1,1),LocalDate.of(3000,1,1),54));
        System.out.print(bd.total_faturado(LocalDate.of(0,1,1),LocalDate.of(3000,1,1),21));*/
        System.out.print(bd.toString());
    }
    
    private static CIndividual criaIndividual(){
        Scanner in = new Scanner(System.in);
        
        System.out.println("New User");
        int nif,n_agregado = -1;
        double coeficiente;
        String mail,nome,morada,password;
        String aux = "le";
        Set<Integer> fatura = new HashSet<>();
        Set<Integer> agregado = new HashSet<>();
        Set<String> setores = new HashSet<>();
        
        System.out.println("Nif");
        nif = in.nextInt();
        in.nextLine();
        System.out.println("Mail");
        mail = in.nextLine();
        System.out.println("Nome");
        nome = in.nextLine();
        System.out.println("Morada");
        morada = in.nextLine();
        System.out.println("Password");
        password = in.nextLine();
        System.out.println("Setores de desconto. Para terminar escreva done");
        while(!aux.equals("done")){
            System.out.println("Setor:");
            aux = in.nextLine();
            if (!aux.equals("done"))
                setores.add(aux);
        }
        System.out.println("Nif do agregado");
        while(n_agregado != -2){
            System.out.println("Próximo nif: ");
            n_agregado = in.nextInt();
            if (n_agregado != -2 && n_agregado >= 0)
                agregado.add(n_agregado);
        }
        
        
        return new CIndividual(nif,mail,nome,morada,password,agregado.size(),agregado,1.2,setores,fatura);
        
        
        
        
    }
}
