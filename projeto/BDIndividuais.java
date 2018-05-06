import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;


public class BDIndividuais
{
    private Map<Integer,CIndividual> individuais;
    
    public BDIndividuais (){
        this.individuais = new HashMap<Integer,CIndividual>();
    }
    
    public BDIndividuais(Map<Integer,CIndividual> a){
        setCindividual(a);
    }
    
    public BDIndividuais(BDIndividuais a){
        this.individuais = a.getCindividual();
    }
    
    public Map<Integer,CIndividual> getCindividual(){
        return this.individuais.entrySet()
                               .stream()
                               .collect(Collectors.toMap((e)->e.getKey(),
                                                         (e)->e.getValue().clone()));
    }
    
    public void setCindividual(Map<Integer,CIndividual> a){
        this.individuais = new HashMap <Integer,CIndividual>();
        a.values().stream().forEach(e -> this.individuais.put(e.getNif(),e.clone()));
    }
    
    public BDIndividuais clone(){
        return new BDIndividuais(this);
    }
    
    
}
