import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;
import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class BDSetores implements Serializable {
    private Map<String, Setor> setores;
    private Set<String> por_aprovar;

    public BDSetores() {
        this.setores = new HashMap<String, Setor>();
        this.por_aprovar = new HashSet<>();
    }

    public BDSetores(Map<String,Setor> set) {
        this.setores = set.entrySet().stream()
                                     .collect(Collectors.toMap(entry->entry.getKey().toUpperCase(),entry->entry.getValue().clone()));
        this.por_aprovar = new HashSet<>();
    }

    public BDSetores(BDSetores bdSetores){
        this.setores = bdSetores.getSetores();
        this.por_aprovar = bdSetores.getAprov();
    }


    public Map<String, Setor> getSetores(){
        return this.setores.entrySet().stream()
                                      .collect(Collectors.toMap(entry->entry.getKey().toUpperCase(),entry->entry.getValue().clone()));
    }

    public void setSetores(Map<String, Setor> setores) {
        this.setores = new HashMap<String, Setor>();
        setores.entrySet().forEach(entry-> this.setores.put(entry.getValue().getNome().toUpperCase(), entry.getValue().clone()));
    }
    
    public Set<String> getAprov(){
        return this.por_aprovar.stream().collect(Collectors.toSet());
    }

    public BDSetores clone(){
        return new BDSetores(this);
    }

    public void addSetor(Setor s){
        this.setores.put(s.getNome().toUpperCase(), s.clone());
        this.por_aprovar.add(s.getNome().toUpperCase());
    }

    public boolean existeSetor(String s){
        return this.setores.containsKey(s.toUpperCase());
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BDSetores bdSetores = (BDSetores) o;
        return this.setores.equals(bdSetores.setores);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for(Setor s : this.setores.values())
            sb.append(s.toString() + "\n");
            
        sb.append("Setores por aprovar: ");
        for(String a : this.por_aprovar){
            sb.append(a + ": ");
        }
        sb.append("\n");

        return sb.toString();
    }
    
    public void admin_aprov() throws AdminAprov{
        if (this.por_aprovar.size() == 0)
            throw new AdminAprov();
            
        Scanner in = new Scanner(System.in);
        System.out.println("Aprovar Setores");
        
        double aux = -1;
        for(String a : this.por_aprovar){
            System.out.println("Setor: " + a + " Valor:");
            while(aux == -1){
                try{
                    aux = in.nextDouble();
                }
                catch (InputMismatchException e){
                    System.out.println("Valor inválido");
                    in.next();
                }
                if (aux < 0 || aux > 0.23){
                    aux = -1;
                    System.out.println("Valor inválido. Por favor insira um valor entre 0 e 0,23");
                }
                else{
                    Setor s = this.setores.get(a);
                    s.setTaxa(aux);
                }
            }
        }
        this.por_aprovar = new HashSet<>();
    }
}
