package org.loader.nettest;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.loader.andnet.net.Net;
import org.loader.andnet.net.RequestParams;
import org.loader.andnet.net.Result;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.tv);

        Net.get("http://192.168.3.116/?name=loader&age=18&city=jinan",
                new CommParser<User>("user") {}, new Net.Callback<User>() {
            @Override
            public void callback(Result<User> result) {
                if(result.getStatus() == Result.SUCCESS) {
                    User user = result.getResult();

                    mTextView.setText(user.getName());
                    mTextView.append("\n" + user.getAge());
                    mTextView.append("\n" + user.getCity());
                }else {
                    mTextView.setText(result.getMsg());
                }
            }
        });


//        RequestParams params = new RequestParams("name", "loader");
//        params.add("age", 18).add("city", "jinan");
//        Net.post("http://192.168.3.116/", params, new CommParser<User>("user") {
//                },
//                new Net.Callback<User>() {
//                    @Override
//                    public void callback(Result<User> result) {
//                        if(result.getStatus() == Result.SUCCESS) {
//                            User user = result.getResult();
//
//                            mTextView.setText(user.getName());
//                            mTextView.append("\n" + user.getAge());
//                            mTextView.append("\n" + user.getCity());
//                        }else {
//                            mTextView.setText(result.getMsg());
//                        }
//                    }
//                });


//        User user = new User();
//        user.setName("qibin");
//        user.setCity("shandong");
//        user.setAge(18);
//
//        Net.post("http://192.168.3.116/", user, new CommParser<User>("user") {
//                }, new Net.Callback<User>() {
//                    @Override
//                    public void callback(Result<User> result) {
//                        if(result.getStatus() == Result.SUCCESS) {
//                            mTextView.setText(result.getResult().toString());
//                        }else {
//                            mTextView.setText(result.getMsg());
//                        }
//                    }
//                });

//        RequestParams params = new RequestParams("name", "qibin");
//        params.add("file", new File(Environment.getExternalStorageDirectory()
//                + "/dl.jar"));
//        Net.post("http://192.168.3.116/upload.php", params, new Net.NoParser(),
//                new Net.Callback<String>() {
//            @Override
//            public void callback(Result<String> result) {
//                mTextView.setText(result.getResult() + "");
//            }
//        });
    }
}
