package net.mobon.externalbatch.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mobon.externalbatch.dto.TestDto;
import net.mobon.externalbatch.mapper.TestMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final TestMapper testMapper;

    @Override
    public boolean registerBoard(TestDto params) {
        int queryResult = 0;

        if (params.getIdx() == null) {
            queryResult = testMapper.insertTest(params);
        } else {
            queryResult = testMapper.updateTest(params);
        }
        return (queryResult == 1) ? true : false;
    }

    @Override
    public TestDto getBoardDetail(Long idx) {
        return testMapper.selectTest(idx);
    }

    @Override
    public boolean deleteBoard(Long idx) {
        int queryResult = 0;

        TestDto board = testMapper.selectTest(idx);

        if (board != null && "N".equals(board.getDeleteYn())) {
            queryResult = testMapper.deleteTest(idx);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<TestDto> getBoardList() {
        List<TestDto> boardList = Collections.emptyList();

        int boardTotalCnt = testMapper.selectTestTotalCount();

        if (boardTotalCnt > 0) {
            boardList = testMapper.selectTestList();
        }
        return boardList;
    }
}
