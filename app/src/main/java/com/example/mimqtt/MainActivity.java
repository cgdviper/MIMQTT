package com.example.mimqtt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
public class MainActivity extends AppCompatActivity {
    static String server = "node02.myqtthub.com:1883";
    static String user = "nightwolf";
    static String pass = "ExiuVAEG-fC5o7CNq";
    static String TOPIC = "mensaje";
    public MqttAndroidClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(),server,clientId);
        MqttConnectOptions opt = new MqttConnectOptions();
        opt.setCleanSession(true);
        opt.setKeepAliveInterval(30);
        opt.setUserName(user);
        opt.setPassword(pass.toCharArray());
        /*
        try {
            IMqttToken token = client.connect(opt);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Toast.makeText(getBaseContext(),"conectado !!!",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Toast.makeText(getBaseContext(),"no se ha conectado",Toast.LENGTH_LONG).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }*/

        try{
            client.connect(opt, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(getBaseContext(),"conectado !!!",Toast.LENGTH_LONG).show();
                }
                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(getBaseContext(),"no se ha conectado",Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e) {
            Toast.makeText(getBaseContext(),"Error conexion al server",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }





    }
    public void mensajear(View v)
    {
        String topic = TOPIC;
        String mensaje = "Este es un mensaje";
        try{
            client.publish(topic,mensaje.getBytes(),0,false);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

}