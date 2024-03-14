package com.enigma.orderin.service.impl;

import com.enigma.orderin.dto.response.CashierResponse;
import com.enigma.orderin.entity.Cashier;
import com.enigma.orderin.repository.CashierRepository;
import com.enigma.orderin.service.CashierService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CashierServiceImpl implements CashierService {

    private final CashierRepository cashierRepository;

    @Override
    public CashierResponse createCashier(Cashier cashier) {
        cashierRepository.createCashier(cashier.getName(), cashier.getPhoneNumber());
        return CashierResponse.builder()
                .id(cashier.getId())
                .name(cashier.getName())
                .mobilePhone(cashier.getPhoneNumber())
                .build();
    }

    @Override
    public CashierResponse getById(Integer id) {
        return cashierRepository.getCashierById(id)
                .map(cashier -> CashierResponse.builder()
                        .id(cashier.getId())
                        .name(cashier.getName())
                        .mobilePhone(cashier.getPhoneNumber())
                        .build())
                .orElse(null);
    }

    @Override
    public List<CashierResponse> getAllCashier() {
        List<Cashier> listCashier = cashierRepository.getAllCashier();
        return listCashier.stream().map(
                        admin -> CashierResponse.builder()
                                .id(admin.getId())
                                .name(admin.getName())
                                .mobilePhone(admin.getPhoneNumber())
                                .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CashierResponse updateCashier(Cashier cashier) {
        cashierRepository.updateCashier(cashier.getName(), cashier.getPhoneNumber(), cashier.getId());
        return getById(cashier.getId());
    }
}
