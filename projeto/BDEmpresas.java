
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

public class BDEmpresas
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
    
}
