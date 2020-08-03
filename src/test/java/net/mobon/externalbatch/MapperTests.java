package net.mobon.externalbatch;

import net.mobon.externalbatch.dto.ExternalLinkageDto;
import net.mobon.externalbatch.mapper.ExternalMapper;
import net.mobon.externalbatch.util.ExternalReportUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class MapperTests {

  @Autowired
  private ExternalMapper externalMapper;

  @Autowired
  private ExternalReportUtils utils;

  @Test
  public void externalMatchList() {
    String zoneid = "u5SVjsNh";
    String snum = externalMapper.selectTest(zoneid);

    System.out.println("snum : " + snum);
  }

  @Test
  public void externalist() {
    String externalId = "nitmuss";
    String zoneid = "u5SVjsNh";
    ArrayList<ExternalLinkageDto> list = utils.externalMatchList(externalId, zoneid, 0);

    for (ExternalLinkageDto dto : list) {
      System.out.println("snum : " + dto.getMedia_code());
    }
  }
}
