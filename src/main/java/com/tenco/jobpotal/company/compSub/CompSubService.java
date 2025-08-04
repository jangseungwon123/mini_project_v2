package com.tenco.jobpotal.company.compSub;

import com.tenco.jobpotal.company.CompInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CompSubService {

    private final CompSubJpaRepository compSubJpaRepository;

    @Transactional
    public CompSubResponse.SaveDTO save(CompSubRequest.SaveDTO saveDTO, CompInfo compInfo) {
        CompSub cSub = saveDTO.toEntity(compInfo);
       //compSubJpaRepository.save(cSub);
        return new CompSubResponse.SaveDTO(cSub);
    }

    @Transactional
    public List<CompSub> findAllByUserAndCompanyId(Long id){
        List<CompSub> compSubList = compSubJpaRepository.findAllByUserAndCompId(id);
        return compSubList;
    }
}
