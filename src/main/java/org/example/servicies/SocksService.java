package org.example.servicies;

import org.example.model.Socks;

import java.util.Collection;

public interface SocksService {
    Socks incomeSocks(int quantity, int cottonPart, String colour);
    Socks outcomeSocks(int quantity, int cottonPart, String colour);

    Socks getSock(long id);

    Collection<Socks> getAllSocks();


}
