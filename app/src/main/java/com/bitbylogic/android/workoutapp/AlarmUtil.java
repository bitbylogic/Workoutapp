package com.bitbylogic.android.workoutapp;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.Log;

import java.io.IOException;

@SuppressWarnings("ConstantConditions")
final class AlarmUtil {

    private static final String TAG = AlarmUtil.class.getSimpleName();
    private static final float BEEP_VOLUME = 1.0f;
    private static final long VIBRATE_DURATION = 1000L;
    private static final long[] vibPatern = {1000L, 500L};
    private final Activity activity;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private final boolean vibrate = true;

    AlarmUtil(Activity activity) {
        this.activity = activity;
        this.mediaPlayer = null;
        updatePrefs();
    }

    void updatePrefs() {
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        playBeep = shouldBeep(activity);
      //  vibrate = prefs.getBoolean(PreferencesActivity.KEY_VIBRATE, false);
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it too loud,
            // so we now play on the music stream.
            activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = buildMediaPlayer(activity);
        }
    }

    void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {

                    mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
            vibrator.vibrate(vibPatern, 0);
        }
    }

    void stopBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.cancel();
        }
    }

    private static boolean shouldBeep(Context activity) {
        //boolean shouldPlayBeep = prefs.getBoolean(PreferencesActivity.KEY_PLAY_BEEP, true);
        boolean shouldPlayBeep = true;
        if (shouldPlayBeep) {
            // See if sound settings overrides this
            AudioManager audioService = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
            if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
                shouldPlayBeep = false;
            }
        }
        return shouldPlayBeep;
    }

    private static MediaPlayer buildMediaPlayer(Context activity) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // When the beep has finished playing, rewind to queue up another one.
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                player.seekTo(0);
            }
        });

        AssetFileDescriptor file = activity.getResources().openRawResourceFd(R.raw.alert_3);
        try {
            mediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
            file.close();
            mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
        } catch (IOException ioe) {
            Log.w(TAG, ioe);
            mediaPlayer = null;
        }
        return mediaPlayer;
    }
}