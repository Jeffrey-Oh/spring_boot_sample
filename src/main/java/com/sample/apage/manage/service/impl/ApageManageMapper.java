package com.sample.apage.manage.service.impl;

import com.sample.apage.manage.service.ApageManageVo;

import org.apache.ibatis.annotations.Mapper;

// Mapper 지정 필수
// 여기서 사용되는 메서드의 이름은 Mapper.xml 에서의 쿼리별 id값이된다. (틀리면안됨)
@Mapper
public interface ApageManageMapper {

    // 관리자 정보
    public ApageManageVo selectMemberByUserIdAndUserPw(ApageManageVo vo) throws Exception;

}