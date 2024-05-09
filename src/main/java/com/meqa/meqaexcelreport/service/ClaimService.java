package com.meqa.meqaexcelreport.service;

import com.meqa.meqaexcelreport.models.Claim;
import com.meqa.meqaexcelreport.repository.ClaimRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.jxls.common.Context;
import java.util.Map.Entry;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClaimService {
    @Autowired
    private  final ClaimRepository claimRepository;

    public Object generateClaimExcelForFirst() {
        Map<String, String> claims = new HashMap<>();
        claims.put("name", "(sığortaçının (təkrarsığortaçının)  adı)");
        claims.put("claimClass", "(01)FerdiQeza");
        return claims;
    }

    public List<Claim> getAllClaims(List<Object> filterDTOList){
        return claimRepository.findAll();
    }

    public void exportData(Map<String, Object> dataList, InputStream templateStream, OutputStream outputStream) throws IOException {
        try(InputStream is = templateStream; OutputStream os = outputStream) {
            Context context = new Context();
            for (Entry<String, Object> element : dataList.entrySet()) { // 2
                context.putVar(element.getKey(), element.getValue());
            }
            JxlsHelper.getInstance().processTemplate(is, os, context);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
