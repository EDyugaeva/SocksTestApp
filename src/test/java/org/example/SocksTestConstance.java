package org.example;

import org.example.model.Socks;

public class SocksTestConstance {

    public final static String URL = "http://localhost:";
    public final static String PORT = "8085";
    public static final String COLOR_RED = "red";
    public static final int QUANTITY_50 = 50;
    public static final int COTTON_PART_80 = 80;
    public static final String COLOR_GREEN = "green";
    public static final int QUANTITY_MINUS_70 = -70;
    public static final int COTTON_PART_10 = 10;
    public static final String COLOR_BLACK = "black";
    public static final int QUANTITY_1 = 1;
    public static final int COTTON_PART_100 = 100;
    public static final Socks SOCK_1 = new Socks(COLOR_RED, QUANTITY_50, COTTON_PART_80);
    public static final Socks SOCK_2 = new Socks(COLOR_RED, QUANTITY_MINUS_70, COTTON_PART_80);


}
