package net.mobon.externalbatch;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.mobon.externalbatch.dto.TestDto;
import net.mobon.externalbatch.mapper.TestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;

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

  @Test
  public void selectTest() {
    TestDto params = testMapper.selectTest((long) 1);
    try {
      String testJson = new ObjectMapper().writeValueAsString(params);

      System.out.println("================================");
      System.out.println(testJson);
      System.out.println("================================");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void updateTest() {
    TestDto params = new TestDto();
    params.setTitle("1번 게시글 제목을 수정합니다.");
    params.setContent("1번 게시글 내용을 수정합니다.");
    params.setWriter("홍길동");
    params.setIdx((long) 1);

    int result = testMapper.updateTest(params);
    if (result == 1) {
      TestDto dto = testMapper.selectTest((long) 1);
      try {
        String testJson = new ObjectMapper().writeValueAsString(dto);

        System.out.println("================================");
        System.out.println(testJson);
        System.out.println("================================");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void deleteTest() {
    int result = testMapper.deleteTest((long) 1);
    if (result == 1) {
      TestDto dto = testMapper.selectTest((long) 1);
      try {
        String testJson = new ObjectMapper().writeValueAsString(dto);

        System.out.println("================================");
        System.out.println(testJson);
        System.out.println("================================");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void testMultipleInsert() {
    for (int i = 2; i <= 50; i++) {
      TestDto params = new TestDto();
      params.setTitle(i + "번 게시글 제목");
      params.setContent(i +"번 게시글 내용");
      params.setWriter(i + "번 게시글 작성자");
      testMapper.insertTest(params);
    }
  }

  @Test
  public void testSelectList() {
    int testTotalCount = testMapper.selectTestTotalCount();
    if (testTotalCount > 0) {
      List<TestDto> testDtoList = testMapper.selectTestList();
      if (!CollectionUtils.isEmpty(testDtoList)) {
        for (TestDto test : testDtoList) {
          System.out.println("================================");
          System.out.println(test.getTitle());
          System.out.println(test.getContent());
          System.out.println(test.getWriter());
          System.out.println("================================");
        }
      }
    }
  }

  @Test
  public void selectListWithHashMap() {
    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put("writer","홍길동");
    List<TestDto> list = testMapper.selectListWithHashMap(hashMap);
    for (TestDto test : list){
      System.out.println("================================");
      System.out.println(test.getTitle());
      System.out.println(test.getContent());
      System.out.println(test.getWriter());
      System.out.println("================================");
    }
  }

}
