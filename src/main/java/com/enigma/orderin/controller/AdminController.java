package com.enigma.orderin.controller;

import com.enigma.orderin.constant.AppPath;
import com.enigma.orderin.dto.response.AdminResponse;
import com.enigma.orderin.dto.response.CommonResponse;
import com.enigma.orderin.entity.Admin;
import com.enigma.orderin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.ADMIN)
public class AdminController {
    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<?> createAdmin(@RequestBody Admin admin) {
        AdminResponse adminResponse = adminService.createAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<AdminResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully create new Admin")
                        .data(adminResponse).build());
    }

    @GetMapping(value = AppPath.ID_ADMIN)
    public ResponseEntity<?> getById (@PathVariable(name = "id_admin") Integer id){
        AdminResponse adminResponse = adminService.getById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get by id admin")
                        .data(adminResponse)
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAllAdmin (){
        List<AdminResponse> adminResponses = adminService.getAllAdmin();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all admin")
                        .data(adminResponses).build());
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateAdmin (@RequestBody Admin admin){
        AdminResponse adminResponse = adminService.updateAdmin(admin);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully update admin")
                        .data(adminResponse).build());
    }

}
