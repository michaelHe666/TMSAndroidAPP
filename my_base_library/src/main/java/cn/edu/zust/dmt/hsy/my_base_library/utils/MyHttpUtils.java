package cn.edu.zust.dmt.hsy.my_base_library.utils;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.edu.zust.dmt.hsy.my_base_library.constants.MyAppSettings;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/24/2020 11:07
 **/
public final class MyHttpUtils {
    /**
     * @description {@link MyHttpUtils} should only be initialized in {@link MyHttpUtilsHolder#INSTANCE}
     */
    private MyHttpUtils() {
    }

    /**
     * @description inner static class for hold instance and ensure lazy-load singleton
     */
    private static final class MyHttpUtilsHolder {
        /**
         * @description {@link MyHttpUtilsHolder} should not be initialized
         */
        private MyHttpUtilsHolder() {
            throw new AssertionError();
        }

        private static final MyHttpUtils INSTANCE = new MyHttpUtils();
    }

    /**
     * @description public access point to {@link MyHttpUtilsHolder#INSTANCE}
     */
    public static MyHttpUtils getInstance() {
        return MyHttpUtilsHolder.INSTANCE;
    }

    /**
     * @param mUrl target network
     * @return null for error or HttpURLConnection for normal
     */
    @Nullable
    private HttpURLConnection initializeHttpConnection(@NonNull final String mUrl) {
        try {
            URL mURL = new URL(mUrl);
            HttpURLConnection mHttpURLConnection = (HttpURLConnection) mURL.openConnection();

            //http connection settings
            mHttpURLConnection.setConnectTimeout(5000);
            mHttpURLConnection.setReadTimeout(5000);
            mHttpURLConnection.setDoInput(true);
            mHttpURLConnection.setDoOutput(true);
            mHttpURLConnection.setInstanceFollowRedirects(true);
            mHttpURLConnection.setRequestProperty("Content-Type", "application/json");
            return mHttpURLConnection;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param path    path for request data
     * @param request request model in string
     * @return null for error or string for normal
     */
    @Nullable
    public String postReturnString(@NonNull final String path, @NonNull final String request) {
        HttpURLConnection mHttpURLConnection = initializeHttpConnection(MyAppSettings.HTTP_URL + path);

        if (mHttpURLConnection != null) {
            try {
                mHttpURLConnection.setRequestMethod("POST");
                mHttpURLConnection.setUseCaches(false);

                mHttpURLConnection.connect();

                DataOutputStream printWriter = new DataOutputStream(mHttpURLConnection.getOutputStream());
                printWriter.write(request.getBytes());
                printWriter.flush();
                printWriter.close();

                int respondCode = mHttpURLConnection.getResponseCode();
                Log.d("respondCode", "respondCode=" + respondCode);

                if (mHttpURLConnection.getResponseCode() == 200) {
                    InputStream inputStream = mHttpURLConnection.getInputStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                    int readCursor = 0;

                    byte[] buffer = new byte[1024];

                    while ((readCursor = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, readCursor);
                    }

                    inputStream.close();
                    outputStream.close();

                    String message = new String(outputStream.toByteArray());
                    Log.d("Respond", message);

                    mHttpURLConnection.disconnect();

                    Log.d("postSuccess", message);
                    return message;
                }

                mHttpURLConnection.disconnect();

                Log.e("PostFail", "fail to post to URL");
                return "PostFail";
            } catch (Exception e) {
                Log.e("HttpHelper", "ERROR:initializeHttpConnection:" + e.toString());
                return null;
            }
        }
        return null;
    }
}
