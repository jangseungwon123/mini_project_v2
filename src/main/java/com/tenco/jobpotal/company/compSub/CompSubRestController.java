package com.tenco.jobpotal.company.compSub;

import com.tenco.jobpotal._core.common.ApiUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "CompSub", description = "기업입장의 구독 관리 API")
public class CompSubRestController {

    private final CompSubService compSubService;

//    @Operation(summary = "구독저장", operationId = "CompSubSave")
//    @PostMapping("/api/comUsers/compsub")
//    public ResponseEntity<?> save(@Valid @RequestBody CompSubRequest.SaveDTO saveDTO, Errors errors) {
//        CompSubResponse.SaveDTO savedsub = compSubService.save(saveDTO,);
//        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiUtil<>(savedsub));
//    }

}
