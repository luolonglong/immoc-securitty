package com.imooc.web.controller;

import com.imooc.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author Gatsby.Luo
 * @date 2018-10-24
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private String folder = "E:/mooc/Security demo/imooc-security/imooc-security-demo/src/main/java/com/imooc/web/controller";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws Exception {

        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile = new File(folder, System.currentTimeMillis() + ".txt");

        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());

    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id,
                         HttpServletResponse response) throws IOException {
        try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
             OutputStream outputStream = response.getOutputStream();) {

            //设置content类型
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename = test.txt");

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }
}
