package com.cecilsystems.mistersoftbackend.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.net.ntp.NTPUDPClient;

/**
 * @author Plamedi L. Lusembo
 */
public class NTPDataHoraCerta {

    private static NTPDataHoraCerta uniqueInstance;
    private final List<String> servidoresNTP;

    public NTPDataHoraCerta() {
        servidoresNTP = new ArrayList();
        servidoresNTP.add("a.st1.ntp.br");
        servidoresNTP.add("b.st1.ntp.br");
        servidoresNTP.add("c.st1.ntp.br");
        servidoresNTP.add("d.st1.ntp.br");
        servidoresNTP.add("a.ntp.br");
        servidoresNTP.add("b.ntp.br");
        servidoresNTP.add("c.ntp.br");
        servidoresNTP.add("gps.ntp.br");
    }

    public static synchronized NTPDataHoraCerta getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new NTPDataHoraCerta();
        }
        return uniqueInstance;
    }

    public Calendar getDataHoraCerta() {
        Calendar calendar = Calendar.getInstance();
        for (String ntpServer : servidoresNTP) {
            try {
                long returnTime = new NTPUDPClient()
                        .getTime(InetAddress.getByName(ntpServer))
                        .getReturnTime();

                Date time = new Date(returnTime);
                calendar.setTime(time);

                return calendar;

            } catch (UnknownHostException ex) {
                System.out.println(ex);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        throw new NullPointerException("Nenhum servidor NTP retornou a Data/Hora Certa");
    }
}
