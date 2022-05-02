package com.example.farmobile.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.farmobile.data.db.DBHandler;
import com.example.farmobile.R;
import com.example.farmobile.ui.adapter.IpRecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.farmobile.utils.CommonUtils.isValidInet4Address;
import static com.example.farmobile.utils.CommonUtils.loadJSONFromAsset;

public class SettingActivity extends AppCompatActivity {
    private Button btnAddIp,btnConnect;
    private EditText edtBroker,edtTopic,edtIp;
    private DBHandler dbHandler;
    private RecyclerView recyclerViewIp;

    private IpRecyclerAdapter ipRecyclerAdapter;

    List<String> ipList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initDatabase();
        initVariable();
        initView();
        initRecyclerView();

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtBroker.getText().toString().isEmpty()&&!edtTopic.getText().toString().isEmpty()){
                    connectToKafka();
                    saveDataFromKafka();
                }
            }
        });

        btnAddIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ipAdded=edtIp.getText().toString();
                if(isValidInet4Address(ipAdded)){
                    if(!ipList.contains(ipAdded)) {
                        ipList.add(edtIp.getText().toString());
                        ipRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void initVariable() {
        ipList= new ArrayList<String>();
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

    private void initRecyclerView() {
        ipRecyclerAdapter=new IpRecyclerAdapter(ipList,this);
        @SuppressLint("WrongConstant") LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        recyclerViewIp.setLayoutManager(linearLayoutManager);
        recyclerViewIp.setAdapter(ipRecyclerAdapter);
        recyclerViewIp.setFocusable(false);
        recyclerViewIp.setNestedScrollingEnabled(false);
    }

    private void initView() {
        btnConnect=(Button) findViewById(R.id.button_setting_connect);
        btnAddIp=(Button) findViewById(R.id.button_setting_addip);

        edtBroker=(EditText) findViewById(R.id.edittext_setting_broker);
        edtTopic=(EditText) findViewById(R.id.edittext_setting_topic);
        edtIp=(EditText) findViewById(R.id.edittext_setting_ip);

        recyclerViewIp=(RecyclerView) findViewById(R.id.recyclerview_setting_iplist);
    }

    private void initDatabase() {
        dbHandler=new DBHandler(SettingActivity.this);
    }
}
