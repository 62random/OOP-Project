
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class FamiliaNum extends CIndividual implements Serializable
{
    public FamiliaNum(){
        super();
    }
    
    public FamiliaNum(int nif1,String nemail,String nnome,String nmorada,String npassword,int nn_agregado,Set<Integer> agregados,double ncoeficiente,Set<String> nsetores,Set<Integer> faturas){
        super(nif1,nemail,nnome,nmorada,npassword,nn_agregado,agregados,ncoeficiente,nsetores,faturas);
    }
    
    public FamiliaNum(FamiliaNum a){
        super(a);
    }
    
    public FamiliaNum clone(){
        return new FamiliaNum(this);
    }
    
    public String toString(){
        return super.toString();
    }
    
    public boolean equals(Object o){
        if(this == o)
            return true;
        
        if (o == null || (this.getClass() != o.getClass()))
            return false;
        
        FamiliaNum aux = (FamiliaNum) o;
        
        return super.equals(aux);
    }
    
    public int hashCode(){
        return super.hashCode();
    }
    
    public double reducaoImposto(){
        return 0.05*getNumAgregado();
    }
}
