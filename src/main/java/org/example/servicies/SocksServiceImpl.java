package org.example.servicies;

import org.example.model.Socks;
import org.example.model.typeOperation;
import org.example.repository.SocksRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class SocksServiceImpl implements SocksService {
    private final SocksRepository socksRepository;

    public SocksServiceImpl(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }


    @Override
    public Socks incomeSocks(int quantity, int cottonPart, String colour) {
        Socks newSock = new Socks(colour, quantity, cottonPart);
        newSock.setDate(Timestamp.valueOf(LocalDateTime.now()));
        newSock.setTypeOperation(typeOperation.INCOME);
        return socksRepository.save(newSock);
    }

    @Override
    public Socks outcomeSocks(int quantity, int cottonPart, String colour) {
        if (quantity > 0) {
            quantity = - quantity;
        }

        Socks newSock = new Socks(colour, quantity, cottonPart);
        newSock.setDate(Timestamp.valueOf(LocalDateTime.now()));
        newSock.setTypeOperation(typeOperation.OUTCOME);
        return socksRepository.save(newSock);
    }

    @Override
    public Socks getSock(long id) {
        return socksRepository.findById(id).orElse(new Socks());
    }

    @Override
    public Collection<Socks> getAllSocks() {
        return null;
    }
}
