package uz.digitalone.houzingapp;

import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import uz.digitalone.houzingapp.service.GoogleDriveService;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootApplication
public class TestUpload {

    @Autowired
    GoogleDriveService driveService;

    @Test
    public void testUpload() {

        File file = new File("D:\\rasmlar\\maxresdefault.jpg");

        com.google.api.services.drive.model.File file2 = driveService.uploadFile(file.getAbsolutePath());
        try {
            System.out.println(file2.toPrettyString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
