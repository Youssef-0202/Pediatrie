package ma.zs.gestion_service_pediatrie.ws.dto.commun;

public class UploadResponse {
    private String uploadedFilePath;

    public UploadResponse(String uploadedFilePath) {
        this.uploadedFilePath = uploadedFilePath;
    }

    public String getUploadedFilePath() {
        return uploadedFilePath;
    }

    public void setUploadedFilePath(String uploadedFilePath) {
        this.uploadedFilePath = uploadedFilePath;
    }
}
