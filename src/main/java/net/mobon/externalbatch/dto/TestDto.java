package net.mobon.externalbatch.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class TestDto {
    long idx;
    String title;
    String writer;
    String content;
}
