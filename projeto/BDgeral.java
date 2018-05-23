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
import java.util.Collections;
import java.io.*;
import java.lang.ArithmeticException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class BDgeral implements Serializable
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
        File f = new File(nome);
        if(!f.exists()){
            f.createNewFile();
        }
        //createFile(nome);
        FileOutputStream fos = new FileOutputStream(f);

        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }
    
    public void carregaEstado(String nome) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(nome);
        ObjectInputStream ois = new ObjectInputStream(fis);
        BDgeral h = (BDgeral) ois.readObject();
        ois.close();
        this.empresas       = h.getBDEmpresas();
        this.individuais    = h.getBDIndividuais();
        this.faturas        = h.getBDFaturas();
        this.setores        = h.getBDSetores();
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
        if (this.empresas.contains(i.getNif())){
            System.out.println("Contribuinte " + i.getNif() + " já inserido");
            return;
        }
        
        try{
            this.individuais.addContribuinte(i);
        }
        catch (ErroNotFound l){
            System.out.println("Contribuinte " + l.getMessage() + " já inserido");
        }
    }
    
    public void addEmpresa(Empresa i){
        if (this.individuais.contains(i.getNif())){
            System.out.println("Contribuinte " + i.getNif() + " já inserido");
            return;
        }
        
        try{
            this.empresas.addContribuinte(i);
        }
        catch (ErroNotFound l){
            System.out.println("Contribuinte " + l.getMessage() + " já inserido");
        }

    }
    
    public void addFatura(Fatura i){
        this.faturas.addFatura(i,this.individuais,this.empresas,this.setores);
    }

    public void addSetor(Setor s){this.setores.addSetor(s);}
    
    public Empresa getEmpresa(int nif) throws ErroNotFound{
        Empresa aux;
        Integer i = new Integer(nif);
        
        try{
            aux = (Empresa) this.empresas.getContribuinte(nif);
        }
        catch (ErroNotFound l){
            throw new ErroNotFound(i.toString());
        }
        
        return aux;
    }
    
    public CIndividual getCIndividual(int nif) throws ErroNotFound{
        CIndividual aux;
        Integer i = new Integer(nif);
        
        try{
            aux = (CIndividual) this.individuais.getContribuinte(nif);
        }
        catch (ErroNotFound l){
            throw new ErroNotFound(i.toString());
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
    
    public double deduz(int id){
        String setor = this.faturas.getFaturas().get(id).getCategoria();
        double taxa = this.setores.getSetores().get(setor).getTaxa();
        taxa *= this.faturas.getFaturas().get(id).getValor();

        try {
            CIndividual cont = (CIndividual) this.individuais.getContribuinte( this.faturas.getFaturas().get(id).getNif_cliente());
            taxa *= (cont.getNumAgregado()*0.05 + 1);
        }
        catch (ErroNotFound e) {
            System.out.println("fatura sem NIF  de cliente válido (NIF: " + this.faturas.getFaturas().get(id).getNif_cliente() +  ")\n");
        }

        return taxa;
    }

    public double reembolso(int nif){
        double ret = 0;

        try {
            CIndividual cont = (CIndividual) this.individuais.getContribuinte(nif);
            for(int id : cont.getFaturas()) {
                ret += deduz(id);
            }
        }
        catch (ErroNotFound e) {
            System.out.println("NIF de cliente válido (NIF: " + nif +  ")\n");
        }

        return ret;
    }

    //7 true ordena por tempo false por valor
    public List<Fatura> listagem_ordenada_emp_fatura( boolean type, int id){
        Empresa e;
        try {
            e = (Empresa) this.empresas.getContribuinte(id);
        }
        catch (ErroNotFound aux){
            System.out.println("Empresa " + aux.getMessage() + " nao encontrada");
            return new ArrayList<Fatura>();
        }
        
        List<Fatura> list = this.faturas.faturas_contribuinte(e.getFaturas());
        
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
        catch (ErroNotFound aux){
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
        catch (ErroNotFound aux){
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
        catch (ErroNotFound aux){
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

    //12
    // falta fazer
    public double top_X_faturacao (int x){
        Map<Integer,Double> faturacao = new HashMap<>();

        Map<Integer,Set<Integer>> database = this.empresas.getFaturasIds();

        Double singlefac;

        //total faturado
        for(Map.Entry<Integer,Set<Integer>> c : database.entrySet()) {
            singlefac = 0.0;

            for (int i : c.getValue()) {
                try{
                    Fatura aux = this.faturas.getFatura(i);
                    singlefac += aux.getValor();
                }
                catch (ErroNotFound e){
                    System.out.println("Fatura " + e.getMessage() + " não encontrada");
                }

            }
            faturacao.put(c.getKey(),singlefac);
            singlefac = 0.0;
        }
        
        //ordena
        Set<Map.Entry<Integer,Double>> ordena = new TreeSet<>(new Comparator<Map.Entry<Integer,Double>>(){

            public int compare(Map.Entry<Integer,Double> f1,Map.Entry<Integer,Double> f2){
                return f2.getValue() > f1.getValue() ? -1 : 1;
            }
        });

        faturacao.entrySet().forEach(e -> ordena.add(e)); //ordenar pelo que mais faturam
        
        //falta algoritmo de dedução


        return 0.0;
    }
    
    private String escolheSetor(int nif){
        Empresa aux;
        String fim,fim2 = null;
        Scanner in = new Scanner(System.in);
        
        try{
            aux= (Empresa) this.empresas.getContribuinte(nif);
        }
        catch (ErroNotFound l){
            System.out.println("Contribuinte " + l.getMessage() + "não inserido");
            return null;
        }
        
        
        boolean choosen = false;
        int k= 1000;
        System.out.println("Escolha da categoria da fatura. 1 para escolher");
        while(!choosen){
            Iterator i = aux.getSetores().iterator();
            
            while(i.hasNext() && (!choosen)){
                fim = (String) i.next();
                if (fim2 == null)
                    fim2 = null;
                System.out.println("Setor: " + fim);
                try{
                    k = in.nextInt();
                }
                catch (InputMismatchException e){
                    in.next();
               
                }
                if (k == 1){
                    fim2 = fim;
                    choosen = true;
                }    
            }
            
        }
        
        return fim2;
        
    }
    
    public void valida_faturas_contribuinte(int nif){
        CIndividual aux;
        try{
            aux= (CIndividual) this.individuais.getContribuinte(nif);
        }
        catch (ErroNotFound l){
            System.out.println("Contribuinte " + l.getMessage() + "não inserido");
            return;
        }
        
        Set<Integer> fact = aux.getFaturas();
        
        
        String a;
        Fatura b;
        
        for(Integer i : fact){
            if (!this.faturas.check_val_fatura(i)){
                try{
                    b = this.faturas.getFatura(i);
                    System.out.print(b.toString());
                    a = escolheSetor(b.getNif_emitente());
                    
                    try{
                        this.faturas.valida_fatura(i,a,this.setores);
                    }
                    catch(FaturaVal k){
                        System.out.println("Fatura " + k + " já validada");
                    }
                }
                catch (ErroNotFound e){
                    System.out.println("Fatura " + i + "não encontrada.");
                }
            }
        }
        
        System.out.println("Todas as faturas estão validadas");
        
    }
    
    public void Setores_admin() throws AdminAprov{
        try{
            this.setores.admin_aprov();
        }
        catch (AdminAprov e){
            throw (new AdminAprov());
        }
        
    }
    
}
