package com.example.likelion13spring.controller;


import com.example.likelion13spring.domain.Member;
import com.example.likelion13spring.dto.request.JoinRequestDto;
import com.example.likelion13spring.dto.response.ProductResponseByNameDto;
import com.example.likelion13spring.dto.response.ProductResponseDto;
import com.example.likelion13spring.dto.response.TokenResponseDto;
import com.example.likelion13spring.jwt.JwtTokenProvider;
import com.example.likelion13spring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.likelion13spring.config.PrincipalHandler;
import com.example.likelion13spring.service.ProductService;


import java.util.List;


@RestController
@RequiredArgsConstructor
public class JoinController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ProductService productService;



    @PostMapping("/join")
    public void join(@RequestBody JoinRequestDto joinRequestDto) {
        memberService.join(joinRequestDto);
    }

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody JoinRequestDto joinRequestDto) {
        Member member = memberService.login(joinRequestDto);
        return TokenResponseDto.of(jwtTokenProvider.generateAccessToken(member.getName()), jwtTokenProvider.generateRefreshToken(member.getName()));
    }

    @GetMapping("/my")
    public ResponseEntity<String> getMyName() {
        String name = PrincipalHandler.getUsernameFromPrincipal();
        return ResponseEntity.ok(name);
    }

    @GetMapping("/my/products")
    public ResponseEntity<List<ProductResponseByNameDto>> getMyProducts() {
        String username = PrincipalHandler.getUsernameFromPrincipal();
        List<ProductResponseByNameDto> products = productService.findProductNamesByUsername(username);
        return ResponseEntity.ok(products);
    }
}
