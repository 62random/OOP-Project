
public class Setor {
    private int id;
    private String nome;
    private double taxa;

    private static int nextid = 0;

    public Setor(){
        this.id     = nextid++;
        this.nome   = "";
        this.taxa   = 0;
    }

    public Setor(int id, String nome, double taxa) {
        this.id     = nextid++;
        this.nome   = nome;
        this.taxa   = taxa;
    }

    public Setor(Setor setor){
        this.id     = setor.id;
        this.nome   = setor.nome;
        this.taxa   = setor.taxa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return  this.getId() == setor.getId() &&
                this.getNome() == setor.getNome() &&
                this.getTaxa() == setor.getTaxa();
    }

    public int hashCode() {

        int hash = 7;
        long aux;

        aux = Double.doubleToLongBits(this.taxa);
        hash = hash*31 + this.id;
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
        sb.append("Id: ");
        sb.append(this.getId());
        sb.append("\n");
        sb.append("Taxa: ");
        sb.append(this.getTaxa());
        sb.append("}\n");

        return sb.toString();
    }

}
