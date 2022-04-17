package uz.digitalone.houzingapp.service;

import com.google.api.services.drive.model.File;

public interface GoogleDriveApiService {
    /**
     *
     * @param filePath fileName on host
     * @param folderId ID folder on google drive
     * @return
     */
    File uploadFile(String filePath, String folderId);

}
