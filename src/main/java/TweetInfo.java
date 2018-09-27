import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class TweetInfo {

    @Id
    private String timeStamp;
    private String author;
    private String text;
    private String sentiment;
}
