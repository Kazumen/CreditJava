package com.labs.lab48.controller;

import com.labs.lab48.dto.LimitDto;
import com.labs.lab48.exception.LimitAlreadyExistsException;
import com.labs.lab48.service.LimitService;
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
@RequestMapping("/limits")
public class LimitController {
    private LimitService limitService;
    @GetMapping
    public Page<LimitDto> getAllLimits(@PageableDefault Pageable pageable) {
        return limitService.getAllLimits(pageable);
    }
    @GetMapping("/{id}")
    public LimitDto getLimit(@PathVariable Integer id) {
        return limitService.getLimit(id);
    }
    @PostMapping
    public LimitDto addLimit(@Param("userId") Integer userId,@Param("bankId") Integer bankId) throws LimitAlreadyExistsException {
        return limitService.addLimit(userId, bankId);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLimit(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok("Limit with id:" + limitService.delete(id) + " was deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }
    @GetMapping("/bank/{id}")
    public Page<LimitDto> getAllLimitsByBank(@PageableDefault Pageable pageable, @PathVariable Integer id) {
        return limitService.getLimitsByBank(pageable,id);
    }
    @GetMapping("/userbank")
    public LimitDto getLimitByUserAndBank(@Param("userId") Integer userId,@Param("bankId") Integer bankId){
        return limitService.getLimitByUserAndBank(userId, bankId);
    }

    @GetMapping("/user/{id}")
    public Page<LimitDto> getAllLimitsByUser(@PageableDefault Pageable pageable, @PathVariable Integer id) {
        return limitService.getLimitsByUser(pageable,id);
    }
    @PatchMapping("/{id}")
    public LimitDto updateLimit(@Param("maxLimit") Double maxLimit,@Param("currentLimit") Double currentLimit, @PathVariable Integer id)  {
        return limitService.updateLimit(maxLimit,currentLimit,id);
    }
}
