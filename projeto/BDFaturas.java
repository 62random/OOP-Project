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

public class BDFaturas
{
    private Map<Integer,Fatura> faturas;

    public BDFaturas (){
        this.faturas = new HashMap<Integer,Fatura>();
    }
    
    public BDFaturas(Map<Integer,Fatura> a){
        setFaturas(a);
    }
    
    public BDFaturas(BDFaturas a){
        this.faturas = a.getFaturas();
    }
    
    public Map<Integer,Fatura> getFaturas(){
        return this.faturas.values()
                           .stream()
                           .collect(Collectors.toMap((e)->e.getId(),
                                                     (e)->e.clone()));
    }
    
    public void setFaturas(Map<Integer,Fatura> a){
        this.faturas = new HashMap <Integer,Fatura>();
        a.values().stream().forEach(e -> this.faturas.put(e.getId(),e.clone()));
    }
    
    public BDFaturas clone(){
        return new BDFaturas(this);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        for(Fatura f : this.faturas.values())
            sb.append(f.toString() + "\n");
            
        return sb.toString();
    }
    
    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        
        BDFaturas aux = (BDFaturas) object;
        Map<Integer,Fatura> aux1 = aux.getFaturas();
        
        return aux1.equals(this.faturas);
    }

        
    public void addFatura(Fatura a, BDIndividuais i, BDEmpresas e){        
        if (i.contains(a.getNif_cliente()) && e.contains(a.getNif_emitente())){
            i.setFaturaId(a.getId(),a.getNif_cliente());
            e.setFaturaId(a.getId(),a.getNif_emitente());
            this.faturas.put(a.getId(),a.clone());
        }
    }
}
