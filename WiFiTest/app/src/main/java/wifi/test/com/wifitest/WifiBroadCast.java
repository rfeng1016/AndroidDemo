package wifi.test.com.wifitest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

/**
 * Created by F_Ruan on 16/9/21.
 */
public class WifiBroadCast extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent){

        if (intent.getAction().equals(WifiManager.RSSI_CHANGED_ACTION)){

        } else  if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)){
            System.out.println("网络状态改变");
            NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);

            // 如果断开连接
            if (info.getState().equals(NetworkInfo.State.DISCONNECTED)){
                System.out.println("wifi网络连接断开");
            }

            if (info.getState().equals(NetworkInfo.State.CONNECTING)){
                System.out.println("连接到wifi网络");
            }

        }else if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)){
            // wifi开关
            int wifistate = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,WifiManager.WIFI_STATE_DISABLED);
            // 如果关闭
            if (wifistate == WifiManager.WIFI_STATE_DISABLED){
                System.out.println("系统开发关闭wifi");
            }
            if (wifistate == WifiManager.WIFI_STATE_ENABLED){
                System.out.println("系统开发开启wifi");
            }
        }

    }
}
