import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

public class BDgeral
{
    private BDEmpresas empresas;
    private BDIndividuais individuais;
    private BDFaturas faturas;
    
    
    public static void createFile(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                FileOutputStream writer = new FileOutputStream(path);
                writer.write(("").getBytes());
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public BDgeral(){
        this.empresas = new BDEmpresas();
        this.individuais = new BDIndividuais();
        this.faturas = new BDFaturas();
    }
    
    public BDgeral(BDEmpresas a,BDIndividuais b,BDFaturas c){
        this.empresas = a.clone();
        this.individuais = b.clone();
        this.faturas = c.clone();
    }
    
    public BDgeral(BDgeral a){
        this.empresas = a.getBDEmpresas();
        this.individuais = a.getBDIndividuais();
        this.faturas = a.getBDFaturas();
    }
    
    public void guardaEstado(String nome) throws FileNotFoundException ,IOException{
      //  createFile(nome);
        FileOutputStream fos = new FileOutputStream(nome);

        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }
    
    public BDgeral carregaEstado(String nome) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(nome);
        ObjectInputStream ois = new ObjectInputStream(fis);
        BDgeral h = (BDgeral) ois.readObject();
        ois.close();
        return h;
    }
    
    
    public BDEmpresas getBDEmpresas(){
        return this.empresas.clone();
    }
    
    public BDIndividuais getBDIndividuais(){
        return this.individuais.clone();
    }
    
    public BDFaturas getBDFaturas(){
        return this.faturas.clone();
    }
    
    public void addIndividual(CIndividual i){
        this.individuais.addContribuinte(i);
    }
    
    public void addEmpresa(Empresa i){
        this.empresas.addContribuinte(i);
    }
    
    public void addFatura(Fatura i){
        this.faturas.addFatura(i,this.individuais,this.empresas);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.individuais.toString() +"\n");
        sb.append(this.empresas.toString() + "\n");
        sb.append(this.faturas.toString() + "\n");
        
        return sb.toString();
    }
    
    
}
