package tools;

import java.util.List;

public class Print {
    public static void PrintArray(List ls) {
        for (int i = 0; i < ls.size(); i++) {
            System.out.println(ls.get(i));
        }
    }
}
