package com.tenco.jobpotal.company.compSub;

import com.tenco.jobpotal.user.comp.CompUser;
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
    public CompSubResponse.SaveDTO save(CompSubRequest.SaveDTO saveDTO) {
        CompUser compUser = saveDTO.getCompUser();
        CompSub cSub= compSubJpaRepository.save(saveDTO.toEntity(compUser));
        return new CompSubResponse.SaveDTO(cSub);
    }

    @Transactional
    public List<CompSub> findAllByUserAndCompanyId(Long id){
        List<CompSub> compSubList = compSubJpaRepository.findAllByUserAndCompId(id);
        return compSubList;
    }
}
