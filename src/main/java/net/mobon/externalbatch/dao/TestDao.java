package net.mobon.externalbatch.dao;

import lombok.extern.slf4j.Slf4j;
import net.mobon.externalbatch.dto.TestDto;
import net.mobon.externalbatch.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TestDao {

    @Autowired
    TestMapper testMapper;

    public void selectStart() {
        List<TestDto> testList = testMapper.selectTestList();
        for (TestDto test : testList) {
            System.out.println("================================");
            System.out.println(test.getTitle());
            System.out.println(test.getContent());
            System.out.println(test.getWriter());
            System.out.println("================================");
        }
    }
}
