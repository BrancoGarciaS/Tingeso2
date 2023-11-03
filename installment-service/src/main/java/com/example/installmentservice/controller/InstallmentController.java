package com.example.installmentservice.controller;

import com.example.installmentservice.entity.Installment;
import com.example.installmentservice.service.InstallmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/installment")
public class InstallmentController {
    @Autowired
    InstallmentService installmentService;

    @GetMapping("/get")
    public ResponseEntity<List<Installment>> getAll() {
        List<Installment> installments = installmentService.getAll();
        if(installments.isEmpty()) { // si no hay cuotas en la base de datos
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(installments);
    }



    @GetMapping("/get/{rut}")
    public ResponseEntity<List<Installment>> getByStudentRut(@PathVariable("rut") String rut){
        List<Installment> installments = installmentService.getInstallmentByRut(rut);
        return ResponseEntity.ok(installments);
    }

    @PostMapping("/post")
    public ResponseEntity<Installment> save(@RequestBody Installment installment) {
        Installment installment1 = installmentService.saveData(installment);
        return ResponseEntity.ok(installment1);
    }

    @PutMapping("/pay/{id}")
    public int payInstallment(@PathVariable("id") Long id){
        return installmentService.pay_installment(id);
    }

    @PostMapping("/generateInstallments")
    public ResponseEntity<List<Installment>> generateInstallments(@RequestBody Map<String, Object> jsonData){
        List<Installment> installments = installmentService.generateInstallments(jsonData);
        return ResponseEntity.ok(installments);
    }



}