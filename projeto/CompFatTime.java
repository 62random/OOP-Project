

import java.util.Comparator;
import java.time.LocalDate;

public class CompFatTime implements Comparator<Fatura>
{
    @Override
    public int compare(Fatura o1, Fatura o2) {
       if(o1.getEmissao().isBefore(o2.getEmissao()))
            return 1;
       else if ((o1.getEmissao().equals(o2.getEmissao())) && o1.equals(o2))
            return 0;
       return -1;
    }
}
