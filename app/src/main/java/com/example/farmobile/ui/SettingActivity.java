package com.example.farmobile.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmobile.R;
import com.example.farmobile.data.db.DBHandler;
import com.example.farmobile.data.prefs.AppPreferencesHelper;
import com.example.farmobile.ui.adapter.IpRecyclerAdapter;
import com.example.farmobile.utils.AppConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.farmobile.utils.CommonUtils.isValidInet4Address;
import static com.example.farmobile.utils.CommonUtils.loadJSONFromAsset;

public class SettingActivity extends AppCompatActivity {
    private Button btnAddIp,btnConnect;
    private EditText edtBroker,edtTopic,edtIp;
    private RecyclerView recyclerViewIp;
    private IpRecyclerAdapter ipRecyclerAdapter;

    private List<String> ipList;

    private DBHandler dbHandler;
    private AppPreferencesHelper preferencesHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initData();
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
                if (isValidInet4Address(ipAdded) && !ipList.contains(ipAdded)) {
                        ipList.add(edtIp.getText().toString());
                        ipRecyclerAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(SettingActivity.this, R.string.setting_notify_invalid_ip, Toast.LENGTH_SHORT).show();
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
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset(SettingActivity.this, "seed/kafka.json"));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String prediction = jsonObject.getString("prediction");
                int modelVersion = jsonObject.getInt("mlflow_model_version");
                String testDateTime = jsonObject.getString("test_datetime");
                String ip = jsonObject.getString("ip");
                String un = jsonObject.getString("u_n");
                String failItem = jsonObject.getString("failitem");
                String kafkaTime = jsonObject.getString("kafka_time");
                dbHandler.addNewKafka(prediction, modelVersion, testDateTime, ip, un, failItem, kafkaTime);
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
        //Event click delete recyclerview
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                ipList.remove(viewHolder.getAdapterPosition());
                ipRecyclerAdapter.notifyDataSetChanged();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewIp);
    }

    private void initView() {
        btnConnect = findViewById(R.id.button_setting_connect);
        btnAddIp = findViewById(R.id.button_setting_addip);

        edtBroker = findViewById(R.id.edittext_setting_broker);
        edtTopic = findViewById(R.id.edittext_setting_topic);
        edtIp = findViewById(R.id.edittext_setting_ip);

        recyclerViewIp = findViewById(R.id.recyclerview_setting_iplist);
    }

    private void initData() {
        dbHandler=new DBHandler(SettingActivity.this);
        preferencesHelper = new AppPreferencesHelper(this, AppConstants.PREF_NAME);
    }
}
