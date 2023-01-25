package org.example.servicies;

import org.example.model.Socks;

public interface SocksService {
    Socks incomeSocks(int quantity, int cottonPart, String colour);
    Socks outcomeSocks(int quantity, int cottonPart, String colour);

    int getAmountOfSocksByParameters(String colour, String operation, int cottonPart);

}
