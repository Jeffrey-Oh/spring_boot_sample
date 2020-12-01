package com.sample.apage.manage.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.sample.apage.manage.service.ApageManageService;
import com.sample.apage.manage.service.ApageManageVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApageManageController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApageManageService ApageManageService;

    /**
     * 관리자 로그인페이지
     * 
     * @param request
     * @param response
     * @param session
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/apage.do")
    public String adminLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session,
            ModelMap model) throws Exception {

        ApageManageVo mv = (ApageManageVo) session.getAttribute("adminInfo");

        if (mv != null && !mv.getSeq().equals("")) {
            // 로그인한 상태라면 메인페이지로 이동
            return "redirect:/apage/index.do";
        }

        return "apage/apageLogin";
    }

    /**
     * 관리자 로그인
     * 
     * @param request
     * @param response
     * @param session
     * @param vo
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/apage/adminLoginJson.do")
    public void adminLoginJson(HttpServletRequest request, HttpServletResponse response, HttpSession session,
            @ModelAttribute("vo") ApageManageVo vo) throws Exception {

        Gson gson = new Gson();
        Map<String, Object> data = new HashMap<String, Object>();

        try {

            log.info("adminLoginJson.do ::: {}", vo);

            ApageManageVo avo = ApageManageService.selectMemberByUserIdAndUserPw(vo);

            if (avo != null) {
                // 로그인정보 세션에 저장
                session.setAttribute("adminInfo", avo);

                // 로그인 정보 일치
                data.put("rs", "1");

            } else {
                // 로그인 정보 불일치
                data.put("rs", "2");
            }

            response.getWriter().print(gson.toJson(data));

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print(gson.toJson(data));
        }

    }

   /**
     * 관리자 메인 페이지
     */
    @RequestMapping(value = "/apage/index.do", method = RequestMethod.GET)
    public String requestMethodName(HttpServletRequest request, HttpServletResponse response, HttpSession session,
            @ModelAttribute("vo") ApageManageVo vo) throws Exception {

        return "apage/index";
    }

}