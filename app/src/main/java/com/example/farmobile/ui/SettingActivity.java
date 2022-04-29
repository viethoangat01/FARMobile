package com.example.farmobile.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.farmobile.data.db.DBHandler;
import com.example.farmobile.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.farmobile.utils.CommonUtils.loadJSONFromAsset;

public class SettingActivity extends AppCompatActivity {
    private Button btnAddIp,btnConnect;
    private EditText edtBroker,edtTopic,edtIp;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        dbHandler=new DBHandler(SettingActivity.this);
        initView();
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtBroker.getText().toString().isEmpty()&&!edtTopic.getText().toString().isEmpty()){
                    connectToKafka();
                    saveDataFromKafka();
                }
            }
        });

    }

    private void connectToKafka() {

    }

    private void saveDataFromKafka() {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(SettingActivity.this,"kafka.json"));
            JSONArray m_jArry = obj.getJSONArray("formules");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {
//                JSONObject jo_inside = m_jArry.getJSONObject(i);
//                Log.d("Details-->", jo_inside.getString("formule"));
//                String formula_value = jo_inside.getString("formule");
//                String url_value = jo_inside.getString("url");
//
//                //Add your values in your `ArrayList` as below:
//                m_li = new HashMap<String, String>();
//                m_li.put("formule", formula_value);
//                m_li.put("url", url_value);
//
//                formList.add(m_li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        btnConnect=(Button) findViewById(R.id.button_setting_connect);
        btnAddIp=(Button) findViewById(R.id.button_setting_addip);

        edtBroker=(EditText) findViewById(R.id.edittext_setting_broker);
        edtTopic=(EditText) findViewById(R.id.edittext_setting_topic);
        edtIp=(EditText) findViewById(R.id.edittext_setting_ip);
    }
}
