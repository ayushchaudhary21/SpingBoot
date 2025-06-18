package net.engineeringdigest.journalApp.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Document (collection = "Users")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
  @Id
  private ObjectId userId;

  @Indexed(unique=true)
  @NonNull
  private String userName;

  @NonNull
    private String password;


  @DBRef
    private List<JournalEntity> journalEntityList=new ArrayList<>();

}
