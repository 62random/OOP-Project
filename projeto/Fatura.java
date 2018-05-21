import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;


public class Fatura {
    private int id;
    private int nif_emitente;
    private int nif_cliente;
    private double valor;
    private String nome_emitente;
    private String categoria;
    private String descricao;
    private Set<String> old_desc;
    private LocalDate emissao;
    
    private static int nextid = 0;

    public Fatura(){
        this.id             = nextid;
        this.nif_emitente   = 0;
        this.nif_cliente    = 0;
        this.valor          = 0;
        this.nome_emitente  = "";
        this.descricao      = "";
        this.categoria      = "";
        this.old_desc       = new HashSet<String>();
        this.emissao        = null;
        
        nextid++;
    }

    public Fatura(int nif_emitente, String nome_emitente, LocalDate emissao, int nif_cliente, String descricao, String categoria, double valor) {
        this.id             = nextid;
        this.nif_emitente   = nif_emitente;
        this.nome_emitente  = nome_emitente;
        this.emissao        = emissao;
        this.nif_cliente    = nif_cliente;
        this.descricao      = descricao;
        this.categoria      = categoria;
        this.old_desc       = new HashSet<String>();
        this.valor          = valor;
        
        nextid++;
    }

    public Fatura(Fatura f) {
        this.id             = f.getId();
        this.nif_emitente   = f.getNif_emitente();
        this.nome_emitente  = f.getNome_emitente();
        this.emissao        = f.getEmissao();
        this.nif_cliente    = f.getNif_cliente();
        this.descricao      = f.getDescricao();
        this.categoria      = f.getCategoria();
        this.old_desc       = f.getOld_desc();
        this.valor          = f.getValor();
    }
    
    public Set<String> getOld_desc(){
        Set<String> aux = new HashSet <String>();
        this.old_desc.forEach(a -> aux.add(a));
        
        return aux;
    }

    public int getId() {
        return id;
    }

    public int getNif_cliente() {
        return nif_cliente;
    }

    public int getNif_emitente() {
        return nif_emitente;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getEmissao() {
        return emissao;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome_emitente() {
        return nome_emitente;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDescricao(String descricao) {
        this.old_desc.add(this.descricao);
        this.descricao = descricao;
    }

    public void setEmissao(LocalDate emissao) {
        this.emissao = emissao;
    }

    public void setNif_cliente(int nif_cliente) {
        this.nif_cliente = nif_cliente;
    }

    public void setNif_emitente(int nif_emitente) {
        this.nif_emitente = nif_emitente;
    }

    public void setNome_emitente(String nome_emitente) {
        this.nome_emitente = nome_emitente;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Fatura fatura = (Fatura) object;
        
        Set<String> aux = fatura.getOld_desc();
        if (aux.size() != this.old_desc.size())
            return false;
        
        for(String a : aux)
            if (!this.old_desc.contains(a))
                return false;
            
        return  this.id              == fatura.getId()           &&
                this.nif_emitente    == fatura.getNif_emitente() &&
                this.nif_cliente     == fatura.getNif_cliente()  &&
                this.valor           == fatura.getValor()        &&
                this.descricao       == fatura.getDescricao()    &&
                this.categoria       == fatura.getCategoria()    &&
                this.nome_emitente   == fatura.getNome_emitente()&&
                this.emissao.equals(fatura.getEmissao())         ;
    }

    public Fatura clone(){
        return new Fatura(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Id: ");
        sb.append(this.id);
        sb.append("\n");
        sb.append("Emitente: ");
        sb.append(this.nome_emitente);
        sb.append("\n");
        sb.append("NIF do emitente: ");
        sb.append(this.nif_emitente);
        sb.append("\n");
        sb.append("NIF do cliente: ");
        sb.append(this.nif_cliente);
        sb.append("\n");
        sb.append("Valor da transação: ");
        sb.append(this.valor);
        sb.append("\n");
        sb.append("Data de emissão: ");
        sb.append(this.emissao);
        sb.append("\n");
        sb.append("Categoria: ");
        sb.append(this.categoria);
        sb.append("\n");
        sb.append("Descrição: ");
        sb.append(this.descricao);
        sb.append("\n");

        return sb.toString();
    }
    
    public int hashCode(){
        int hash = 7;
        long aux;
        
        aux = Double.doubleToLongBits(this.valor);
        hash = hash*31 + this.id;
        hash = hash*31 + this.nif_emitente;
        hash = hash*31 + this.nif_cliente;
        hash = hash*31 + (int)(aux^(aux >>> 32));
        hash = hash*31 + this.nome_emitente.hashCode();
        hash = hash*31 + this.categoria.hashCode();
        hash = hash*31 + this.descricao.hashCode();
        hash = hash*31 + this.emissao.hashCode();
        
        for(String a : this.old_desc){
            hash = hash*31 + a.hashCode();
        }
        
        return hash;
    }
    
    public int compareTo(Fatura h) {
        return h.getId() - this.id;
    }


}

