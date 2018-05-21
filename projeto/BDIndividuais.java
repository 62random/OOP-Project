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
import java.io.*;


public class BDIndividuais implements BaseFunc,Serializable 
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
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        for(CIndividual f : this.individuais.values())
            sb.append(f.toString() + "\n");
            
        return sb.toString();
    }
    
    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        
        BDIndividuais aux = (BDIndividuais) object;
        Map<Integer,CIndividual> aux1 = aux.getCindividual();
        
        return aux1.equals(this.individuais);
    }
    
    public Contribuinte getContribuinte(int nif) throws Erros{
        Contribuinte aux = this.individuais.get(nif);
        if (aux == null){
            throw new Erros("Contribuinte não encontrado");
        }
        return aux.clone();
    }
    
    public void addContribuinte(Contribuinte o){
        CIndividual a = (CIndividual) o;
        this.individuais.put(a.getNif(),a.clone());
    }
    
    public boolean contains(int nif){
        CIndividual a = this.individuais.get(nif);
        if (a == null)
            return false;
        return true;
    }
    
    public void setFaturaId(int id,int nif){
        CIndividual a = this.individuais.get(nif);
        a.setFatura(id);

    }
    
}
