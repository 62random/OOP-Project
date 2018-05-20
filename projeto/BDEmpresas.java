
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

public class BDEmpresas implements BaseFunc
{
    private Map<Integer,Empresa> empresas;
    
    public BDEmpresas (){
        this.empresas = new HashMap<Integer,Empresa>();
    }
    
    public BDEmpresas(Map<Integer,Empresa> a){
        setEmpresas(a);
    }
    
    public BDEmpresas(BDEmpresas a){
        this.empresas = a.getEmpresas();
    }
    
    public Map<Integer,Empresa> getEmpresas(){
        return this.empresas.entrySet()
                               .stream()
                               .collect(Collectors.toMap((e)->e.getKey(),
                                                         (e)->e.getValue().clone()));
    }
    
    public void setEmpresas(Map<Integer,Empresa> a){
        this.empresas = new HashMap <Integer,Empresa>();
        a.values().stream().forEach(e -> this.empresas.put(e.getNif(),e.clone()));
    }
    
    public BDEmpresas clone(){
        return new BDEmpresas(this);
    }
    
        public String toString(){
        StringBuilder sb = new StringBuilder();
        
        for(Empresa f : this.empresas.values())
            sb.append(f.toString() + "\n");
            
        return sb.toString();
    }
    
    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        
        BDEmpresas aux = (BDEmpresas) object;
        Map<Integer,Empresa> aux1 = aux.getEmpresas();
        
        return aux1.equals(this.empresas);
    }
    
    public Contribuinte getContribuinte(int nif) throws Erros{
        Contribuinte aux = this.empresas.get(nif);
        if (aux == null){
            throw new Erros("Contribuinte não encontrado");
        }
        return aux.clone();
    }
    
    public void addContribuinte(Contribuinte o){
        Empresa a = (Empresa) o;
        this.empresas.put(a.getNif(),a.clone());
    }
    
    public boolean contains(int nif){
        Empresa a = this.empresas.get(nif);
        if (a == null)
            return false;
        return true;
    }
    
    public void setFaturaId(int id,int nif){
        Empresa a = this.empresas.get(nif);
        a.setFatura(id);
    }
    
    
}
