
public interface BaseFunc
{
    public Contribuinte getContribuinte(int nif) throws Erros;
    
    public void addContribuinte(Contribuinte o);
    
    public void setFaturaId(int id,int cliente);
    
    public boolean contains(int nif);
}
