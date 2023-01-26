package org.example.servicies;

import lombok.AllArgsConstructor;
import org.example.model.Socks;
import org.example.model.typeOperation;
import org.example.repository.SocksRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SocksServiceImpl implements SocksService {
    private final SocksRepository socksRepository;


    /**
     * Post income socks
     * @param quantity must be positive
     * @param cottonPart from 0 to 100
     * @param colour String
     * @return new object of Socks with current time and date and type of operation - income
     */
    @Override
    public Socks incomeSocks(int quantity, int cottonPart, String colour) {
        if (quantity <= 0 || cottonPart < 0 || cottonPart > 100 || colour.isBlank()) {
            throw new IllegalArgumentException("Mistake in parameters");
        }
        Socks newSock = new Socks(colour, quantity, cottonPart);
        newSock.setDate(Timestamp.valueOf(LocalDateTime.now()));
        newSock.setTypeOperation(typeOperation.INCOME);
        return socksRepository.save(newSock);
    }
    /**
     * Post outcome socks
     * @param quantity must be positive, reverse inside the method
     * @param cottonPart from 0 to 100
     * @param colour String
     * @return new object of Socks with current time and date and type of operation - outcome
     */
    @Override
    public Socks outcomeSocks(int quantity, int cottonPart, String colour) {
        if (quantity <= 0 || cottonPart < 0 || cottonPart > 100 || colour.isBlank()) {
            throw new IllegalArgumentException("Mistake in parameters");
        }
        Socks newSock = new Socks(colour, -quantity, cottonPart);
        newSock.setDate(Timestamp.valueOf(LocalDateTime.now()));
        newSock.setTypeOperation(typeOperation.OUTCOME);
        return socksRepository.save(newSock);
    }


    /**
     * Getting amount of socks in parameters
     * @param colour - String, colour
     * @param operation - moreThan/equals/lessThan (ignore case)
     * @param cottonPart - int, positive, less than 100
     * @return amount (int)
     */
    @Override
    public int getAmountOfSocksByParameters(String colour, String operation, int cottonPart) {
        if (colour.isEmpty() || operation.isEmpty() || cottonPart < 0 || cottonPart > 100 ) {
            throw new IllegalArgumentException("Wrong parameters");
        }
        Integer result = null;
        if (operation.equalsIgnoreCase("moreThan")) {
            result = socksRepository.getQuantityByColourAndMoreThanCottonPart(colour, cottonPart);
        }

        if (operation.equalsIgnoreCase("equals")) {
            result = socksRepository.getQuantityByColourAndEqualsCottonPart(colour, cottonPart);
        }

        if (operation.equalsIgnoreCase("lessThan")) {
            result = socksRepository.getQuantityByColourAndLessThenCottonPart(colour, cottonPart);
        }
        if (result == null) {
            throw new NotFoundException("There are any socks in this type");
        }
        return result;
    }
}
