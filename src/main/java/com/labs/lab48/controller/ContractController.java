package com.labs.lab48.controller;

import com.labs.lab48.dto.ContractDto;
import com.labs.lab48.exception.LimitAlreadyExistsException;
import com.labs.lab48.exception.SumOutOfLimitException;
import com.labs.lab48.service.ContractService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/contracts")
public class ContractController {
    private ContractService contractService;
    @GetMapping
    public Page<ContractDto> getAllContracts(@PageableDefault Pageable pageable) {
        return contractService.getAllContracts(pageable);
    }
    @GetMapping("/user/{id}")
    public Page<ContractDto> getAllContractsWithUser(@PageableDefault Pageable pageable, @PathVariable Integer id) {
        return contractService.getContractByUser(pageable,id);
    }
    @GetMapping("/{id}")
    public ContractDto getContract(@PathVariable Integer id) {
        return contractService.getContract(id);
    }
    @PostMapping
    public ContractDto createContract(@Param("creditId") Integer creditId, @Param("userId") Integer userId) throws LimitAlreadyExistsException, SumOutOfLimitException {
        return contractService.createContract(creditId,userId);
    }
    @PatchMapping("/pay/{id}")
    public ContractDto payRepayment(@Param("payment")Double payment, @PathVariable Integer id) {
        return contractService.payRepayment(payment,id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContract(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok("Contract with id:" + contractService.deleteContract(id) + " was deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }
}
