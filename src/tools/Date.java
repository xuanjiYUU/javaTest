package tools;

public class Date {
    private static final String[] end = new String[]{"Äê", "ÔÂ", "ÈÕ"};
    public static String Format_String(Object _t) {
        StringBuffer result = new StringBuffer();
        String[] desc = String.valueOf(_t).split("-");
        for (int i = 0; i < desc.length; i++) {
            result.append(desc[i] + end[i]);
        }
        return result.toString();
    }
}
