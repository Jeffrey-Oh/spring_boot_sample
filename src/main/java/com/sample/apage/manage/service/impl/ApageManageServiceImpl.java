package com.sample.apage.manage.service.impl;

import com.sample.apage.manage.service.ApageManageService;
import com.sample.apage.manage.service.ApageManageVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApageManageServiceImpl implements ApageManageService {

    @Autowired
    ApageManageMapper mapper;

    @Override
    public ApageManageVo selectMemberByUserIdAndUserPw(ApageManageVo vo) throws Exception {
        return mapper.selectMemberByUserIdAndUserPw(vo);
    }

}