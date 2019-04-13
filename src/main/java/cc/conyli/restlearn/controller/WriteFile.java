package cc.conyli.restlearn.controller;

import cc.conyli.restlearn.integration.FileWriterGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/write")
public class WriteFile {

    private FileWriterGateway fileWriterGateway;

    @Autowired
    public WriteFile(FileWriterGateway fileWriterGateway) {
        this.fileWriterGateway = fileWriterGateway;
    }

    @GetMapping
    public String writeToFile(@RequestParam("data") String data) {
        System.out.println(data);

        fileWriterGateway.writeToFile("myfile.txt", data);
        return "message";
    }
}
