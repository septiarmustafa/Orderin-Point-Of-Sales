package com.enigma.orderin.controller;

import com.enigma.orderin.constant.AppPath;
import com.enigma.orderin.dto.request.ProductRequest;
import com.enigma.orderin.dto.response.CashierResponse;
import com.enigma.orderin.dto.response.CommonResponse;
import com.enigma.orderin.dto.response.ProductResponse;
import com.enigma.orderin.entity.Cashier;
import com.enigma.orderin.service.CashierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.CASHIER)
public class CashierController {
    private final CashierService cashierService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createAdmin(@RequestBody Cashier cashier) {
        CashierResponse cashierResponse = cashierService.createCashier(cashier);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<CashierResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully create new Cashier")
                        .data(cashierResponse).build());
    }

    @GetMapping(value = AppPath.ID_CASHIER)
    public ResponseEntity<?> getById (@PathVariable(name = "id_cashier") Integer id){
        CashierResponse cashierResponse = cashierService.getById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get by id cashier")
                        .data(cashierResponse)
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAllCashier (){
        List<CashierResponse> cashierResponses = cashierService.getAllCashier();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all cashier")
                        .data(cashierResponses).build());
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCashier (@RequestBody Cashier cashier){
        CashierResponse cashierResponse = cashierService.updateCashier(cashier);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully update admin")
                        .data(cashierResponse).build());
    }
}
