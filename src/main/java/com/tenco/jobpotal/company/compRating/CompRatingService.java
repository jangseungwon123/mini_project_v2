//package com.tenco.jobpotal.company.comp_rating;
//
//import com.tenco.jobpotal._core.errors.exception.Exception404;
//import com.tenco.jobpotal.user.CompUser;
//import com.tenco.jobpotal.user.CompUserJpaRepository;
//import com.tenco.jobpotal.user.LoginUser;
//import com.tenco.jobpotal.user.User;
//import com.tenco.jobpotal.user.appl_info.AppiInfo;
//import com.tenco.jobpotal.user.appl_info.AppiInfoJpaRepository;
//import com.tenco.jobpotal.user.appl_info.AppiInfoResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//@Service
//public class CompRatingService {
//
//    private final CompRatingJpaRepository compRatingJpaRepository;
//    private final AppiInfoJpaRepository appiInfoJpaRepository;
//    private final CompUserJpaRepository compUserJpaRepository;
//
//
//
//
//
//
////    @Transactional
////    public BoardResponse.SaveDTO save(BoardRequest.SaveDTO saveDTO, SessionUser sessionUser) {
////        User user = User.builder()
////                .id(sessionUser.getId())
////                .username(sessionUser.getUsername())
////                .email(sessionUser.getEmail())
////                .build();
////        Board board = saveDTO.toEntity(user);
////        Board savedBoard = boardJpaRepository.save(board);
////        return new BoardResponse.SaveDTO(savedBoard);
////    }
//
//}
