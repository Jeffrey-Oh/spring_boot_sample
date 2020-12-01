package com.sample.apage.manage.service;

import javax.servlet.http.HttpServletRequest;

public interface ApageManageService {

    // 관리자 정보
    public ApageManageVo selectMemberByUserIdAndUserPw(ApageManageVo vo) throws Exception;

}