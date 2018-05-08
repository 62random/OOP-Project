
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

public class main
{
    public static void main(String[] args){
        
        System.out.print("--------------------------------\n");
        BDgeral bd = new BDgeral();
        
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
        
        Fatura f1 = new Fatura(54,"",LocalDateTime.now(),1,"","",123);
        Fatura f2 = new Fatura(23,"",LocalDateTime.now(),1,"","",121);
        Fatura f4 = new Fatura(12,"",LocalDateTime.now(),1,"","",123);
        Fatura f3 = new Fatura(23,"",LocalDateTime.now(),1,"","",12);
        
        
        bd.addFatura(f1);
        bd.addFatura(f2);
        bd.addFatura(f3);
        bd.addFatura(f4);
        
        System.out.print(bd.toString());
        
    }
}
