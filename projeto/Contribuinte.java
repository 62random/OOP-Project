
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Contribuinte
 */
public class Contribuinte
{
    private int nif;
    private String email;
    private String nome;
    private String morada;
    private String password;
    private List<Integer> faturas;
    
    
    /**
      * Construtor sem argumentos.
    */
    
    public Contribuinte(){
        this.nif = -1;
        this.email = "";
        this.nome = "";
        this.morada = "";
        this.password = "";
        this.faturas = new ArrayList<Integer>();
        
    }

    /**
      * Construtor com argumentos.
      * @param nif1 	    Nif a introduzir.
      * @param nemail 	    Email a introduzir.
      * @param nnome  	    Nome a introduzir.
      * @param nmorada 	    Morada a introduzir.
      * @param npassword    Password a introduzir.
    */
    
    public Contribuinte(int nif1,String nemail,String nnome,String nmorada,String npassword,List<Integer> faturas){
        this.nif = nif1;
        this.email = nemail;
        this.nome = nnome;
        this.morada = nmorada;
        this.password = npassword;
        setFaturas(faturas);
    }

    /**
      * Construtor com argumentos.
      * @param a  Contribuinte a copiar.
    */
    
    public Contribuinte(Contribuinte a){
        this.nif = a.getNif();
        this.email = a.getEmail();
        this.nome = a.getNome();
        this.morada = a.getMorada();
        this.password = a.getPassword();
        this.faturas = a.getFaturas();
    }

    /**
      * Get para a variável nif do objeto.
    */
    
    public int getNif(){
        return this.nif;
    }

    /**
      * Get para a variável email do objeto.
    */
    
    public String getEmail(){
        return this.email;
    }

    /**
      * Get para a variável Nome do objeto.
    */
	
    public String getNome(){
        return this.nome;
    }

    /**
      * Get para a variável morada do objeto.
    */
	
    public String getMorada(){
        return this.morada;
    }

    /**
      * Get para a variável password do objeto.
    */
    
    public String getPassword(){ /*Acho que isto não devia ter get, mas em vez disso uma função que recebe uma string e retorna um booleano (caso a string coincida com a pass)*/
        return this.password;
    }
    
    public List<Integer> getFaturas(){
        List<Integer> nova = new ArrayList<>();
        Iterator <Integer> i = this.faturas.iterator();
        int aux;
        
        while(i.hasNext()){
            aux = i.next();
            nova.add(aux);
        }
        
        return nova;
    }

    /**
      * Método clone do objeto Contribuinte.
    */
    
    public Contribuinte clone(){
        return new Contribuinte(this);
    }

    /**
      * Método toString do objeto Contribuinte.
    */
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("Nif: ");
        sb.append(this.nif+"\n");
        sb.append("Nome: ");
        sb.append(this.nome+"\n");
        sb.append("Morada: ");
        sb.append(this.morada+"\n");
        sb.append("Email: ");
        sb.append(this.email+"\n");
        sb.append("Password: ");
        sb.append(this.password+"\n");
        
        return sb.toString();
    }

    /**
       * Método set da variável nif.
       * @param a valor a introduzir.
    */
    
    public void setNif(int a){
        this.nif = a;
    }

	/**
       * Método set da variável email.
	   * @param e valor a introduzir.
   	*/
	
    public void setEmail(String e){
        this.email = e;
    }

	/**
	   * Método set da variável nome.
	   * @param e valor a introduzir.
	*/
	
	
    public void setNome(String e){
        this.nome = e;
    }

	/**
	   * Método set da variável morada.
	   * @param m valor a introduzir.
	*/
	
	
    public void setMorada(String m){
        this.morada = m;
    }

	/**
	   * Método set da variável password.
	   * @param p valor a introduzir.
	*/
	
	
    public void setPassword(String p){
        this.password = p;
	}
	
    public void setFaturas(List<Integer> a){
        this.faturas = new ArrayList<Integer>();
        Iterator <Integer> i = a.iterator();
        int aux;
        
        while(i.hasNext()){
            aux = i.next();
            this.faturas.add(aux);
        }
    }

	/**
	   * Método equals para a classe Contribuinte.
	   * @param o objeto a comparar.
	*/

    public boolean equals(Object o){
        if(this == o)
            return true;
        
        if (o == null || (this.getClass() != o.getClass()))
            return false;
            
        Contribuinte aux = (Contribuinte) o;
        
        return aux.getNif()==this.nif
            && aux.getNome().equals(this.nome)
            && aux.getMorada().equals(this.morada)
            && aux.getEmail().equals(this.email)
            && aux.getPassword().equals(this.password)
            && aux.getFaturas().equals(this.faturas);
    }
    
    public int hashCode(){
        int hash = 7;
        
        hash = hash*31 + this.nif;
        hash = hash*31 + this.email.hashCode();
        hash = hash*31 + this.nome.hashCode();
        hash = hash*31 + this.morada.hashCode();
        hash = hash*31 + this.password.hashCode();
        hash = hash*31 + this.faturas.hashCode();
        
        
        return hash;
    }
    
    
    
}