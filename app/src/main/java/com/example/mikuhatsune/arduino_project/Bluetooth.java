package com.example.mikuhatsune.arduino_project;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * Created by MikuHatsune on 2016/2/19.
 */
public class Bluetooth {
    private BluetoothAdapter adapter;
    private BluetoothSocket socket;
    private BluetoothDevice device;
    private OutputStream outputStream;
    private InputStream inputStream;
    private String transmitEncode = "big5";

    public Bluetooth(Activity activity, String BTname, String transmitEncode){
        this.transmitEncode = transmitEncode;
        start(activity, BTname);
    }

    public Bluetooth(Activity activity, String BTname) {
        start(activity, BTname);
    }

    void sendData(String msg) throws IOException
    {
        msg += "\r\n";
        outputStream.write(msg.getBytes());
        outputStream.write(msg.getBytes(transmitEncode)); //才不會有亂碼
    }

    void start(Activity activity, String BTname){
        adapter.isMultipleAdvertisementSupported();

        Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(activity, enableBluetooth, 0, null);

        Set<BluetoothDevice> pairedDevices = adapter.getBondedDevices();

        if(pairedDevices.size() > 0)
        {
            for(BluetoothDevice device : pairedDevices)
            {
                // 在已連線的list中找 BTname
                if(device.getName().equals(BTname))
                {
                    this.device = device;
                    break;
                }
            }
        }

        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService ID
        try {
            socket = device.createRfcommSocketToServiceRecord(uuid);
            socket.connect();
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    byte [] readResult(){
        byte[] result = new byte [200];
        try {
            inputStream.read(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    void close(){
        try {
            socket.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
