package hello2.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
/** @ComponentScan : 컴포넌트 스캔을 위한 어노테이션, @Component 어노테이션이 붙은 클래스를 찾아 자동으로 등록해줌 */
@ComponentScan(
        //basePackages = "hello2.core.member", /** 이런 식으로 컴포넌트 탐색 위치를 지정할 수 있음.*/
        //basePackageClasses = AutoAppConfig.class, /** 지정한 클래스의 패키지를 탐색 시작 위치로 지정.*/
        /**
         * 권장하는 방법: 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것. 최근 스프링 부트도 이 방법을 기본으로 제공.
        */
        // Configuration 이라는 어노테이션 붙은건 빼버림. AppConfig 클래스의 스프링 빈들과 충돌할까봐
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) )
public class AutoAppConfig {
}
