package org.example.servicies;

import lombok.AllArgsConstructor;
import org.example.model.Socks;
import org.example.model.typeOperation;
import org.example.repository.SocksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SocksServiceImpl implements SocksService {
    private final SocksRepository socksRepository;
    private final Logger logger = LoggerFactory.getLogger(SocksServiceImpl.class);


    /**
     * Post income socks
     *
     * @param quantity   must be positive
     * @param cottonPart from 0 to 100
     * @param color      String
     * @return new object of Socks with current time and date and type of operation - income
     */
    @Override
    public Socks incomeSocks(int quantity, int cottonPart, String color) {
        checkingOnMistakes(color, cottonPart, quantity);

        Socks newSock = new Socks(color, quantity, cottonPart);
        newSock.setDate(Timestamp.valueOf(LocalDateTime.now()));
        newSock.setTypeOperation(typeOperation.INCOME);
        Socks savingSock = socksRepository.save(newSock);
        logger.info("Saving new income: {}", savingSock);
        return savingSock;
    }

    /**
     * Post outcome socks
     *
     * @param quantity   must be positive, reverse inside the method
     * @param cottonPart from 0 to 100
     * @param color      String
     * @return new object of Socks with current time and date and type of operation - outcome
     */
    @Override
    public Socks outcomeSocks(int quantity, int cottonPart, String color) {
        checkingOnMistakes(color, cottonPart, quantity);
        checkingOutcomeSock(color, cottonPart, quantity);

        Socks newSock = new Socks(color, -quantity, cottonPart);
        newSock.setDate(Timestamp.valueOf(LocalDateTime.now()));
        newSock.setTypeOperation(typeOperation.OUTCOME);
        Socks savingSock = socksRepository.save(newSock);
        logger.info("Saving new outcome: {}", savingSock);
        return savingSock;
    }


    /**
     * Getting amount of socks in parameters
     *
     * @param color      - String, color
     * @param operation  - moreThan/equals/lessThan (ignore case)
     * @param cottonPart - int, positive, less than 100
     * @return amount (int)
     */
    @Override
    public int getAmountOfSocksByParameters(String color, String operation, int cottonPart) {
        if (color.isBlank() || operation.isBlank() || cottonPart < 0 || cottonPart > 100) {
            logger.info("Mistake in parameters: operation = {}, cotton part= {}, color = {}", operation, cottonPart, color);

            throw new IllegalArgumentException("Wrong parameters");
        }

        Integer result;
        if (operation.equalsIgnoreCase("moreThan")) {
            result = socksRepository.getQuantityByColorAndMoreThanCottonPart(color, cottonPart);
            logger.info("Operation more than");
        } else if (operation.equalsIgnoreCase("equals")) {
            result = socksRepository.getQuantityByColorAndEqualsCottonPart(color, cottonPart);
            logger.info("Operation more than");

        } else if (operation.equalsIgnoreCase("lessThan")) {
            result = socksRepository.getQuantityByColorAndLessThenCottonPart(color, cottonPart);
            logger.info("Operation more than");

        } else {
            logger.info("Mistake in parameters: operation = {}", operation);
            throw new IllegalArgumentException("Wrong operation");

        }
        logger.info("Result = " + result);
        if (result == null) {
            throw new NotFoundException("There are any socks in this type");
        }
        return result;
    }

    /**
     * Checking if there were a mistake in parameters
     *
     * @param color      of socks
     * @param cottonPart of sosks
     * @param quantity   of theoretical outcome
     */
    private void checkingOnMistakes(String color, int cottonPart, int quantity) {
        if (quantity <= 0 || cottonPart < 0 || cottonPart > 100 || color.isBlank()) {
            logger.info("Mistake in parameters: quantity = {}, cotton part= {}, color = {}", quantity, cottonPart, color);
            throw new IllegalArgumentException("Mistake in parameters");
        }
    }


    /**
     * Checking if sum quantity of socks is not enough to make an outcome
     *
     * @param color      of socks
     * @param cottonPart of sosks
     * @param quantity   of theoretical outcome
     */
    private void checkingOutcomeSock(String color, int cottonPart, int quantity) {
        try {
            int sum = socksRepository.getQuantityByColorAndEqualsCottonPart(color, cottonPart);
            if (sum < quantity) {
                logger.info("Quantity of outcome socks with color = {} and cotton part {} is more than sum quantity of socks", color, cottonPart);
                throw new IllegalArgumentException("Quantity of outcome is more than sum quantity of socks");
            }
        } catch (NullPointerException e) {
            logger.info("There are not any socks in this parameters");
            throw new IllegalArgumentException("There are not any socks in this parameters");
        }
    }
}
