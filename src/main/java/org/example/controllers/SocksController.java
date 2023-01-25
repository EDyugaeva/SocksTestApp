package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    public Socks incomeSocks(@RequestParam String colour, @RequestParam int cottonPart, @RequestParam int quantity) {
        return socksService.incomeSocks(quantity, cottonPart, colour);

    }

    @PostMapping("/outcome")
    public Socks outcome(@RequestParam String colour, @RequestParam int cottonPart, @RequestParam int quantity) {
        return socksService.outcomeSocks(quantity, cottonPart, colour);

    }


    @Operation(summary = "Get amount of socks in parameters",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Socks are found"),
                    @ApiResponse(responseCode = "400",
                            description = "Error in parameter")
            })

    @GetMapping(path = "/finding")
    public int getAmountOfSocksByColour(@RequestParam("colour") String colour,
                                        @RequestParam("operation") String operation,
                                        @RequestParam("cottonPart") int cottonPart) {
        return socksService.getAmountOfSocksByParameters(colour, operation, cottonPart);
    }

}
