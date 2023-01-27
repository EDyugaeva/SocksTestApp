package org.example.servicies;

import org.example.model.Socks;

public interface SocksService {
    Socks incomeSocks(int quantity, int cottonPart, String color);
    Socks outcomeSocks(int quantity, int cottonPart, String color);

    int getAmountOfSocksByParameters(String color, String operation, int cottonPart);

}
