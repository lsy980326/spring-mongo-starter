//package com.template.spring_mongodb.domain.user;
//
//import com.template.spring_mongodb.config.security.JwtUtil;
//import com.template.spring_mongodb.domain.GlobalServiceContext;
//import com.template.spring_mongodb.dto.request.*;
//import com.template.spring_mongodb.dto.response.*;
//import com.template.spring_mongodb.exception.AdminErrors;
//import com.template.spring_mongodb.exception.UserErrors;
//import com.template.spring_mongodb.mapper.UserMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
///**
// * - 유저 서비스
// * : 사용자 인증, 로그인, 비밀번호 변경, 사용자 정보 조회 및 수정, 그룹 관리,
// *       알림 설정, 토큰 갱신 등 사용자와 관련된 비즈니스 로직 처리
// */
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class UserService {
//    private final GlobalServiceContext globalServiceContext;
//    private final AuthenticationService authenticationService;
//    private final AuthenticationManager authenticationManager;
//    private final CustomUserDetailsService userDetailsService;
//    private final JwtUtil jwtUtil;
//    private final PasswordEncoder passwordEncoder;
//    private final UserMapper userMapper;
//    private final UserRepository userRepository;
//    private final AdminRepository adminRepository;
//
//
//    @Transactional
//    public LoginResponseDto login(LoginDto loginDto) {
//        //TODO: 관리자 테이블에서 먼저 조회하는 로직 필요
//        Boolean isAdmin = adminRepository.existsByextAdminId(Long.valueOf(loginDto.username()));
//        if (!isAdmin) {
//            throw new AdminErrors.RequiredValuesNotFORBIDDEN();
//        }
//
//        // 사용자 이름으로 사용자 정보를 로드합니다. (사용자 ID 즉 사번(MEMKEY)로 로그인함)
//        User user = (User) userDetailsService.loadUserByUsername(loginDto.username());
//
//        // 로드된 사용자 정보를 바탕으로 데이터베이스에서 사용자 정보를 조회합니다.
//        Optional<User> loggedInUser = userRepository.findById(user.getId());
//
//        // 입력된 비밀번호가 저장된 비밀번호와 일치하지 않으면 로그인 실패 처리합니다.
//        System.out.println("확인용:::::"+user); // 디버그용 출력
//        if (!passwordEncoder.matches(loginDto.password(), user.getPassword())) {
//            log.info("사용자: {}, 로그인 실패, 비밀번호 일치하지 않음", user.getUsername());
//            // 비밀번호가 일치하지 않으면 예외를 던집니다.
//            throw new UserErrors.BadCredentials();
//        }
//
//        // 비밀번호가 일치하면 로그인 성공 처리합니다.
//        loggedInUser.ifPresent(User::loginSuccess);
//
//        // JWT 토큰을 생성합니다.
//        String accessToken = jwtUtil.generateToken(user);
//        String refreshToken = jwtUtil.generateRefreshToken(user);
//        String expiresIn = String.valueOf(jwtUtil.extractExpiration(accessToken).getTime());
//
//        log.info("사용자: {}, 로그인 성공", user.getUsername());
//
//        Admin admin = adminRepository.findByExtAdminId(user.getId());
//        System.out.println("확인용:::::"+admin); // 디버그용 출력
//
//        // TokenDto 생성
//        LoginResponseDto.TokenDto tokenDto = new LoginResponseDto.TokenDto(
//            accessToken,
//            refreshToken,
//            "bearer",
//            expiresIn
//        );
//
//        // UserInformationDto 생성
//        LoginResponseDto.UserInformationDto userInformationDto = new LoginResponseDto.UserInformationDto(
//            admin.getAdminId(),
//            user.getName(),
//            user.getMemberDepName()
//        );
//
//        // 최종 응답 생성 및 반환
//        return new LoginResponseDto(tokenDto, userInformationDto);
//    }
//
//    public LoginResponseDto refreshToken(String refreshToken) {
//        try {
//            // TODO: 토큰 타입이 refresh 인지 확인
//            String username = jwtUtil.getUsernameByToken(refreshToken);
//            User user = (User) userDetailsService.loadUserByUsername(username);
//            if (jwtUtil.validateToken(refreshToken, user)) {
//                String newAccessToken = jwtUtil.generateToken(user);
//                String expiresIn = String.valueOf(jwtUtil.extractExpiration(newAccessToken).getTime());
//                log.info("사용자: {}, 토큰 갱신 성공", username);
//
//                Admin admin = adminRepository.findByExtAdminId(user.getId());
//
//                // TokenDto 생성
//                LoginResponseDto.TokenDto tokenDto = new LoginResponseDto.TokenDto(
//                    newAccessToken,
//                    refreshToken,
//                    "bearer",
//                    expiresIn
//                );
//
//                // UserInformationDto 생성
//                LoginResponseDto.UserInformationDto userInformationDto = new LoginResponseDto.UserInformationDto(
//                    admin.getAdminId(),
//                    user.getUsername(),
//                    user.getMemberDepName()
//                );
//
//                return new LoginResponseDto(tokenDto, userInformationDto);
//            } else {
//                throw new UserErrors.InvalidRefreshToken();
//            }
//        } catch (Exception e) {
//            throw new UserErrors.InvalidRefreshToken();
//        }
//    }
//
//    public CurrentUser getCurrentUser() {
//        return authenticationService.getCurrentUser();
//    }
//}