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
import java.lang.String;


public class BDgeral implements Serializable
{
    
    
    private BDContribuintes empresas;
    private BDContribuintes individuais;
    private BDFaturas faturas;
    private BDSetores setores;
    private Map<String,Double> conselhos = new HashMap<>();
    
    
    
    /**
      * Construtor sem argumentos.
    */
    public BDgeral(){
        this.empresas = new BDContribuintes();
        this.individuais = new BDContribuintes();
        this.faturas = new BDFaturas();
        this.setores = new BDSetores();
        adicionaConselhos();
    }
    

    /**
      * Construtor com argumentos.
      * @param a         Base de Dados de empresas a introduzir.
      * @param b         Base de Dados de contribuintes individuais a introduzir.
      * @param c         Base de Dados de faturas a introduzir.
      * @param d         Base de dados dos setores a introduzir.
    */
    public BDgeral(BDContribuintes a,BDContribuintes b,BDFaturas c, BDSetores d){
        this.empresas       = a.clone();
        this.individuais    = b.clone();
        this.faturas        = c.clone();
        this.setores        = d.clone();
        adicionaConselhos();
    }
    
    /**
      * Construtor com argumentos.
      * @param a  Contribuinte a copiar.
    */
    public BDgeral(BDgeral a){
        this.empresas       = a.getBDEmpresas();
        this.individuais    = a.getBDIndividuais();
        this.faturas        = a.getBDFaturas();
        this.setores        = a.getBDSetores();
        adicionaConselhos();
    }
    
    /**
      * Metodo para guardar o estado do objeto.
      * @param a  Nome do ficheiro a guardar.
    */
    public void guardaEstado(String nome) throws FileNotFoundException ,IOException{
        File f = new File(nome);
        if(!f.exists()){
            f.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(f);

        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }
    
    /**
      * Metodo para carregar o estado de um dado ficheiro.
      * @param a  Nome do ficheiro a carregar.
    */
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
    
    /**
      * Get para a variável empresas do objeto.
      * @return  Base de dados de empresas do objeto.
    */    
    public BDContribuintes getBDEmpresas(){
        return this.empresas.clone();
    }
    
    /**
      * Get para a variável contribuintes individuais do objeto.
      * @return  Base de dados de contribuintes individuais do objeto.
    */
    public BDContribuintes getBDIndividuais(){
        return this.individuais.clone();
    }
    
    /**
      * Get para a variável faturas do objeto.
      * @return  Base de dados de faturas do objeto.
    */
    public BDFaturas getBDFaturas(){
        return this.faturas.clone();
    }

    /**
      * Get para a variável setores do objeto.
      * @return  Base de dados dos setores do objeto.
    */
    public BDSetores getBDSetores() {
        return this.setores.clone();
    }
    
    
    /**
     * Metodo que adiciona conselhos a base de dados de setores.
     */
    private void adicionaConselhos(){
        this.conselhos.put("Alvito".toUpperCase(),0.2);
        this.conselhos.put("Cuba".toUpperCase(),0.2);
        this.conselhos.put("Ourique".toUpperCase(),0.4);
        this.conselhos.put("Serpa".toUpperCase(),0.1);
        this.conselhos.put("Almeida".toUpperCase(),0.2);
        this.conselhos.put("Belmonte".toUpperCase(),0.1);
        this.conselhos.put("Covilhã".toUpperCase(),0.4);
        this.conselhos.put("Fundão".toUpperCase(),0.2);
        this.conselhos.put("Guarda".toUpperCase(),0.05);
        this.conselhos.put("Gouveia".toUpperCase(),0.1);
        this.conselhos.put("Manteigas".toUpperCase(),0.5);
        this.conselhos.put("Oleiros".toUpperCase(),0.1);
        this.conselhos.put("Seia".toUpperCase(),0.4);
        this.conselhos.put("Pinhel".toUpperCase(),0.1);
        this.conselhos.put("Penamacor".toUpperCase(),0.2);
        this.conselhos.put("Tondela".toUpperCase(),0.1);
        this.conselhos.put("Viseu".toUpperCase(),0.3);
    }
    
    /**
     * Metodo que verifica se um conselho esta inserido na base de dados de setores.
     * @param s     Setor a verificar.
     * @return      Boolean que confirma a existencia do conselho.
     */
    public boolean containsConselho(String s){
        return this.conselhos.containsKey(s);
    }
    
    /**
      * Get para a lista de conselhos inseridos no objeto.
      * @return  Lista de conselhos inseridos no objeto.
    */
    public List<String> getConselhos(){
        return this.conselhos.keySet().stream().collect(Collectors.toList());
    }
    
    /**
     * Metodo que adiciona um contribuinte individual na base de dados
     * @param i     Contribuinte a inserir
     */
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
    
    /**
     * Metodo que adiciona uma empresa na base de dados
     * @param i     Empresa a inserir
     */
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
    
    /**
     * Metodo que adiciona uma fatura na base de dados
     * @param i     Fatura a inserir
     */
    public void addFatura(Fatura i){
        this.faturas.addFatura(i,this.individuais,this.empresas,this.setores);
    }

    /**
     * Metodo que adiciona um setor na base de dados
     * @param i     Setor a inserir
     */
    public void addSetor(Setor s){
        this.setores.addSetor(s);
    }
    
    /**
      * Metodo que retorna procura uma empresa dado um nif, da throw a ErroNotFound caso nao seja encontrada.
      * @param  nif Nif a procurar.
      * @return     Empresa encontrada.
    */
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
    
    /**
      * Metodo que retorna procura um contribuinte individual dado um nif, da throw a ErroNotFound caso nao seja encontrado.
      * @param  nif Nif a procurar.
      * @return     Contribuinte individual encontrada.
    */
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
    
    /**
      * Método toString do objeto.
      * @return Objeto em modo string.
    */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("==============Contribuintes individuais========================= \n");
        sb.append(this.individuais.toString() +"\n");
        sb.append("==============Empresas========================================== \n");
        sb.append(this.empresas.toString() + "\n");
        sb.append("==============Faturas=========================================== \n");
        sb.append(this.faturas.toString() + "\n");
        sb.append("==============Setores de Dedução================================ \n");
        sb.append(this.setores.toString() + "\n");
        
        return sb.toString();
    }
    
    /**
      * Método toString do objeto que guarda os contribuinte individuais.
      * @return Objeto em modo string.
    */
    
    public String toStringIndividuais(){
        return this.individuais.toString() + "\n";
    }
    
    /**
      * Método toString do objeto guarda as empresas.
      * @return Objeto em modo string.
    */
    
    public String toStringEmpresas(){
        return this.empresas.toString() + "\n";
    }
    
    /**
      * Método toString do objeto guarda os setores de dedução.
      * @return Objeto em modo string.
    */
    
    public String toStringSetores(){
        return this.setores.toString() + "\n";
    }
    
    /**
      * Método toString do objeto guarda as faturas.
      * @return Objeto em modo string.
    */
    
    public String toStringFaturas(){
        return this.faturas.toString() + "\n";
    }
    
    /**
      * Método que deduz o montante de um dado contribuinte.
      * @param  e   Contribuinte a verificar.
      * @return     Montante a receber do contribuinte.
    */
    public double deduz_montante(Contribuinte e){
        Set<Integer> idfaturas = e.getFaturas();
        
        EmpInterior aux1;
        FamiliaNum aux2;
        double montante = 0;
        double bonus;
        
        for(Integer i : idfaturas){
            try{
                Fatura a = this.faturas.getFatura(i);
                if (e.verificaSetor(a.getCategoria()))
                    montante += a.getValor() * this.setores.getBonificacao(a.getCategoria());
            }
            catch(ErroNotFound a){
                System.out.println("Fatura " + a.getMessage() + " não encontrada.");
            }
        }
        bonus = e.bonus();
        if (e instanceof FamiliaNum){
            aux2 = (FamiliaNum) e;
            bonus += aux2.reducaoImposto();
        }
        else if (e instanceof EmpInterior){
            aux1 = (EmpInterior) e;
            bonus += aux1.reducaoImposto(this.conselhos);
        }
        
        montante *= bonus;
        
        return montante;
    }
    
    /**
      * Método que deduz o montante de um contribuinte individual,caso nao encontre o contribuinte da throw ErrorNotFound.
      * @param  nif Contribuinte a verificar.
      * @return     Montante a receber .
    */
    public double deduz_montante_Individual(int nif) throws ErroNotFound{
        CIndividual e;
        FamiliaNum aux2;
        
        try{
            e = (CIndividual) this.individuais.getContribuinte(nif);
        }
        catch (ErroNotFound l){
            throw l;
        }
        
        Set<Integer> idfaturas = e.getFaturas();
        
        double montante = 0,bonus;
        
        for(Integer i : idfaturas){
            try{
                Fatura a = this.faturas.getFatura(i);
                if (e.verificaSetor(a.getCategoria()))
                    montante += a.getValor() * this.setores.getBonificacao(a.getCategoria());
            }
            catch(ErroNotFound a){
                System.out.println("Fatura " + a.getMessage() + " não encontrada.");
            }
        }
        bonus = e.bonus();
        if (e instanceof FamiliaNum){
            aux2 = (FamiliaNum) e;
            bonus += aux2.reducaoImposto();
        }
        
        montante *= bonus;
        
        return montante;
        
    }

    //7 true ordena por tempo false por valor
    /**
     * Metodo que devolve a lista de faturas de uma empresa ordenada conforme os parametros dados.
     * @param type  Boolean que permite a ordenacao com base o tempo/valor.
     * @param id    ID da empresa.
     * @return      Lista de faturas.
     */
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
    /**
     * Metodo que devolve a lista de faturas  (Map de <Consumidor,List<Fatura>>) de uma empresa ordenada conforme um intervalo de tempo.
     * @param start Data inicial.
     * @param end   Data final.
     * @param id    ID da empresa.
     * @return      Map de faturas.
     */
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
            if (!listagem.containsKey(f.getNif_cliente())){
                aux1 = new ArrayList<>();
                listagem.put(f.getNif_cliente(),aux1);
            }
            aux1 = listagem.get(f.getNif_cliente());
            aux1.add(f);
        }
        
        return listagem;
    }
    
    //9
    /**
     * Metodo que devolve a lista de faturas  (Map de <Consumidor,List<Fatura>>) de uma empresa.
     * @param id    ID da empresa.
     * @return      Map de faturas.
     */
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
    /**
     * Metodo que devolve o total faturado de uma empresa ordenada durante um intervalo de tempo.
     * @param start Data inicial.
     * @param end   Data final.
     * @param id    ID da empresa.
     * @return      Total faturado da empresa.
     */
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
    /**
     * Metodo que devolve a razao entre os 10 clientes que mais faturaram, da throw a ArithmeticException se o total for 0.
     * @return      Razao entre os 10 clientes que mais faturaram.
     */
    public double rel_top10() throws ArithmeticException{
        /*
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
                    return f2 > f1 ? -1 : 1;
                }
            });
        
        listagem.forEach((k,v) -> aux2.add(v.stream().mapToDouble(b -> b.getValor() ).sum()));*/
        
        Map<Integer,Double> listagem = new HashMap<>();
        double aux;
        
        for(Fatura a : faturas.getFaturas().values()){
            if(!listagem.containsKey(a.getNif_cliente()))
                listagem.put(a.getNif_cliente(),0.0);
                
            aux = listagem.get(a.getNif_cliente());
            aux += a.getValor();
            listagem.put(a.getNif_cliente(),aux);
        }
        
        TreeSet <Double> aux2 = new TreeSet <Double>(new Comparator<Double>(){
                
                public int compare(Double f1,Double f2){
                    return f2 > f1 ? 1 : -1;
                }
        });
        
        listagem.values().forEach(a -> aux2.add(a));
        
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
            throw new ArithmeticException("Total = 0");
        }
        
        return top10_total / total;
    }

    //12
    // falta fazer
    /**
     * Metodo que devolve a razao entre os X clientes que mais faturaram.
     * @param   x   Numero de clientes.
     * @return      Total faturado da empresa.
     */
    public double top_X_faturacao (int x){
        Map<Integer,Double> faturacao = new HashMap<>();
        Map<Integer,Double> deducao = new HashMap<>();

        Map<Integer,Contribuinte> database = this.empresas.getDados();

        Double singlefac;

        //total faturado
        for(Map.Entry<Integer,Contribuinte> c : database.entrySet()) {
            singlefac = 0.0;

            for (int i : c.getValue().getFaturas()) {
                try{
                    Fatura aux = this.faturas.getFatura(i);
                    singlefac += aux.getValor();
                }
                catch (ErroNotFound e){
                    System.out.println("Fatura " + e.getMessage() + " não encontrada");
                }

            }
            faturacao.put(c.getKey(),singlefac);
            deducao.put(c.getKey(),deduz_montante(c.getValue()));
            singlefac = 0.0;
        }
        
        //ordena
        Set<Map.Entry<Integer,Double>> ordena = new TreeSet<>(new Comparator<Map.Entry<Integer,Double>>(){

            public int compare(Map.Entry<Integer,Double> f1,Map.Entry<Integer,Double> f2){
                return f2.getValue() > f1.getValue() ? 1 : -1;
            }
        });

        faturacao.entrySet().forEach(e -> ordena.add(e)); //ordenar pelo que mais faturam
        
        //falta algoritmo de dedução
        Iterator i = ordena.iterator();
        int k = 0;
        double faturado = 0, deduzido = 0;
        
        while(i.hasNext() && k < x){
            Map.Entry<Integer,Double> a = (Map.Entry<Integer,Double>) i.next();
            faturado += a.getValue();
            deduzido += deducao.get(a.getKey());
            k++;
        }


        return (deduzido / faturado)*100;
    }
    
    /**
     * Metodo que escolhe o setor de uma fatura faturada por uma dada empresa.
     * @param   nif Nif da empresa.
     * @return      Setor escolhido.
     */
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
    
    /**
     * Metodo que valida as faturas de um dado contribuinte individual.
     * @param   nif Nif do contribuinte individual.
     */
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
    
    /**
     * Metodo que permite o admin aprovar os setores da base de dados, da throw a AdminAprov caso nao existam setores por aprovar.
     */
    public void Setores_admin() throws AdminAprov{
        try{
            this.setores.admin_aprov();
        }
        catch (AdminAprov e){
            throw (new AdminAprov("Tudo aprovado"));
        }
        
    }
    /**
     * Metodo que devolve a listagem de faturas de um dado contribuinte.
     * @param  a    Lista de faturas do contribuinte.
     * @return      Lista de faturas.
     */
    public List<Fatura> getFaturas_de_Id(Set<Integer> a){
        return this.faturas.faturas_contribuinte(a);
    }
    //individuais //empresa
    /**
     * Metodo que adiciona um setor a uma empresa/contribuinte individual, da throw a ErrorNotFound caso nao seja encontrado
     * @param nif       Nif a qual vai ser adicionado o setor
     * @param s         Setor a ser adicionado
     * @param type      True caso seja contribuinte individual, false caso seja empresa
     */
    public void addSetor(int nif,String s, boolean type) throws ErroNotFound{
        if (type){
            try{
                this.individuais.addSetor(s,nif);
            }
            catch(ErroNotFound l){
                throw l;
            }
        }
        else{
            try{
                this.empresas.addSetor(s,nif);
            }
            catch(ErroNotFound a){
                throw a;
            }
        }
    }
    
    /**
     * Metodo que adiciona um agregado a um contribuinte individual.
     * @param  nif          Nif do contribuinte a qual vai ser inserido o agregado.
     * @param nif_agregado  Nif a ser adicionar ao agregado.
     * @return              Lista de faturas.
     */
    public void addAgregado(int nif, int nif_agregado) throws ErroNotFound{
        try{
            this.individuais.addAgregado(nif,nif_agregado);
        }
        catch(ErroNotFound l){
            throw l;
        }
    }
    
    
}
