
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;

public class BDContribuintes implements Serializable
{
    private Map<Integer,Contribuinte> dados;
    
    public BDContribuintes (){
        this.dados = new HashMap<Integer,Contribuinte>();
    }
    
    public BDContribuintes(Map<Integer,Contribuinte> a){
        setDados(a);
    }
    
    public BDContribuintes(BDContribuintes a){
        this.dados = a.getDados();
    }
    
    public Map<Integer,Contribuinte> getDados(){
        return this.dados.entrySet()
                         .stream()
                         .collect(Collectors.toMap((e)->e.getKey(),
                                                   (e)->e.getValue().clone()));
    }
    
    public void setDados(Map<Integer,Contribuinte> a){
        this.dados = new HashMap <Integer,Contribuinte>();
        a.values().stream().forEach(e -> this.dados.put(e.getNif(),e.clone()));
    }
    
    public BDContribuintes clone(){
        return new BDContribuintes(this);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        for(Contribuinte f : this.dados.values())
            sb.append(f.toString() + "\n");
            
        return sb.toString();
    }
    
    
    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        
        BDContribuintes aux = (BDContribuintes) object;
        Map<Integer,Contribuinte> aux1 = aux.getDados();
        
        return aux1.equals(this.dados);
    }
    
    
    public Contribuinte getContribuinte(int nif) throws ErroNotFound{
        Contribuinte aux = this.dados.get(nif);
        
        if (aux == null){
            Integer i = new Integer(nif);
            throw new ErroNotFound(i.toString());
        }
        return aux.clone();
    }
    
    public void addContribuinte(Contribuinte o) throws ErroNotFound{
        Integer i = new Integer(o.getNif());
        
        if (this.dados.containsKey(o.getNif()))
            throw new ErroNotFound(i.toString());
            
        this.dados.put(o.getNif(),o.clone());
    }
    
    public boolean contains(int nif){
        Contribuinte a = this.dados.get(nif);
        
        if (a == null)
            return false;
            
        return true;
    }
    
    public void setFaturaId(int id,int nif){
        Contribuinte a = this.dados.get(nif);
        a.setFatura(id);

    }

    public Map<Integer,Set<Integer>> getFaturas(){
        Map<Integer,Set<Integer>> aux = new HashMap<>();

        this.dados.forEach((n,i) -> aux.put(n,i.getFaturas()));

        return aux;
    }
    
    
    
    
}
