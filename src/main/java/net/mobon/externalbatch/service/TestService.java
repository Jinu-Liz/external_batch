package net.mobon.externalbatch.service;

import net.mobon.externalbatch.dto.TestDto;

import java.util.List;

public interface TestService {

    public boolean registerBoard(TestDto params);

    public TestDto getBoardDetail(Long idx);

    public boolean deleteBoard(Long idx);

    public List<TestDto> getBoardList();

}
