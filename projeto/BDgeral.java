import java.util.Set;
import java.util.Comparator;
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
import java.util.Collections;
import java.lang.ArithmeticException;

public class BDgeral
{
    private BDContribuintes empresas;
    private BDContribuintes individuais;
    private BDFaturas faturas;
    private BDSetores setores;
    
    
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
        this.empresas = new BDContribuintes();
        this.individuais = new BDContribuintes();
        this.faturas = new BDFaturas();
        this.setores = new BDSetores();
    }
    

    
    public BDgeral(BDContribuintes a,BDContribuintes b,BDFaturas c, BDSetores d){
        this.empresas       = a.clone();
        this.individuais    = b.clone();
        this.faturas        = c.clone();
        this.setores        = d.clone();
    }
    
    public BDgeral(BDgeral a){
        this.empresas       = a.getBDEmpresas();
        this.individuais    = a.getBDIndividuais();
        this.faturas        = a.getBDFaturas();
        this.setores        = a.getBDSetores();
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
    
    
    public BDContribuintes getBDEmpresas(){
        return this.empresas.clone();
    }
    
    public BDContribuintes getBDIndividuais(){
        return this.individuais.clone();
    }
    
    public BDFaturas getBDFaturas(){
        return this.faturas.clone();
    }

    public BDSetores getBDSetores() {
        return this.setores.clone();
    }
    
    public void addIndividual(CIndividual i){
        try{
            this.individuais.addContribuinte(i);
        }
        catch (Erros l){
            System.out.println("Contribuinte " + l.getMessage() + " já inserido");
        }
    }
    
    public void addEmpresa(Empresa i){
        try{
            this.empresas.addContribuinte(i);
        }
        catch (Erros l){
            System.out.println("Contribuinte " + l.getMessage() + " já inserido");
        }

    }
    
    public void addFatura(Fatura i){
        this.faturas.addFatura(i,this.individuais,this.empresas,this.setores);
    }

    public void addSetor(Setor s){this.setores.addSetor(s);}
    
    public Empresa getEmpresa(int nif) throws Erros{
        Empresa aux;
        Integer i = new Integer(nif);
        
        try{
            aux = (Empresa) this.empresas.getContribuinte(nif);
        }
        catch (Erros l){
            throw new Erros(i.toString());
        }
        
        return aux;
    }
    
    public CIndividual getCIndividual(int nif) throws Erros{
        CIndividual aux;
        Integer i = new Integer(nif);
        
        try{
            aux = (CIndividual) this.individuais.getContribuinte(nif);
        }
        catch (Erros l){
            throw new Erros(i.toString());
        }
        
        return aux;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.individuais.toString() +"\n");
        sb.append(this.empresas.toString() + "\n");
        sb.append(this.faturas.toString() + "\n");
        sb.append(this.setores.toString() + "\n");
        
        return sb.toString();
    }
    
    public double deduz(int id){ //falta contabilizar o número de elementos do agregado familiar (quando isso estivr pronto)
        String setor = this.faturas.getFaturas().get(id).getCategoria();
        double taxa = this.setores.getSetores().get(setor).getTaxa();
        taxa *= this.faturas.getFaturas().get(id).getValor();

        return taxa;
    }


    //7 true ordena por tempo false por valor
    public List<Fatura> listagem_ordenada_emp_fatura(LocalDate start,LocalDate end, boolean type, int id){
        Empresa e;
        try {
            e = (Empresa) this.empresas.getContribuinte(id);
        }
        catch (Erros aux){
            System.out.println("Empresa " + aux.getMessage() + " nao encontrada");
            return new ArrayList<Fatura>();
        }
        
        List<Fatura> list = this.faturas.faturas_no_intervalo(start,end,e.getFaturas());
        
        TreeSet<Fatura> ordena_aux;
        
        if (type){
            ordena_aux = new TreeSet<>(new CompFatTime());
            list.forEach( a -> ordena_aux.add(a));
            list = ordena_aux.stream().collect(Collectors.toList());
        }
        else {
            ordena_aux = new TreeSet<>(new CompValor());
            list.forEach( a -> ordena_aux.add(a));
            list = ordena_aux.stream().collect(Collectors.toList());
            
        }
        
        return list;
    }
    
    //8
    public Map<Integer,List<Fatura>> listagem_cont_fatura_time(LocalDate start,LocalDate end, int id){
        Empresa e;
        Map <Integer,List<Fatura>> listagem = new HashMap<>();
        try {
            e = (Empresa) this.empresas.getContribuinte(id);
        }
        catch (Erros aux){
            System.out.println("Empresa " + aux.getMessage() + " nao encontrada");
            return listagem;
        }
        
        List<Fatura> list = this.faturas.faturas_no_intervalo(start,end,e.getFaturas());
        List<Fatura> aux1;
        
        for(Fatura f : list){
            if (listagem.containsKey(f.getNif_cliente())){
                aux1 = new ArrayList<>();
                listagem.put(f.getNif_cliente(),aux1);
            }
            aux1 = listagem.get(f.getNif_cliente());
            aux1.add(f);
        }
        
        return listagem;
    }
    
    //9
    
    public Map<Integer,List<Fatura>> listagem_cont_fatura(int id){
        Empresa e;
        Map <Integer,List<Fatura>> listagem = new HashMap<>();
        try {
            e = (Empresa) this.empresas.getContribuinte(id);
        }
        catch (Erros aux){
            System.out.println("Empresa " + aux.getMessage() + " nao encontrada");
            return listagem;
        }
        
        List<Fatura> list = this.faturas.faturas_contribuinte(e.getFaturas());
        List<Fatura> aux1;
        
        for(Fatura f : list){
            if (listagem.containsKey(f.getNif_cliente())){
                aux1 = new ArrayList<>();
                listagem.put(f.getNif_cliente(),aux1);
            }
            aux1 = listagem.get(f.getNif_cliente());
            aux1.add(f);
        }
        
        
        for(List<Fatura> a : listagem.values()){
           a.sort(new CompValor());
        }
        
        return listagem;
    }
    //10
    public double total_faturado(LocalDate start, LocalDate end ,int id){
        Empresa e;
        try {
            e = (Empresa) this.empresas.getContribuinte(id);
        }
        catch (Erros aux){
            System.out.println("Empresa " + aux.getMessage() + " nao encontrada");
            return 0;
        }
        
        List<Fatura> list = this.faturas.faturas_no_intervalo(start,end,e.getFaturas());
        
        return list.stream()
                   .mapToDouble( b -> b.getValor())
                   .sum();
    }
    //11
    public double rel_top10() throws ArithmeticException{
        Map <Integer,List<Fatura>> listagem = new HashMap<>();
        
        List<Fatura> aux = new ArrayList<>();
        
        for(Fatura a : faturas.getFaturas().values()){
            if (!listagem.containsKey(a.getNif_cliente())){
                aux = new ArrayList<>();
                listagem.put(a.getNif_cliente(),aux);
            }
            aux = listagem.get(a.getNif_cliente());
            aux.add(a);
        }
        
        TreeSet <Double> aux2 = new TreeSet <Double>(new Comparator<Double>(){
                
                public int compare(Double f1,Double f2){
                    if (f2 == f1)
                        return 0;
                    return f2 > f1 ? -1 : 1;
                }
            });
        
        listagem.forEach((k,v) -> aux2.add(v.stream().mapToDouble(b -> b.getValor()).sum()));
        
        
            
        int i = 0;
        double top10_total = 0;
        double total = 0;
        
        for(Double k : aux2){
            if (i < 10){
                i++;
                top10_total += k;
            }
            total += k;
        }
        
        if (total == 0){
            throw new ArithmeticException();
        }
        
        return top10_total / total;
    }
    
}
