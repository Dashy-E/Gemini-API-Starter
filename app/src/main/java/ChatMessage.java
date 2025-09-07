// ChatMessage.java
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chat_messages")
public class ChatMessage {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String message;
    private String response;
    private long timestamp;
    private boolean isUser;

    // Constructors
    public ChatMessage() {}

    public ChatMessage(String message, String response, boolean isUser) {
        this.message = message;
        this.response = response;
        this.isUser = isUser;
        this.timestamp = System.currentTimeMillis();
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

    public boolean isUser() { return isUser; }
    public void setUser(boolean user) { isUser = user; }
}
