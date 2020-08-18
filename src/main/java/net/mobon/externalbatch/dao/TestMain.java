package net.mobon.externalbatch.dao;

import lombok.RequiredArgsConstructor;
import net.mobon.externalbatch.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TestMain {

    @Autowired
    private TestService testService;

    public static void main(String[] args) {
        System.out.println("테스트");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/config/mybatisConfig.xml");
        System.out.println("ctx >>" + ctx);

        TestMain testMain = ctx.getBean(TestMain.class);
        System.out.println(testMain);
        testMain.callService(ctx);
    }

    public void callService(ApplicationContext ctx) {
        System.out.println("--callService--");
        System.out.println();
    }
}
