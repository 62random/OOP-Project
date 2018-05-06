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

public class BDgeral
{
    private BDEmpresas empresas;
    private BDIndividuais individuais;
    private BDFaturas faturas;
    
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
    
    public BDEmpresas getBDEmpresas(){
        return this.empresas.clone();
    }
    
    public BDIndividuais getBDIndividuais(){
        return this.individuais.clone();
    }
    
    public BDFaturas getBDFaturas(){
        return this.faturas.clone();
    }
    
    
}
