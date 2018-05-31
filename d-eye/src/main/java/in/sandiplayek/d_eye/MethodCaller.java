package in.sandiplayek.d_eye;

/**
 * Created by Android on 10-10-2017.
 */

public class MethodCaller {
    public static String floatBounder(String value){
        String neetValue="";
        if(value.matches("\\d+(?:\\.\\d+)?")){
            neetValue = value.substring(0, value.length() - 2);
        }else{
            neetValue=value;
        }
        return neetValue;
    }
}
