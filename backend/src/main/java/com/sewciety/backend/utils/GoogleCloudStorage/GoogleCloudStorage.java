package com.sewciety.backend.utils.GoogleCloudStorage;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;
import java.util.UUID;

/**
 * Simple class for creating, reading and modifying text blobs on Google Cloud
 */
public class GoogleCloudStorage {

    private static Storage storage = StorageOptions.getDefaultInstance().getService();

    public static String upload(String file) throws IOException {
        try {
            byte[] bytes = Base64.decodeBase64(file);
            BlobInfo blobInfo = storage.create(
                    BlobInfo.newBuilder("sewciety-pattern-images", UUID.randomUUID().toString()).setContentType("image")
                            .build(),
                    bytes);
            return blobInfo.getMediaLink(); // Return file url
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

}