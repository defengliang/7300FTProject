package hk.edu.hkbu.ftproject.entity;

import io.jmix.core.metamodel.annotation.Comment;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.eclipse.persistence.annotations.PrimaryKey;

@JmixEntity
@Table(name = "FTP_COMPANY_FILE")
@Entity(name = "ftp_CompanyFile")
public class CompanyFile {

    @Id
    @Comment("Company SYMBOL")
    @Column(name = "SYMBOL", nullable = false, length = 20)
    @NotNull
    private String symbol;

    @Column(name = "FILE_PATH", length = 1000)
    private String filePath;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "STATUS", nullable = false)
    @NotNull
    private String status;

    public ProcessStatus getStatus() {
        return status == null ? null : ProcessStatus.fromId(status);
    }

    public void setStatus(ProcessStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}