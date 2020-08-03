package net.mobon.externalbatch.util;

import net.mobon.externalbatch.dto.ExternalLinkageDto;
import net.mobon.externalbatch.mapper.ExternalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExternalReportUtils {

  @Autowired
  private ExternalMapper externalMapper;

  public ArrayList<ExternalLinkageDto> externalMatchList(String externalId, String zoneId, int mediaCode) {
    Map<String, String> params = new HashMap<>();
    params.put("externalId", externalId);
    params.put("zoneid", zoneId);
    if(mediaCode != 0) {
      params.put("scriptNo", String.valueOf(mediaCode));
    }
    params.put("type", "batch");

    return externalMapper.selectExternalMatch(params);
  }
}
