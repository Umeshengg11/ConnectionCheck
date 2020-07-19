package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        for (int j = 0; j < 40; j++) {
            String hex1 = "";
            String hex2 = "";
            String hex3 = "";
            for (int i = 0; i < 40; i++) {


                Random random = new Random();
                Random random1 = new Random();
                Random random2 = new Random();
                int randInt = random.nextInt(15);
                int randInt1 = random1.nextInt(15);
                int randInt2 = random2.nextInt(15);
                String hex[] = new String[40];
                String hex11[] = new String[40];
                String hex22[] = new String[40];
                hex[i] = Integer.toHexString(randInt).toUpperCase();
                hex11[i] = Integer.toHexString(randInt1).toUpperCase();
                hex22[i] = Integer.toHexString(randInt2).toUpperCase();
                hex1 = hex1.concat(hex[i]);
                hex2 = hex2.concat(hex11[i]);
                hex3 = hex3.concat(hex22[i]);

            }
            Random rand = new Random();
            int randInt12 = rand.nextInt(100);
            String randIntS = Integer.toString(randInt12);
            String IpPre = "192.168.2.".concat(randIntS);
            String IpMid = "192.165.6.".concat(randIntS);
            String IpSuc = "192.164.1.".concat(randIntS);

            System.out.println(IpPre+" "+IpMid+" "+IpSuc);




            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Connection connection = DriverManager.getConnection("jdbc:sqlite:/opt/DataBase/P2P Routing/BootstrapTable");
                PreparedStatement pst = connection.prepareStatement("insert into bootstrap_table (MIDNODID,PRENODID,SUCNODID,IPADDRESSPRE,IPADDRESSMID,IPADDRESSSUC) values(?,?,?,?,?,?)");
                pst.setString(1, hex1);
                pst.setString(2, hex2);
                pst.setString(3, hex3);
                pst.setString(4,IpPre);
                pst.setString(5,IpMid);
                pst.setString(6,IpSuc);
                pst.executeUpdate();

                System.out.println("Successful");
            } catch (SQLException throwables) {
                throwables.printStackTrace();

            }
        }
    }


}
