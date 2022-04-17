package uz.digitalone.houzingapp.service.impl;

;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uz.digitalone.houzingapp.service.GoogleDriveService;
import org.slf4j.Logger;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Collections;


@Service
public class GoogleDriveServiceImpl implements GoogleDriveService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleDriveServiceImpl.class);

    @Value("${google.service_account_email}")
    private String serviceAccountEmail;

    @Value("${google.application_name}")
    private String applicationName;

    @Value("${google.service_account_key}")
    private String serviceAccountKey;

    @Value("${google.folder_id}")
    private String folderId;


    public Drive getDriveService() {
        Drive service = null;

        try {
            URL resource = GoogleDriveServiceImpl.class.getResource("/"+this.serviceAccountKey);

            java.io.File file = Paths.get(resource.toURI()).toFile();

            HttpTransport httpTransport = new NetHttpTransport();
            JacksonFactory jacksonFactory = new JacksonFactory();

            com.google.api.client.googleapis.auth.oauth2.GoogleCredential credential = new GoogleCredential.Builder()
                    .setTransport(httpTransport)
                    .setJsonFactory(jacksonFactory).setServiceAccountId(serviceAccountEmail)
                    .setServiceAccountScopes(Collections.singleton(com.google.api.services.drive.DriveScopes.DRIVE))
                    .setServiceAccountPrivateKeyFromPemFile(file).build();
            service = new Drive.Builder(httpTransport, jacksonFactory, credential).setApplicationName(applicationName)
                    .setHttpRequestInitializer(credential).build();


        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return service;

    }




    @Override
    public File uploadFile(String filePath) {
        File file = new File();
        try {
            java.io.File fileUpload = new java.io.File(filePath);
            com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
//            fileMetadata.setMimeType("jpeg");
//            if (file.getName() == null)
//                fileMetadata.setName(fileName);
            fileMetadata.setParents(Collections.singletonList(folderId));
            com.google.api.client.http.FileContent fileContent = new FileContent("jpeg/image", fileUpload);
            file = getDriveService().files().create(fileMetadata, fileContent)
                    .setFields("id")
                    .execute();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return file;
    }
}
