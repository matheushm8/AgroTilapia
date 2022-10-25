package com.example.agrotilapia.singleton;

public class LoginSingleton {
    private static LoginSingleton propriedadeLogada;
    private static int codigoPropriedade;
    private static String nomePropriedade;

    public LoginSingleton(int codigoPropriedade, String nomePropriedade) {
        this.codigoPropriedade = codigoPropriedade;
        this.nomePropriedade = nomePropriedade;
    }

    public static LoginSingleton iniciaPropriedadeLogada(int codigoPropriedade, String nomePropriedade){
        if (propriedadeLogada == null)
            propriedadeLogada = new LoginSingleton(codigoPropriedade, nomePropriedade);

        return propriedadeLogada;

    }

    public static void limpaInstancia() {
        if (propriedadeLogada != null)
            propriedadeLogada = null;
    }

    public static LoginSingleton getpropriedadeLogada() {
        return propriedadeLogada;
    }

    public static void setpropriedadeLogada(LoginSingleton propriedadeLogada) {
        LoginSingleton.propriedadeLogada = propriedadeLogada;
    }

    public static int getCodigoPropriedade() {
        return codigoPropriedade;
    }

    public static void setCodigoPropriedade(int codigoPropriedade) {
        LoginSingleton.codigoPropriedade = codigoPropriedade;
    }

    public static String getnomePropriedade() {
        return nomePropriedade;
    }

    public static void setnomePropriedade(String nomePropriedade) {
        LoginSingleton.nomePropriedade = nomePropriedade;
    }
}
