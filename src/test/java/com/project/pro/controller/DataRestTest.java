package com.project.pro.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Transactional은 꼭 springframework 것을 확인하고 선택하도록한다.
@Transactional
@DisplayName("rest - api 생성 테스트")
//WebMvcTest Test는 불필요한 패키지를 읽어들이지 않아서 autoconfiguration이 실행이 되지 않는다.
//따라서 간단한 방법은 아래와 같이 SpringBootTest를 사용하는 것이다.
//@WebMvcTest
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {
    private final MockMvc mvc;
    public DataRestTest(@Autowired MockMvc mvc){
        this.mvc = mvc;
    }
    @DisplayName("api 게시글 리스트 조회")
    @Test
    void test() throws Exception{

            mvc.perform(MockMvcRequestBuilders.get("/api/articles"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.valueOf("application/hal+json")))
                    .andDo(print());

    }
}
