package tools;

public class Date {
    private static String[] end = new String[]{"Äê", "ÔÂ", "ÈÕ"};
    public static String Format_String(Object _t) {
        StringBuffer result = new StringBuffer("");
        String[] desc = String.valueOf(_t).split("-");
        for (int i = 0; i < desc.length; i++) {
            result.append(desc[i] + end[i]);
        }
        return result.toString();
    }

    public static java.sql.Date Format_Date(Object _t) {
        String[] desc = String.valueOf(_t).split("-");
        java.sql.Date date = new java.sql.Date(Integer.valueOf(desc[0]), Integer.valueOf(desc[1]), Integer.valueOf(desc[2]));
        return date;
    }
}
