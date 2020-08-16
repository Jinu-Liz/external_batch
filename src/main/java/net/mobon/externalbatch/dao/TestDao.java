package net.mobon.externalbatch.dao;

import lombok.extern.slf4j.Slf4j;
import net.mobon.externalbatch.dto.TestDto;
import net.mobon.externalbatch.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public interface TestDao {
    public List<TestDto> selectListWithHashMap();
}
