
        package com.example.llama2;
        import com.example.llama2.Message;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
        public class ChatActivity extends AppCompatActivity {
    RecyclerView recyclerChat;
    EditText editMessage;
    Button btnSend;
    List<Message> messageList;
    ChatAdapter chatAdapter;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        username = getIntent().getStringExtra("USERNAME");

        recyclerChat = findViewById(R.id.recyclerChat);
        editMessage = findViewById(R.id.editMessage);
        btnSend = findViewById(R.id.btnSend);
        messageList = new ArrayList<>();

        chatAdapter = new ChatAdapter(messageList, username);
        recyclerChat.setLayoutManager(new LinearLayoutManager(this));
        recyclerChat.setAdapter(chatAdapter);

        btnSend.setOnClickListener(v -> {
            String message = editMessage.getText().toString().trim();
            if (!message.isEmpty()) {
                sendMessage(message);
                editMessage.setText("");
            }
        });
    }

    private void sendMessage(String messageText) {
        messageList.add(new Message(username, messageText));
        chatAdapter.notifyItemInserted(messageList.size() - 1);

        // TODO: Send messageText to Llama 2 backend and add response
    }
}
