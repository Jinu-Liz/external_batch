package net.mobon.externalbatch.mapper;

import net.mobon.externalbatch.dto.ExternalLinkageDto;
import net.mobon.externalbatch.dto.MediaScriptDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
@Mapper
public interface ExternalMapper {

  public ArrayList<ExternalLinkageDto> selectExternalMatch(Map<String, String> params);

  public MediaScriptDto getMediaScript(HashMap<String, Object> daoMap);

  public String selectTest(String zoneid);
}
