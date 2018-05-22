import java.io.Serializable;


public class Setor implements Serializable {
    private String nome;
    private double taxa;

    public Setor(){
        this.nome   = "";
        this.taxa   = 0;
    }

    public Setor(String nome, double taxa) {
        this.nome   = nome;
        this.taxa   = taxa;
    }

    public Setor(Setor setor){
        this.nome   = setor.nome;
        this.taxa   = setor.taxa;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Setor setor = (Setor) o;
        return  this.getNome() == setor.getNome() &&
                this.getTaxa() == setor.getTaxa();
    }

    public int hashCode() {

        int hash = 7;
        long aux;

        aux = Double.doubleToLongBits(this.taxa);

        hash = hash*31 + this.nome.hashCode();
        hash = 31*hash + (int)(aux^(aux >>> 32));

        return hash;
    }

    public Setor clone(){
        return new Setor(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Setor{ \nNome: ");
        sb.append(this.getNome());
        sb.append("\n");
        sb.append("Taxa: ");
        sb.append(this.getTaxa());
        sb.append("}\n");

        return sb.toString();
    }

}
