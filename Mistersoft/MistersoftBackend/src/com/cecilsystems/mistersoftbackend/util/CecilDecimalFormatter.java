package com.cecilsystems.mistersoftbackend.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author Plamedi L. Lusembo
 */
public class CecilDecimalFormatter {

    private final NumberFormat numberFormat;
    private final NumberFormat numberFormat2;
    private static CecilDecimalFormatter uniqueInstance;

    public CecilDecimalFormatter() {
        numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        numberFormat2 = NumberFormat.getInstance(new Locale("da", "DK"));
    }

    public static synchronized CecilDecimalFormatter getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CecilDecimalFormatter();
        }
        return uniqueInstance;
    }

    public String formattedValue(String value) {
        value = value.replace(",", ".");
        double dbl = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return numberFormat.format(dbl);
    }

    public String formattedValue(BigDecimal value) {
        double dbl = value.setScale(2, RoundingMode.HALF_UP).doubleValue();
        return numberFormat.format(dbl);
    }

    public String formattedValueSemSimbolo(String value) {
        value = value.replace(",", ".");
        if (value == null || value.isEmpty()) {
            return "0,00";
        }
        double dbl = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
        String result = numberFormat2.format(dbl);
        if (!result.contains(",")) {
            return result + ",00";
        } else {
            String ra[] = result.split(",");
            if (ra[1].length() == 1) {
                return result + "0";
            }
        }
        return numberFormat2.format(dbl);
    }

    public String formattedValueSemSimbolo4Zero(String value) {
        value = value.replace(",", ".");
        if (value == null || value.isEmpty()) {
            return "0,0000";
        }
        double dbl = new BigDecimal(value).setScale(4, RoundingMode.HALF_UP).doubleValue();
        String result = numberFormat2.format(dbl);
        if (!result.contains(",")) {
            return result + ",0000";
        } else {
            String ra[] = result.split(",");
            if (ra[1].length() == 1) {
                return result + "0";
            }
        }
        return numberFormat2.format(dbl);
    }

    public String formattedValueSemSimbolo(BigDecimal value) {
        double dbl = value.setScale(2, RoundingMode.HALF_UP).doubleValue();
        String result = numberFormat2.format(dbl);
        if (!result.contains(",")) {
            return result + ",00";
        } else {
            String ra[] = result.split(",");
            if (ra[1].length() == 1) {
                return result + "0";
            }
        }
        return numberFormat2.format(dbl);
    }

    public String formattedValueSemSimbolo4Zero(BigDecimal value) {
        double dbl = value.setScale(4, RoundingMode.HALF_UP).doubleValue();
        String result = numberFormat2.format(dbl);
        if (!result.contains(",")) {
            return result + ",0000";
        } else {
            String ra[] = result.split(",");
            if (ra[1].length() == 1) {
                return result + "0";
            }
        }
        return numberFormat2.format(dbl);
    }

    public String standardValue(String value) {
        return value.replace("R$ ", "").replace(".", "");
    }

    public BigDecimal bigStandardValue(String value) {
        if (value.isEmpty()) {
            return new BigDecimal("0.00");
        }
        return new BigDecimal(value.replace("R$ ", "").replace(".", "").replace(",", "."));
    }

    public BigDecimal bigStandardValue4Zero(String value) {
        if (value.isEmpty()) {
            return new BigDecimal("0.0000");
        }
        return new BigDecimal(value.replace("R$ ", "").replace(".", "").replace(",", "."));
    }

}
