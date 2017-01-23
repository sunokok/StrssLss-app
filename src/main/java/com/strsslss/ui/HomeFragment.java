package com.strsslss.ui;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cardiomood.android.controls.progress.CircularProgressBar;
import com.felhr.usbserial.UsbSerialDevice;
import com.felhr.usbserial.UsbSerialInterface;
import com.github.yongjhih.mismeter.MisMeter;
import com.strsslss.R;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SunOK on 13/12/2016.
 */

public class HomeFragment extends Fragment {

    @BindView(R.id.buttonStart)
    Button beginButton;

    @BindView(R.id.buttonSend)
    Button sendButton;

    @BindView(R.id.buttonClear)
    Button clearButton;

    @BindView(R.id.buttonStop)
    Button stopButton;

    @BindView(R.id.editTextSerial)
    EditText editTextSerial;


    public final String ACTION_USB_PERMISSION = "com.hariharan.arduinousb.USB_PERMISSION";

    @BindView(R.id.textViewSerial)
    TextView textViewSerial;

    @BindView(R.id.meter)
    CircularProgressBar meter;

    UsbManager usbManager;
    UsbDevice device;
    UsbSerialDevice serialPort;
    UsbDeviceConnection connection;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_home , container, false);
        ButterKnife.bind(this, rootView);

        usbManager = (UsbManager) getContext().getSystemService(getActivity().USB_SERVICE);
        setUiEnabled(false);

        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);

        getActivity().registerReceiver(broadcastReceiver, filter);

        meter.setLabelConverter(new CircularProgressBar.LabelConverter() {
            @Override
            public String getLabelFor(float progress, float max, Paint paint) {
                return "Calm";
            }
        });
        meter.setLineWidth(6);
        meter.setColor(Color.BLUE);
        meter.setTextColor(Color.BLUE);
        meter.setMin(0);
        meter.setProgress(25);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Home");
    }

    UsbSerialInterface.UsbReadCallback mCallback = new UsbSerialInterface.UsbReadCallback() { //Defining a Callback which triggers whenever data is read.
        @Override
        public void onReceivedData(byte[] arg0) {
            String data = null;
            try {
                data = new String(arg0, "UTF-8");
                data.concat("/n");
                tvAppend(textViewSerial, data);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


        }
    };

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { //Broadcast Receiver to automatically start and stop the Serial connection.
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION_USB_PERMISSION)) {
                boolean granted = intent.getExtras().getBoolean(UsbManager.EXTRA_PERMISSION_GRANTED);
                if (granted) {
                    connection = usbManager.openDevice(device);
                    serialPort = UsbSerialDevice.createUsbSerialDevice(device, connection);
                    if (serialPort != null) {
                        if (serialPort.open()) { //Set Serial Connection Parameters.
                            setUiEnabled(true);
                            serialPort.setBaudRate(9600);
                            serialPort.setDataBits(UsbSerialInterface.DATA_BITS_8);
                            serialPort.setStopBits(UsbSerialInterface.STOP_BITS_1);
                            serialPort.setParity(UsbSerialInterface.PARITY_NONE);
                            serialPort.setFlowControl(UsbSerialInterface.FLOW_CONTROL_OFF);

                            // Reading from the serial port
                            serialPort.read(mCallback);
                            tvAppend(textViewSerial,"Serial Connection Opened!\n");

                        } else {
                            Log.d("SERIAL", "PORT NOT OPEN");
                        }
                    } else {
                        Log.d("SERIAL", "PORT IS NULL");
                    }
                } else {
                    Log.d("SERIAL", "PERM NOT GRANTED");
                }
            } else if (intent.getAction().equals(UsbManager.ACTION_USB_DEVICE_ATTACHED)) {
                onClickBegin();
            } else if (intent.getAction().equals(UsbManager.ACTION_USB_DEVICE_DETACHED)) {
                onClickStop();

            }
        }

        ;
    };

    public void setUiEnabled(boolean bool) {
        beginButton.setEnabled(!bool);
        sendButton.setEnabled(!bool);
        stopButton.setEnabled(bool);
        textViewSerial.setEnabled(bool);
    }

    @OnClick(R.id.buttonStart)
    public void onClickBegin() {

        HashMap<String, UsbDevice> usbDevices = usbManager.getDeviceList();
        if (!usbDevices.isEmpty()) {
            boolean keep = true;
            for (Map.Entry<String, UsbDevice> entry : usbDevices.entrySet()) {
                device = entry.getValue();
                int deviceVID = device.getVendorId();
                if (deviceVID == 0x2341)//Arduino Vendor ID
                {
                    PendingIntent pi = PendingIntent.getBroadcast(getActivity(), 0, new Intent(ACTION_USB_PERMISSION), 0);
                    usbManager.requestPermission(device, pi);
                    keep = false;
                } else {
                    connection = null;
                    device = null;
                }

                if (!keep)
                    break;
            }
        }


    }

    // send will be used to send the start breathing function
    @OnClick(R.id.buttonSend)
    public void onClickSend() {
        String string = editTextSerial.getText().toString() + "\n";
        serialPort.write("B".getBytes());
        // print the sent data - useful for debugging
        // tvAppend(textViewSerial, "\nData Sent : " + string + "\n");
    }

    @OnClick(R.id.buttonStop)
    public void onClickStop() {
        setUiEnabled(false);
        serialPort.close();

        tvAppend(textViewSerial,"\nSerial Connection Closed! \n");
    }

    @OnClick(R.id.buttonClear)
    public void onClickClear() {
        textViewSerial.setText(" ");
    }

    private void tvAppend(TextView tv, CharSequence text) {
        final TextView ftv = tv;
        final CharSequence ftext = text;

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ftv.append(ftext);
            }
        });
    }

}
