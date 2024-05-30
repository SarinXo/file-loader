package fileloader.downloaderapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

import static fileloader.downloaderapp.model.entity.AppSchema.DOCUMENT_TABLE;
import static fileloader.downloaderapp.model.entity.AppSchema.SCHEMA;
import static java.sql.Types.BINARY;

@Entity
@Table(schema = SCHEMA,
       name = DOCUMENT_TABLE)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {
    @Id
    @UuidGenerator
    @Column(name = Field.Database.ID)
    private UUID id;

    @JdbcTypeCode(BINARY)
    @Column(name = Field.Database.FILE, columnDefinition = "bytea")
    private byte[] file;

    @Column(name = Field.Database.FILE_NAME)
    private String fileName;

    public FileEntity(byte[] file, String fileName) {
        this.file = file;
        this.fileName = fileName;
    }

    public static class Field{
        public static class Entity{
            public final static String ID = "id";
            public final static String FILE = "file";
            public static final String FILE_NAME = "fileName";
        }
        public static class Database{
            public final static String ID = "id";
            public final static String FILE = "file";
            public static final String FILE_NAME = "file_name";
        }
    }
}
