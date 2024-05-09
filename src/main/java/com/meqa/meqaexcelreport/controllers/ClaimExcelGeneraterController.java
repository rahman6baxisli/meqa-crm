package com.meqa.meqaexcelreport.controllers;

import com.meqa.meqaexcelreport.models.Claim;
import com.meqa.meqaexcelreport.service.ClaimService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/export/")
@Controller
public class ClaimExcelGeneraterController {
   private final ClaimService claimService;

   @GetMapping("/forma/1")
   public void exportData(HttpServletResponse response) throws IOException {
       Map<String, Object> dataList = fetchData(); // Method to fetch your dynamic data

       // Add any dynamic template data if needed
       response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
       response.setHeader("Content-Disposition", "attachment; filename=\"forma_1.xlsx\"");
       OutputStream outputStream = response.getOutputStream();
       try (InputStream templateStream = new ClassPathResource("excel/forma1.xlsx").getInputStream()) {
           claimService.exportData(dataList, templateStream, outputStream);
       }
       outputStream.close();
   }
    private Map<String, Object> fetchData() {
        List<Object> filterDTOList = Collections.emptyList();
        List<Claim> claims = claimService.getAllClaims(filterDTOList);
        List<Claim> tekrarSigortaClaims = claims.stream().filter(claim -> !Objects.equals(claim.getTekrarSigortaSlipininNomresi(), ""))
                .collect(Collectors.toList());
        Map<String, Object> dynamicData = new HashMap<>();
        dynamicData.put("class", "(01)FerdiQeza");
        dynamicData.put("date", new Date().toString());
        dynamicData.put("claims", claims);
        dynamicData.put("tekrarSigortaClaims", tekrarSigortaClaims);
        return dynamicData;
    }
    @PostMapping("/claims/list")
    public ResponseEntity<Object> index(@RequestBody List<Object> filterDTOList) throws IOException {
        Object claimList = claimService.getAllClaims(filterDTOList);
        return  ResponseEntity.ok(claimList);
    }
}
