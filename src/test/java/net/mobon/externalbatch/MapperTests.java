package net.mobon.externalbatch;

import net.mobon.externalbatch.dto.TestDto;
import net.mobon.externalbatch.mapper.TestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
public class MapperTests {

  @Autowired
  private TestMapper testMapper;

  @Test
  public void testOfInsert() {
    TestDto params = new TestDto();
    params.setTitle("1번 게시글 제목");
    params.setContent("1번 게시글 내용");
    params.setWriter("테스터");

    int result = testMapper.insertTest(params);
    System.out.println("결과는 " + result + "입니다.");
  }
}
