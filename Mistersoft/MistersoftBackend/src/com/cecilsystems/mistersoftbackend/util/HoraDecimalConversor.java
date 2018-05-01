package com.cecilsystems.mistersoftbackend.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Plamedi L. Lusembo
 */
public class HoraDecimalConversor {

    private static HoraDecimalConversor uniqueInstance;

    public HoraDecimalConversor() {
    }

    public static synchronized HoraDecimalConversor getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new HoraDecimalConversor();
        }
        return uniqueInstance;
    }

    public String converteDecimalEmHHMM(BigDecimal valor) {
        BigDecimal horas = new BigDecimal(valor.intValue() + "");
        BigDecimal minutos = valor.subtract(horas).multiply(new BigDecimal("60"));

        String resultadoHH = horas.intValue() < 10 ? "0" + horas : horas.toString();
        String resultadoMM = minutos.intValue() < 10 ? "0" + minutos.intValue() : minutos.intValue() + "";

        return resultadoHH + ":" + resultadoMM;
    }

    public BigDecimal converteHHMMEmDecimal(String valor) {
        String hm[] = valor.split(":");
        BigDecimal h = new BigDecimal(hm[0]);
        BigDecimal m = new BigDecimal(hm[1]);
        return h.add((m.multiply(new BigDecimal("100")))
                .divide(new BigDecimal("60"), 2, RoundingMode.HALF_UP)
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP));
    }
}
