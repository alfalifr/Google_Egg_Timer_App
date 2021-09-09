/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.eggtimernotifications.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.android.eggtimernotifications.MainActivity
import com.example.android.eggtimernotifications.R
import com.example.android.eggtimernotifications.receiver.SnoozeReceiver

// Notification ID.
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0

// TODO: Step 1.1 extension function to send messages (GIVEN)
/**
 * Builds and delivers the notification.
 *
 * @param context, activity context.
 */
fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {
    // Create the content intent for the notification, which launches
    // this activity
    // TODO: Step 1.11 create intent

    // TODO: Step 1.12 create PendingIntent

    // TODO: Step 2.0 add style

    // TODO: Step 2.2 add snooze action

    // TODO: Step 1.2 get an instance of NotificationCompat.Builder
    // Build the notification

    // TODO: Step 1.8 use the new 'breakfast' notification channel

    // TODO: Step 1.3 set title, text and icon to builder

    // TODO: Step 1.13 set content intent

        // TODO: Step 2.1 add style to builder

        // TODO: Step 2.3 add snooze action

        // TODO: Step 2.5 set priority

    // TODO: Step 1.4 call notify

  val openAppIntent = Intent(applicationContext, MainActivity::class.java)
  val openAppPi = PendingIntent.getActivity(
    applicationContext,
    REQUEST_CODE,
    openAppIntent,
    PendingIntent.FLAG_UPDATE_CURRENT
  )

  val snoozeIntent = Intent(applicationContext, SnoozeReceiver::class.java)
  val snoozeActionPi = PendingIntent.getBroadcast(
    applicationContext,
    REQUEST_CODE,
    snoozeIntent,
    PendingIntent.FLAG_ONE_SHOT,
  )

  val bigPic = BitmapFactory.decodeResource(
    applicationContext.resources,
    R.drawable.cooked_egg,
  )
  val bigPicStyle = NotificationCompat.BigPictureStyle()
    .bigLargeIcon(null)
    .bigPicture(bigPic)

  val notifBuilder = NotificationCompat.Builder(
    applicationContext,
    applicationContext.getString(R.string.egg_notification_channel_id)
  ).setSmallIcon(R.drawable.cooked_egg)
    .setContentTitle(applicationContext.getString(R.string.notification_title))
    .setContentText(messageBody)
    .setContentIntent(openAppPi)
    .addAction(
      R.drawable.egg_icon,
      applicationContext.getString(R.string.snooze),
      snoozeActionPi,
    )
    .setStyle(bigPicStyle)
    .setAutoCancel(true)

    if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.N_MR1) {
      notifBuilder.priority = Notification.PRIORITY_HIGH
    }

  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    val channel = NotificationChannel(
      applicationContext.getString(R.string.egg_notification_channel_id),
      applicationContext.getString(R.string.egg_notification_channel_name),
      NotificationManager.IMPORTANCE_HIGH
    ).apply {
      enableLights(true)
      enableVibration(true)
      lightColor = Color.RED
      vibrationPattern = longArrayOf(
        0, 100, 100, 100, 500, 100, 100, 100, 500, 100, 100, 100, 200, 100, 100, 100, 200, 100, 100, 100,
        500, 100, 100, 100, 100, 1000, 400, 3000,

        1000,

        100, 100, 100, 500, 100, 100, 100, 500, 100, 100, 100, 200, 100, 100, 100, 200, 100, 100, 100,
        500, 100, 100, 100, 100, 1000, 400, 3000,

        1000,

        100, 100, 100, 500, 100, 100, 100, 500, 100, 100, 100, 200, 100, 100, 100, 200, 100, 100, 100,
        500, 100, 100, 100, 100, 1000, 400, 3000,
      )
    }
    createNotificationChannel(channel)
  }

  notify(
    NOTIFICATION_ID,
    notifBuilder.build()
  )
}

// TODO: Step 1.14 Cancel all notifications
fun NotificationManager.cancellAllNotif() {
  cancelAll()
}