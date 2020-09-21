package fr.eurler.invoicemanager.dao;

import java.io.File;
import java.io.InputStream;

public interface FileStorageDao {

    void uploadFile(String parentFolder, String fileName, File file);

    public InputStream getFile(String parentFolder, String fileName);
}
