package com.cz2415.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * @author chenzhuo
 * @version V1.0
 * @package com.cz2415.controller
 * @description: TODO
 * @email chzhuo@neusoft.com
 * @create 2018-02-25 18:38
 */
@Controller
public class FileUploadController {
    @RequestMapping("/file")
    public String file() {
        return "/file";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                File dir = new File("upload");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File f = new File("upload/" + file.getOriginalFilename());
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
            return "上传成功";
        } else {
            return "上传失败，因为文件是空的.";
        }
    }

    @RequestMapping("/mutifile")
    public String mutifile() {
        return "/mutifile";
    }


    @RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    File dir = new File("upload");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    File f = new File("upload/" + file.getOriginalFilename());
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(f));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "You failed to upload " + i + " =>" + e.getMessage();
                }
            } else {
                return "You failed to upload " + i + " because the file was empty.";
            }
        }
        return "upload success";
    }
}
