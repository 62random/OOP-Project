import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

public class BDSetores {
    private Map<String, Setor> setores;

    public BDSetores() {
        this.setores = new HashMap<String, Setor>();
    }

    public BDSetores(Map<String, Setor> setores) {
        this.setores = setores;
    }

    public BDSetores(BDSetores bdSetores){
        this.setores = (Map) bdSetores.getSetores();
    }


    public Map<String, Setor> getSetores(){
        return this.setores.entrySet().stream()
                                        .collect(Collectors.toMap(entry->entry.getKey(),entry->entry.getValue().clone()));
    }

    public void setSetores(Map<String, Setor> setores) {
        this.setores = new HashMap<String, Setor>();
        setores.entrySet().stream().forEach(entry-> this.setores.put(entry.getValue().getNome(), entry.getValue().clone()));
    }

    public BDSetores clone(){
        return new BDSetores(this);
    }

    public void addSetor(Setor s){
        this.setores.put(s.getNome(), s.clone());
    }

    public boolean existeSetor(String s){
        return this.setores.containsKey(s);
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

        return sb.toString();
    }
}
