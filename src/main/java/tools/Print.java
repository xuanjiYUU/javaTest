package tools;
import java.util.List;

public class Print{
    public static void PrintArray(List ls) {
        if(ls.size()==0){
            return ;
        }
        for (Object l : ls) {
            System.out.println(l);
        }
    }
}
