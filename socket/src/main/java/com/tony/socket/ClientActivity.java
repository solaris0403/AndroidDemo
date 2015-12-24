package com.tony.socket;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ClientActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.msg_container)
    TextView msgContainer;
    @Bind(R.id.msg)
    EditText msg;
    @Bind(R.id.send)
    Button send;

    private static final int MESSAGE_RECEIVE_NEW_MSG = 1;
    private static final int MESSAGE_SOCKET_CONNECTED = 2;

    private PrintWriter mPrintWriter;
    private Socket mClientSocket;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case MESSAGE_RECEIVE_NEW_MSG: {
                    msgContainer.setText(msgContainer.getText() + (String) message.obj);
                    break;
                }
                case MESSAGE_SOCKET_CONNECTED: {
                    send.setEnabled(true);
                    break;
                }
                default:
                    super.handleMessage(message);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcpclient);
        ButterKnife.bind(this);
        send.setOnClickListener(this);
        Intent service = new Intent(this, TCPServerService.class);
        startService(service);
        new Thread() {
            @Override
            public void run() {
                connectTCPServer();
            }
        }.start();
    }

    @SuppressLint("SimpleDateFormat")
    private String formatDateTime(long time) {
        return new SimpleDateFormat("(HH:mm:ss)").format(new Date(time));
    }

    private void connectTCPServer() {
        Socket socket = null;
        while (socket == null) {
            try {
                socket = new Socket("localhost", 8688);
                mClientSocket = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
                System.out.println("connect server success");
            } catch (IOException e) {
                e.printStackTrace();
                SystemClock.sleep(1000);
                System.out.println("connect tcp server failed, retry...");
            }
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(mClientSocket.getInputStream()));
            while (!ClientActivity.this.isFinishing()) {
                String msg = br.readLine();
                System.out.println("receive :" + msg);
                if (msg != null) {
                    String time = formatDateTime(System.currentTimeMillis());
                    final String showedMsg = "server " + time + ":" + msg + "\n";
                    mHandler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG, showedMsg).sendToTarget();
                }
            }
            System.out.println("quit...");
            MyUtils.close(mPrintWriter);
            MyUtils.close(br);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        if (mClientSocket != null) {
            try {
                mClientSocket.shutdownInput();
                mClientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        final String message = msg.getText().toString();
        if (!TextUtils.isEmpty(message) && mPrintWriter != null) {
            mPrintWriter.println(message);
            msg.setText("");
            String time = formatDateTime(System.currentTimeMillis());
            final String showedMsg = "self " + time + ":" + message + "\n";
            msgContainer.setText(msgContainer.getText() + showedMsg);
        }
    }
}
