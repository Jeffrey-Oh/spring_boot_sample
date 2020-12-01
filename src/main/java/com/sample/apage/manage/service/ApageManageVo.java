package com.sample.apage.manage.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApageManageVo {

    private String seq; // 일련번호
    private String userId; // 아이디
    private String userPw; // 비밀번호
    private String userPwEnc; // 비밀번호 암호화 SHA2-256
    private String name; // 이름
    private String tel; // 전화번호
    private String phone; // 휴대폰
    private String email; // 이메일
    private String zipCode; // 우편번호
    private String addr1; // 대표주소
    private String addr2; // 상세주소
    private String remoteIp; // 접근IP
    private String accessIp; // 허용IP 문자열
    private String level; // 관리자 권한
    private String regDate; // 등록일
    private String editDate; // 수정일
    private String lastLoginDate; // 마지막 로그인 일시
}