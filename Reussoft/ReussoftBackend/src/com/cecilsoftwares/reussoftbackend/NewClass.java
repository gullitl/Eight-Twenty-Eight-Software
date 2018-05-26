/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cecilsoftwares.reussoftbackend;

/**
 *
 * @author Plamedi L. Lusembo
 */
public class NewClass {

    public static void main(String[] args) {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection(
//                    "jdbc:mysql://db4free.net:3306/makservicedb?verifyServerCertificate=false&useSSL=true", "cecilsystems", "reussoft2018");
//            //here sonoo is database name, root is username and password  
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("select * from collaborateur");
//            while (rs.next()) {
//                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
//            }
//            con.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        String var1 = "A1;A2";
        String var2 = var1 + ";A3";

        var1.substring(var1.indexOf(";") + 1);

        String res;
        if (var1.length() < var2.length()) {
            res = var1;
        } else {
            res = var2;
        }

        System.out.println(String.format("Résultats: %s (%d)", res, res.length()));

    }
}
