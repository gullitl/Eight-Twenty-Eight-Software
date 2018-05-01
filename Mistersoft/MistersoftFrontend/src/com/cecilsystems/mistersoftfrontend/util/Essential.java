package com.cecilsystems.mistersoftfrontend.useful;

import java.net.URL;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * @author Plamedi L. Lusembo
 */
public class Essential {

    private static Essential uniqueInstance;

    public Essential() {
    }

    public static synchronized Essential getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Essential();
        }
        return uniqueInstance;
    }

    public void geraRelatorio(JRDataSource jRDataSource, Map<String, Object> parametros,
            URL arquivo, String titulo) {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(arquivo);
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, jRDataSource);

            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle(titulo);
            jv.setVisible(true);

        } catch (JRException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

}
