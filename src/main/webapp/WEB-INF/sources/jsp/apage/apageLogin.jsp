<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
    <head>
        <link href="/resources/favicon.ico?v=2" type="image/x-icon" rel="shortcut icon" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>관리자로그인</title>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#userId").focus();
            });

            function doLogin() {
                if ($("#userId").val() == "" || $("#userId").val() == null) {
                    alert("아이디는 필수 입력 항목입니다.");
                    return;
                }

                if ($("#userPw").val() == "" || $("#userPw").val() == null) {
                    alert("패스워드는 필수 입력 항목입니다.");
                    return;
                }

                $.ajax({
                    type: "POST",
                    url: "<c:url value='/apage/adminLoginJson.do'/>",
                    data: {
                        userId: $.trim($("#userId").val()),
                        userPw: $.trim($("#userPw").val()),
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data.rs == "1") {
                            location.replace("<c:url value='/apage/index.do'/>");
                        } else {
                            alert("아이디 또는 패스워드를 확인하세요.");
                        }
                    },
                    error: function (data, status, err) {
                        alert("로그인에 실패하였습니다.");
                    },
                });
            }
        </script>
    </head>
    <body class="pg-log">
        <div class="log-panel">
            <h1>
                <span>관리자 시스템</span>
            </h1>
            <fieldset class="log-frm">
                <legend>관리자 로그인</legend>
                <p>
                    <label for="userId"><input type="text" id="userId" style="ime-mode: disabled" placeholder="아이디" title="아이디 입력" /></label>
                </p>
                <p>
                    <label for="password"
                        ><input
                            type="password"
                            id="userPw"
                            placeholder="비밀번호"
                            title="비밀번호 입력"
                            onkeypress="if(event.keyCode =='13'){doLogin()}"
                    /></label>
                </p>
                <input type="submit" value="로그인" onclick="doLogin();" />
            </fieldset>
            <p class="log-foot">
                <span>ⓒCopyright(c) 2020. All rights reserved.</span>
            </p>
        </div>
        <!-- //log-panel -->
    </body>
</html>