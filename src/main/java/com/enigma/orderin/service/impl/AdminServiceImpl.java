package com.enigma.orderin.service.impl;

import com.enigma.orderin.dto.response.AdminResponse;
import com.enigma.orderin.entity.Admin;
import com.enigma.orderin.repository.AdminRepository;
import com.enigma.orderin.service.AdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public AdminResponse createAdmin(Admin admin) {
        adminRepository.createAdmin(admin.getName(), admin.getPhoneNumber());
        return AdminResponse.builder()
                .id(admin.getId())
                .name(admin.getName())
                .phoneNumber(admin.getPhoneNumber())
                .build();
    }

    @Override
    public AdminResponse getById(Integer id) {
        return adminRepository.getAdminById(id)
                .map(admin -> AdminResponse.builder()
                        .id(admin.getId())
                        .name(admin.getName())
                        .phoneNumber(admin.getPhoneNumber())
                        .build())
                .orElse(null);
    }

    @Override
    public List<AdminResponse> getAllAdmin() {
        List<Admin> listAdmin = adminRepository.getAllAdmin();
        return listAdmin.stream().map(
                        admin -> AdminResponse.builder()
                                .id(admin.getId())
                                .name(admin.getName())
                                .phoneNumber(admin.getPhoneNumber())
                                .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AdminResponse updateAdmin(Admin admin) {
        adminRepository.updateAdmin(admin.getName(), admin.getPhoneNumber(), admin.getId());
        return getById(admin.getId());
    }
}
