package net.engineeringdigest.journalApp.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;


@Document (collection = "journal_Entry")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class JournalEntity {

//        @Indexed(unique=true)
//        private String journalId;  // indexed used to create every journalId unique
         @Id
        private ObjectId journalId;

        private String title;
        private String  content;
        private LocalDateTime date;
}
