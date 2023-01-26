package org.example.servicies;

import org.example.model.Socks;
import org.example.model.typeOperation;
import org.example.repository.SocksRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.example.SocksTestConstance.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SocksServiceImplTest {

    private SocksRepository socksRepository;
    private final SocksServiceImpl out = new SocksServiceImpl(socksRepository);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    void contextLoads() {
    }

    @Test
    public void testIncomeSocks() {
        Socks sock1 = new Socks();
        sock1.setTypeOperation(typeOperation.INCOME);
        sock1.setDate(Timestamp.valueOf(LocalDateTime.now()));
        sock1.setColour(COlOUR_RED);
        sock1.setQuantity(QUANTITY_50);
        sock1.setCottonPart(COTTON_PART_80);
        System.out.println(out.incomeSocks(QUANTITY_50, COTTON_PART_80, COlOUR_RED));
        Assertions.assertEquals(sock1, out.incomeSocks(QUANTITY_50, COTTON_PART_80, COlOUR_RED));

    }

}
