package com.imooc.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Gatsby.Luo
 * @date 2018-10-23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before()  //这个方法在每个方法执行之前都会执行一遍 如果没有初始化mockMvc 会在运行时报一个空指针错误.
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }

    @Test
    public void whenUploadSuccess() throws Exception {
        String result = mockMvc.perform(fileUpload("/file")
                .file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello upload".getBytes(StandardCharsets.UTF_8))))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        String result = mockMvc.perform(
                get("/user").param("username", "jojo").param("age", "18").param("ageTo", "60").param("xxx", "yyy")
                        // .param("size", "15")
                        // .param("page", "3")
                        // .param("sort", "age,desc")
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {
        String res = mockMvc.perform(get("/user/1")
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("tom"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(res);
    }

    @Test
    public void whenGetInfoFail() throws Exception {
        //"user/a"  与 "/user/a"效果不一样.    根路径也要加/
        mockMvc.perform(get("/user/a")
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }

    // TODO: 2018/10/23 此处userName 没有传递过去
    @Test
    public void whenCreateUser() throws Exception {
        Date time = new Date();
        System.out.println(time.getTime());
        String content = "{\" username \":\" tom \", \" password \":null , \" birthday \": " + time.getTime() + "}";
        String res = mockMvc.perform(post("/user").contentType(APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(res);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        Date date = new Date();
        System.out.println(date.getTime());
        String content = " {\"id\": \"22\",\"username\" :\"tom\" ,\"password\": null , \"birthday\" :" + date.getTime() + "}";
        String contentAsString = mockMvc.perform(post("/user/edit").content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);
    }
}