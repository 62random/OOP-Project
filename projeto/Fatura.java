import java.time.LocalDateTime;


public class Fatura {
    private int nif_emitente;
    private int nif_cliente;
    private int valor;
    private String nome_emitente;
    private String categoria;
    private String descricao;
    private LocalDateTime emissao;

    public Fatura(){
        this.nif_emitente   = 0;
        this.nif_cliente    = 0;
        this.valor          = 0;
        this.nome_emitente  = "";
        this.descricao      = "";
        this.categoria      = "";
        this.emissao        = null;
    }

    public Fatura(int nif_emitente, String nome_emitente, LocalDateTime emissao, int nif_cliente, String descricao, String categoria, int valor) {
        this.nif_emitente   = nif_emitente;
        this.nome_emitente  = nome_emitente;
        this.emissao        = emissao;
        this.nif_cliente    = nif_cliente;
        this.descricao      = descricao;
        this.categoria      = categoria;
        this.valor          = valor;
    }

    public Fatura(Fatura f) {
        this.nif_emitente   = f.nif_emitente;
        this.nome_emitente  = f.nome_emitente;
        this.emissao        = f.emissao;
        this.nif_cliente    = f.nif_cliente;
        this.descricao      = f.descricao;
        this.categoria      = f.categoria;
        this.valor          = f.valor;
    }

    public int getNif_cliente() {
        return nif_cliente;
    }

    public int getNif_emitente() {
        return nif_emitente;
    }

    public int getValor() {
        return valor;
    }

    public LocalDateTime getEmissao() {
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
        this.descricao = descricao;
    }

    public void setEmissao(LocalDateTime emissao) {
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

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Fatura fatura = (Fatura) object;
        return  this.nif_emitente    == fatura.getNif_emitente() &&
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


}

