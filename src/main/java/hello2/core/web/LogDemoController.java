package hello2.core.web;

import hello2.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor // 생성자에 Autowired 자동으로 들어가면서 주입 자동으로 되게 하는 어노테이션
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerObjectProvider; // Provider를 사용해서 원하는 시점에 logger에 대한 빈을 생성해서 들고 온다.

    @RequestMapping("log-demo")
    @ResponseBody // 문자를 그대로 응답으로 보낼 수 있음
    public String logDemo(HttpServletRequest request){// HttpServletRequest, 자바에서 제공하는 표준 Servlet 규약이 있는데 http request 정보를 받을 수 있음
        String requestURL = request.getRequestURL().toString(); // 이러면 고객이 어떤 url을 요청했는지 알 수 있음.

        /** ObjectProvider덕분에 getObject를 호출하는 시점까지 request scope 빈의 생성을 지연할 수 있다. **/
        MyLogger myLogger = myLoggerObjectProvider.getObject();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }

}
