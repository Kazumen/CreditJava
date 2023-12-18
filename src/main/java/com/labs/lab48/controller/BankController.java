package com.labs.lab48.controller;


import com.labs.lab48.dto.BankCreatingDto;
import com.labs.lab48.dto.BankDto;
import com.labs.lab48.exception.BankAlreadyExistsException;

import com.labs.lab48.service.BankService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@AllArgsConstructor
@RequestMapping("/banks")

public class BankController {
    private BankService bankService;
    @GetMapping
    public Page<BankDto> getAllBanks(@PageableDefault Pageable pageable) {
        return bankService.getAllBanks(pageable);
    }
    @GetMapping("/{id}")
    public BankDto getBank(@PathVariable Integer id) {
        return bankService.getBank(id);
    }
    @PostMapping
    public BankDto addBank(@RequestBody @Valid BankCreatingDto bankCreatingDto) throws BankAlreadyExistsException {
        return bankService.addBank(bankCreatingDto);
    }
    @PatchMapping("/{id}")
    public BankDto editBank(@PathVariable Integer id,@RequestBody BankCreatingDto bankCreatingDto) throws BankAlreadyExistsException {
        return bankService.editBank(id,bankCreatingDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBank(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok("Bank with id:" + bankService.deleteBank(id) + " was deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }
}
