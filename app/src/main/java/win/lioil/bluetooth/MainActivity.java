package win.lioil.bluetooth;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import win.lioil.bluetooth.ble.BleClientActivity;
import win.lioil.bluetooth.ble.BleServerActivity;
import win.lioil.bluetooth.bt.BtClientActivity;
import win.lioil.bluetooth.bt.BtServerActivity;
import win.lioil.bluetooth.util.Util;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter == null) {
            Util.toast(this, "本机没有找到蓝牙硬件或驱动");
            finish();
            return;
        } else {
            if (!adapter.isEnabled())
                adapter.enable();
        }

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Util.toast(this, "不支持低功耗蓝牙！");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE
                    , Manifest.permission.READ_EXTERNAL_STORAGE
                    , Manifest.permission.ACCESS_COARSE_LOCATION
                    , Manifest.permission.ACCESS_FINE_LOCATION};
            for (String str : permissions) {
                if (checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(permissions, 111);
                    break;
                }
            }
        }
    }

    public void btClient(View view) {
        startActivity(new Intent(this, BtClientActivity.class));
    }

    public void btServer(View view) {
        startActivity(new Intent(this, BtServerActivity.class));
    }

    public void bleClient(View view) {
        startActivity(new Intent(this, BleClientActivity.class));
    }

    public void bleServer(View view) {
        startActivity(new Intent(this, BleServerActivity.class));
    }
}
