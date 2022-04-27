package hello2.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
/** @ComponentScan : 컴포넌트 스캔을 위한 어노테이션, @Component 어노테이션이 붙은 클래스를 찾아 자동으로 등록해줌 */
@ComponentScan(
        // Configuration 이라는 어노테이션 붙은건 빼버림. AppConfig 클래스의 스프링 빈들과 충돌할까봐
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) )
public class AutoAppConfig {
}
