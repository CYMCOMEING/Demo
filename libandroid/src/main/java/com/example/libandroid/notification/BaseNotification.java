package com.example.libandroid.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.widget.RemoteViews;

import com.example.libandroid.R;

public class BaseNotification {

    private NotificationManager manager;

    public void sendNormalNotification(Context context){
        Notification.Builder builder = getNotificationBuilder(context);
        getManager(context).notify(1, builder.build());
    }

    public void sendProgressNotification(final Context context){
        final Notification.Builder builder = getNotificationBuilder(context);
        builder.setDefaults(Notification.FLAG_ONLY_ALERT_ONCE);
        getManager(context).notify(2, builder.build());

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++){
                    try {
                        Thread.sleep(1000);
                        builder.setDefaults(Notification.FLAG_ONLY_ALERT_ONCE);
                        builder.setProgress(100, i, false);
                        getManager(context).notify(2, builder.build());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 自定义通知栏
     * @param context
     * @param activityClass 跳转的页面
     */
    public void sendCustomNotification(Context context, Class activityClass){
        Notification.Builder builder = getNotificationBuilder(context);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_custom_notification);
        remoteViews.setTextViewText(R.id.notification_title, "custom_title");
        remoteViews.setTextViewText(R.id.notification_content, "custom_content");

        // 即将要发生的意图 它时可以被取消 更新
        Intent intent = new Intent(context,activityClass);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, -1, intent,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.turn_next, pendingIntent);
//        remoteViews.setOnClickFillInIntent(R.id.turn_next, intent);

        builder.setCustomBigContentView(remoteViews);

        getManager(context).notify(3, builder.build());
    }

    private NotificationManager getManager(Context context){
        if (manager == null){
            manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    private Notification.Builder getNotificationBuilder(Context context){

        NotificationManager manager;
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_DEFAULT);
            channel.canBypassDnd(); // 可否绕过请勿打扰模式
            channel.enableLights(true); // 闪光
            channel.setLockscreenVisibility(Notification.VISIBILITY_SECRET); // 锁屏显示通知
            channel.setLightColor(Color.RED); // 指定闪光时的颜色
            channel.canShowBadge(); // 桌面laucnher消息角标
            channel.enableVibration(true); // 是否允许震动
            channel.getAudioAttributes(); // 获取系统通知铃声声音的配置
            channel.getGroup(); // 获取通知渠道组
            channel.setBypassDnd(true); // 设置可绕过 请勿打扰模式
            channel.setVibrationPattern(new long[]{100, 100, 200}); // 震动的模式
            channel.shouldShowLights(); // 是否会闪光灯

            getManager(context).createNotificationChannel(channel);
        }

        return new Notification.Builder(context)
                .setAutoCancel(true)
                .setChannelId("channel_id")
                .setContentTitle("新消息，标题")
                .setContentText("消息，内容")
                .setSmallIcon(R.drawable.icon_red);
//                    .setStyle(new Notification.BigTextStyle())
    }
}
