//package com.template.spring_mongodb.domain.user;
//
//import com.template.spring_mongodb.exception.UserErrors;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * - 커스텀 유저 디테일 서비스
// * : Spring Security의 UserDetailsService 인터페이스를 구현하여,
// *   주어진 사용자 이름(username)으로 사용자 정보를 로드하는 서비스.
// *   데이터베이스에서 사용자 정보를 조회하여 UserDetails 객체로 반환하며,
// *   존재하지 않는 경우 예외를 던짐 (UserErrors.BadCredentials).
// */
//
//
//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//    private final UserRepository userRepository;
//
//    @Override
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // 사용자 정보를 DB에서 조회
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(UserErrors.BadCredentials::new);
//
//        // 평문 비밀번호를 그대로 반환
//        return new User(
//            user.getId(),
//            user.getUsername(),
//            user.getName(),
//            new BCryptPasswordEncoder().encode(user.getPassword()), // 평문 비밀번호
//            user.getEmail(),
//            user.getMemberDepKey(),
//            user.getMemberDepName(),
//            user.getUseYn()
//        );
//    }
//}
