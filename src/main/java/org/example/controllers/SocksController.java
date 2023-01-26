package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.example.model.Socks;
import org.example.servicies.SocksService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
@AllArgsConstructor
public class SocksController {

    private final SocksService socksService;

    @Operation(summary = "Income socks",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Socks are added"),
                    @ApiResponse(responseCode = "400",
                            description = "Error in parameter"),
                    @ApiResponse(responseCode = "500",
                            description = "Error from API")
            })
    @PostMapping("/income")
    public Socks incomeSocks(@RequestParam(name = "colour") @Parameter(description = "Socks colour", example = "red") String colour,
                             @RequestParam(name = "cottonPart") @Parameter(description = "Cotton part in percentage, from 0 to 100", example = "50") int cottonPart,
                             @RequestParam(name = "quantity") @Parameter(description = "Quantity, more than 0", example = "15") int quantity) {
        return socksService.incomeSocks(quantity, cottonPart, colour);

    }

    @Operation(summary = "Outcome socks",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Socks are added"),
                    @ApiResponse(responseCode = "400",
                            description = "Error in parameter"),
                    @ApiResponse(responseCode = "500",
                            description = "Error from API")
            })
    @PostMapping("/outcome")
    public Socks outcome(@RequestParam(name = "colour") @Parameter(description = "Socks colour", example = "red") String colour,
                         @RequestParam(name = "cottonPart") @Parameter(description = "Cotton part in percentage, from 0 to 100", example = "50") int cottonPart,
                         @RequestParam(name = "quantity") @Parameter(description = "Quantity, more than 0", example = "15") int quantity) {
        return socksService.outcomeSocks(quantity, cottonPart, colour);

    }


    @Operation(summary = "Get amount of socks in parameters",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Socks are found"),
                    @ApiResponse(responseCode = "400",
                            description = "Error in parameter"),
                    @ApiResponse(responseCode = "500",
                            description = "Error from API")
            }
    )
    @GetMapping()
    public int getAmountOfSocksByColour(@RequestParam(name = "colour") @Parameter(description = "Socks colour", example = "red") String colour,
                                        @RequestParam(name = "operation")@Parameter(description = "Operation: lessThan, moreThan or equals", example = "equals") String operation,
                                        @RequestParam("cottonPart") @Parameter(description = "Cotton part in percentage, from 0 to 100", example = "50") int cottonPart) {
        return socksService.getAmountOfSocksByParameters(colour, operation, cottonPart);
    }

}
