package com.pgi.pgic.controller;

import com.pgi.pgic.dto.DashboardKPIsResponse;
import com.pgi.pgic.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/dashboard")
@PreAuthorize("hasRole('ADMIN')")
public class AdminDashboardController {

    @Autowired private DashboardService dashboardService;

    @GetMapping("/kpis")
    public ResponseEntity<DashboardKPIsResponse> getKPIs() {
        return ResponseEntity.ok(dashboardService.getAdminKPIs());
    }
}