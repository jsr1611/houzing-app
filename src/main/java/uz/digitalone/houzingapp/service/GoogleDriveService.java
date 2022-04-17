package uz.digitalone.houzingapp.service;

import com.google.api.services.drive.model.File;

public interface GoogleDriveService {
    File uploadFile(String filePath);

}
