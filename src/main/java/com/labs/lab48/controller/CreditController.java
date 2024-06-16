package com.labs.lab48.controller;

import com.labs.lab48.dto.BankCreatingDto;
import com.labs.lab48.dto.BankDto;
import com.labs.lab48.dto.CreditCreatingDto;
import com.labs.lab48.dto.CreditDto;
import com.labs.lab48.exception.BankAlreadyExistsException;
import com.labs.lab48.exception.CreditAlreadyExistsException;
import com.labs.lab48.service.CreditService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/credits")
public class CreditController {
    private CreditService creditService;
    @GetMapping
    public Page<CreditDto> getAllCredits(@PageableDefault Pageable pageable) {
        return creditService.getAllCredits(pageable);
    }

    @GetMapping("/bank/{id}")
    public Page<CreditDto> getAllCreditsFromBank(@PageableDefault Pageable pageable, @PathVariable Integer id) {
        return creditService.getCreditsByBank(pageable, id);
    }

    @GetMapping("/{id}")
    public CreditDto getCredit(@PathVariable Integer id) {
        return creditService.getCredit(id);
    }
    @PostMapping
    public CreditDto addCredit(@RequestBody @Valid CreditCreatingDto creditCreatingDto) {
        return creditService.addCredit(creditCreatingDto);
    }
    @PatchMapping("/{id}")
    public CreditDto editCredit(@PathVariable Integer id,@RequestBody CreditCreatingDto creditCreatingDto) throws CreditAlreadyExistsException {
        return creditService.editCredit(id,creditCreatingDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCredit(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok("Credit with id:" + creditService.deleteCredit(id) + " was deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }
}
