package in.surajsau.chatwithbot;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by suraj on 22/4/16.
 */
public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;

    private ArrayList<Message> messages;

    private String mBuyerDp;
    private String mSellerDp;

    private Message lastMessage;

    public ChatAdapter(Context context, String buyerDp, String sellerDp) {
        mContext = context;

        lastMessage = new Message(Message.TYPE_TIME_SEPARATOR, null, 0);

        messages = new ArrayList<>();

        mBuyerDp = buyerDp;
        mSellerDp = sellerDp;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Message.TYPE_BUYER: {
                View buyerView = LayoutInflater.from(mContext).inflate(R.layout.buyer_chat_row_item, parent, false);
                return new ChatBuyerViewHolder(buyerView);
            }

            case Message.TYPE_SELLER: {
                View sellerView = LayoutInflater.from(mContext).inflate(R.layout.seller_chat_row_item, parent, false);
                return new ChatSellerViewHolder(sellerView);
            }

            case Message.TYPE_TIME_SEPARATOR: {
                View timeSeparatorView = LayoutInflater.from(mContext).inflate(R.layout.time_seperator_row_item, parent, false);
                return new ChatTimeSeparatorViewHolder(timeSeparatorView);
            }
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ChatBuyerViewHolder) {
            ChatBuyerViewHolder vh = (ChatBuyerViewHolder) holder;
            vh.tvBuyerDp.setText(mBuyerDp);
            vh.tvBuyerMessage.setText(messages.get(position).getMessage());
            vh.tvBuyerTimeStamp.setText(Util.getCurrentTimeString(messages.get(position).getTimeStamp()));
        } else if (holder instanceof ChatSellerViewHolder) {
            ChatSellerViewHolder vh = (ChatSellerViewHolder) holder;
            vh.tvSellerDp.setText(mSellerDp);
            vh.tvSellerMessage.setText(messages.get(position).getMessage());
            vh.tvSellerTimeStamp.setText(Util.getCurrentTimeString(messages.get(position).getTimeStamp()));
        } else if(holder instanceof ChatTimeSeparatorViewHolder) {
            ChatTimeSeparatorViewHolder vh = (ChatTimeSeparatorViewHolder) holder;
            vh.tvTimeIndicator.setText(Util.getCurrentTimeString(messages.get(position).getTimeStamp()));
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addMessageToList(Message message) {
        if(messages != null && message != null) {
            if(message.getUserType() == lastMessage.getUserType()) {
                messages.get(messages.size() - 1)
                        .setMessage(messages.get(messages.size() - 1)
                                .getMessage()
                                .concat("\n")
                                .concat(message.getMessage()));
                notifyItemChanged(messages.size() - 1);
            } else {
                messages.add(message);
                notifyItemInserted(messages.size());
            }

            lastMessage = message;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).getUserType();
    }

    public class ChatBuyerViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvBuyerDp) TextView tvBuyerDp;
        @Bind(R.id.tvBuyerMessage) TextView tvBuyerMessage;
        @Bind(R.id.tvBuyerTimeStamp) TextView tvBuyerTimeStamp;

        public ChatBuyerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ChatSellerViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvSellerDp) TextView tvSellerDp;
        @Bind(R.id.tvSellerMessage) TextView tvSellerMessage;
        @Bind(R.id.tvSellerTimeStamp) TextView tvSellerTimeStamp;

        public ChatSellerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ChatTimeSeparatorViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvTimeIndicator) TextView tvTimeIndicator;

        public ChatTimeSeparatorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public long getLastMessageTimeStamp() {
        return lastMessage.getTimeStamp();
    }

}
