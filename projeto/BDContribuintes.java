
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
    
    
    /**
     * Contrutor sem argumentos da classe BDContribuintes.
     */
    public BDContribuintes (){
        this.dados = new HashMap<Integer,Contribuinte>();
    }
    
    /**
      * Construtor com argumentos.
      * @param a            Map de contribuintes a introduzir.
    */
    public BDContribuintes(Map<Integer,Contribuinte> a){
        setDados(a);
    }
    
    /**
      * Construtor da classe BDContribuintes com argumentos.
      * @param a  BDContribuintes a copiar.
    */
    public BDContribuintes(BDContribuintes a){
        this.dados = a.getDados();
    }
    
    
    /**
     * Método que devolve a base de dados de contribuinte.
     * @return base de dados de contribuinte
     */
    public Map<Integer,Contribuinte> getDados(){
        return this.dados.entrySet()
                         .stream()
                         .collect(Collectors.toMap((e)->e.getKey(),
                                                   (e)->e.getValue().clone()));
    }
    
    /**
     * Método que altera a base de dados de contribuinte.
     * @param a     Valor a inserir.
     */ 
    public void setDados(Map<Integer,Contribuinte> a){
        this.dados = new HashMap <Integer,Contribuinte>();
        a.values().stream().forEach(e -> this.dados.put(e.getNif(),e.clone()));
    }
    
    /**
     * Método que clona este objeto.
     * @return clone do objeto
     */
    public BDContribuintes clone(){
        return new BDContribuintes(this);
    }
    
    /**
     * Método toString do objeto.
     * @return objeto em modo string
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        for(Contribuinte f : this.dados.values())
            sb.append(f.toString() + "\n");
            
        return sb.toString();
    }
    
    /**
     * Método equal do objeto.
     * @return booelan que verifica se o objeto e igual
     */
    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        
        BDContribuintes aux = (BDContribuintes) object;
        Map<Integer,Contribuinte> aux1 = aux.getDados();
        
        return aux1.equals(this.dados);
    }
    
    /**
     * Método que devolve o contribuinte dado um nif, da throw a erro caso nao encontre.
     * @param nif   nif a procurar
     * @return coeficiente de deducao fiscal
     */
    public Contribuinte getContribuinte(int nif) throws ErroNotFound{
        Contribuinte aux = this.dados.get(nif);
        
        if (aux == null){
            Integer i = new Integer(nif);
            throw new ErroNotFound(i.toString());
        }
        return aux.clone();
    }
    
    /**
     * Método que adiciona um contribuinte na base de dados de contribuintes.
     * @param o    Contribuinte a adicionar
     */
    public void addContribuinte(Contribuinte o) throws ErroNotFound{
        Integer i = new Integer(o.getNif());
        
        if (this.dados.containsKey(o.getNif()))
            throw new ErroNotFound(i.toString());
            
        this.dados.put(o.getNif(),o.clone());
    }
    
    /**
     * Método que verifica se contem um dado nif numa base de dados.
     * @param nif   nif a procurar
     * @return boolean que representa se o nif encontra-se na base de dados.
     */
    public boolean contains(int nif){
        Contribuinte a = this.dados.get(nif);
        
        if (a == null)
            return false;
            
        return true;
    }
    
    /**
     * Método que altera a fatura com base num id.
     * @param id    id da fatura 
     * @param nif   nif do contribuinte
     */
    public void setFaturaId(int id,int nif){
        Contribuinte a = this.dados.get(nif);
        a.setFatura(id);

    }
    
    /**
     * Método que devolve o um map de <Nif,Faturas desse nif> inseridas na base de dados.
     * @return map de <Nif,Faturas desse Nif>
     */
    public Map<Integer,Set<Integer>> getFaturasIds(){
        Map<Integer,Set<Integer>> aux = new HashMap<>();

        this.dados.forEach((n,i) -> aux.put(n,i.getFaturas()));

        return aux;
    }
    
    /**
     * Método que adiciona um setor a um dado contribuinte.
     * @param s         Setor a adicionar
     * @param nif       Nif do contribuinte
     */
    public void addSetor(String s, int nif) throws ErroNotFound{
        Contribuinte aux = this.dados.get(nif);
        Integer i = new Integer(nif);
        if (aux == null)
            throw new ErroNotFound(i.toString());
        aux.addSetor(s);
    }
    
    /**
     * Método que adiciona um agregado a um dado contribuinte.
     * @param s         agregado a adicionar
     * @param nif       Nif do contribuinte
     */
    public void addAgregado(int nif, int nif_agregado) throws ErroNotFound{
        Contribuinte aux = this.dados.get(nif);
        Integer i = new Integer(nif);
        if (aux == null)
            throw new ErroNotFound(i.toString());
            
        if (aux.getClass().getSimpleName().equals("CIndividual")){
            CIndividual aux2 = (CIndividual) aux;
            aux2.addAgregado(nif_agregado);
        }
    }
    
    
    
    
}
