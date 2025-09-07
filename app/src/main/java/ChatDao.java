// ChatDao.java
import androidx.room.*;
import java.util.List;

@Dao
public interface ChatDao {
    @Query("SELECT * FROM chat_messages ORDER BY timestamp ASC")
    List<ChatMessage> getAllMessages();

    @Insert
    void insertMessage(ChatMessage message);

    @Delete
    void deleteMessage(ChatMessage message);

    @Query("DELETE FROM chat_messages")
    void clearAllMessages();
}
