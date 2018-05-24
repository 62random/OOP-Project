import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.io.*;
import java.util.Scanner;


public class BDFaturas implements Serializable
{
    private Map<Integer,Fatura> faturas;
    private Set<Integer> faturas_porval;

    public BDFaturas (){
        this.faturas = new HashMap<Integer,Fatura>();
        this.faturas_porval = new HashSet<Integer>();
    }
    
    public BDFaturas(Map<Integer,Fatura> a){
        setFaturas(a);
        this.faturas_porval = new HashSet<Integer>();
    }
    
    public BDFaturas(BDFaturas a){
        this.faturas_porval = a.getFaturas_porval();
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
    
    public Set<Integer> getFaturas_porval(){
        Set<Integer> aux = new HashSet<Integer>();
        this.faturas_porval.forEach( a -> aux.add(a));
        
        return aux;
    }
    
    public BDFaturas clone(){
        return new BDFaturas(this);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        for(Fatura f : this.faturas.values())
            sb.append(f.toString() + "\n");
            
        sb.append("Por validar: ");
        for(Integer i : this.faturas_porval)
            sb.append(i + ": ");
            
        sb.append("\n");
            
        return sb.toString();
    }
    
    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        
        BDFaturas aux = (BDFaturas) object;
        Map<Integer,Fatura> aux1 = aux.getFaturas();
        
        return aux1.equals(this.faturas);
    }

        
    public void addFatura(Fatura a, BDContribuintes i, BDContribuintes e, BDSetores d){
        Empresa aux;
        CIndividual aux2;
           
            
        try {
             aux = (Empresa) e.getContribuinte(a.getNif_emitente());
             aux2 = (CIndividual) i.getContribuinte(a.getNif_cliente());
        }
        catch (ErroNotFound l){
             System.out.println("Contribuinte" +  l.getMessage() +"não existe\n");
             return;
        }
            
        i.setFaturaId(a.getId(),a.getNif_cliente());
        e.setFaturaId(a.getId(),a.getNif_emitente());
            
        if (aux.getSetores().size() > 1){
            this.faturas_porval.add(a.getId());
            
        }
        else if (aux.getSetores().size()==1){
            if(!d.existeSetor(a.getCategoria()))
                d.addSetor(new Setor(a.getCategoria(), 0));
        }
            
        this.faturas.put(a.getId(),a.clone());
    }

    public Fatura getFatura(int id) throws ErroNotFound {
        Fatura a;

        a = this.faturas.get(id);
        Integer i = new Integer(id);

        if (a == null)
            throw new ErroNotFound (i.toString());

        return a;
    }
    
    public List<Fatura> faturas_no_intervalo(LocalDate start ,LocalDate end, Set<Integer> idlist){
        return this.faturas.values().stream()
                                    .filter(b -> idlist.contains(b.getId()) 
                                    && b.getEmissao().isAfter(start)
                                    && b.getEmissao().isBefore(end))
                                    .map( b -> b.clone())
                                    .collect(Collectors.toList());
    }
    
    public List<Fatura> faturas_contribuinte(Set<Integer> idlist){
        return this.faturas.values().stream()
                                    .filter( b -> idlist.contains(b.getId()))
                                    .map( b -> b.clone())
                                    .collect(Collectors.toList());
    }
    
    public boolean check_val_fatura(int id){
        if (this.faturas_porval.contains(id))
            return false;
        return true;
    }
    
    public void valida_fatura(int id,String setor,BDSetores set) throws ErroNotFound,FaturaVal{
        Fatura a = this.faturas.get(id);
        Integer i = new Integer(id);
        if (a == null)
            throw new ErroNotFound(i.toString());
        if (!this.faturas_porval.contains(id))
            throw new FaturaVal(i.toString());
            
        a.setCategoria(setor);
        this.faturas_porval.remove(id);
        
        if(!set.existeSetor(a.getCategoria()))
                set.addSetor(new Setor(a.getCategoria(), 0));
    }
}
