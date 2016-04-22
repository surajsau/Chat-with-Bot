package in.surajsau.chatwithbot;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by suraj on 22/4/16.
 */
public interface ChatClient {

    @GET("v2/chatbot/")
    Observable<ChatResponse> getChatResponse(
            @Query("bot_id") int botId,
            @Query("say") String message,
            @Query("convo_id") String convoId,
            @Query("format") String responseFormat
    );
}
