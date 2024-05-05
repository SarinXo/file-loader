package ilya.coursework.fileloader.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

import static ilya.coursework.fileloader.model.entity.AppSchema.DOCUMENT_TABLE;
import static ilya.coursework.fileloader.model.entity.AppSchema.SCHEMA;

@Entity
@Table(schema = SCHEMA,
       name = DOCUMENT_TABLE)
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class FileEntity {
    @Id
    @UuidGenerator
    @Column(name = Field.Database.ID)
    private UUID id;
    @Lob
    @Column(name = Field.Database.FILE, columnDefinition = "BLOB")
    private byte[] file;

    public static class Field{
        public static class Entity{
            public final static String ID = "id";
            public final static String FILE = "file";

        }
        public static class Database{
            public final static String ID = "id";
            public final static String FILE = "file";
        }
    }
}
