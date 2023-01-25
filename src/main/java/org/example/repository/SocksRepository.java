package org.example.repository;

import org.example.model.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {
    @Query(value = "select sum(quantity) from socks WHERE colour =?1 and cotton_part <=?2", nativeQuery = true)
    Integer getQuantityByColourAndLessThenCottonPart(String color, int cottonPart);

    @Query(value = "select sum(quantity) from socks WHERE colour =?1 and cotton_part >=?2", nativeQuery = true)
    Integer getQuantityByColourAndMoreThanCottonPart(String color, int cottonPart);

    @Query(value = "select sum(quantity) from socks WHERE colour =?1 and cotton_part =?2", nativeQuery = true)
    Integer getQuantityByColourAndEqualsCottonPart(String color, int cottonPart);
}
