package org.example.controllers;

import org.example.model.Socks;
import org.example.servicies.SocksService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks/")
public class SocksController {

    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/income")
    public Socks incomeSocks(@RequestParam String colour, @RequestParam int cottonPart,@RequestParam int quantity) {
        return   socksService.incomeSocks(quantity, cottonPart, colour);

    }

    @PostMapping("/outcome")
    public Socks outcome(@RequestParam String colour, @RequestParam int cottonPart,@RequestParam int quantity) {
        return   socksService.outcomeSocks(quantity, cottonPart, colour);

    }

    @GetMapping
    public Socks getSocks(@RequestParam Long id) {
        return socksService.getSock(id);
    }



}
