package trab.io;

import java.util.Calendar;

public class manipulaData {

    public static Calendar leDataString(String str) {
        String[] dado;
        int dia, mes, ano;
        Calendar data;
        
        dado = str.split("/");
        dia = Integer.parseInt(dado[0]);
        mes = Integer.parseInt(dado[1]) - 1;
        ano = Integer.parseInt(dado[2]);

        data = Calendar.getInstance();
        data.set(ano, mes, dia);

        return data;
    }
    public static String formatar(Calendar data) {
        String str = "";

        str += data.get(Calendar.DATE) + "/" +
                (data.get(Calendar.MONTH) + 1) + "/" +
                data.get(Calendar.YEAR);
        return str;
    }
}
