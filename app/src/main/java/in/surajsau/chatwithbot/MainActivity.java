package in.surajsau.chatwithbot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.rlChat)
    RecyclerView rlChat;

    @Bind(R.id.etMessage)
    EditText etMessage;

    private ChatAdapter mAdapter;

    private ChatClient mClient;

    private static final String FORMAT_JSON = "json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mClient = ServiceGenerator.createService(ChatClient.class);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mAdapter = new ChatAdapter(this, "AB", "VK");
        LinearLayoutManager llChat = new LinearLayoutManager(this);
        llChat.setStackFromEnd(true);
        llChat.setReverseLayout(false);

        rlChat.setLayoutManager(llChat);
        rlChat.setAdapter(mAdapter);
    }

    @OnClick(R.id.btnSend)
    void sendMessage() {
        String userMessage = etMessage.getText().toString();
        etMessage.setText("");

        addToList(userMessage, Message.TYPE_SELLER);

        Observable<ChatResponse> response =
                mClient.getChatResponse(6, userMessage, "Example1234232", FORMAT_JSON);

        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<ChatResponse, String>() {
                    @Override
                    public String call(ChatResponse chatResponse) {
                        return chatResponse != null ? chatResponse.getBotsay() : null;
                    }
                })
                .subscribe(new ChatMessageSubscriber());
    }

    public class ChatMessageSubscriber extends Subscriber<String> {

        @Override
        public void onCompleted() {
            Log.d(TAG, "Completed");
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, e.getMessage());
        }

        @Override
        public void onNext(String s) {
            addToList(s, Message.TYPE_BUYER);
        }
    }

    private void addToList(String message, @Message.UserType int userType) {
        long currentTime =System.currentTimeMillis();

        mAdapter.addMessageToList(new Message(userType, message, currentTime));

        rlChat.smoothScrollToPosition(mAdapter.getItemCount() - 1);

    }
}
