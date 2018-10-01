package hackathon;
/**
 * Copyright 2018, Charter Communications,  All rights reserved.
 */

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author jkendall1
 */
@Document(collection = "twitterinfo")
@Getter
@Setter
public class TwitterInfo {
    @Id
    private Object _id;
    private String timestamp;
    private String author;
    private String text;
    private String sentiment;

    public String getText() {
        return this.text;
    }
}
