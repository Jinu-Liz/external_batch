package net.mobon.externalbatch.mapper;

import net.mobon.externalbatch.dto.TestDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestMapper {

  public int insertTest(TestDto params);

  public TestDto selectTest(Long idx);

  public int updateTest(TestDto params);

  public int deleteTest(Long idx);

  public List<TestDto> selectTestList();

  public int selectTestTotalCount();

}
