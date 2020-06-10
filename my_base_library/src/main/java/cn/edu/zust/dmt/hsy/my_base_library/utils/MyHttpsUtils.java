package cn.edu.zust.dmt.hsy.my_base_library.utils;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import cn.edu.zust.dmt.hsy.my_base_library.constants.MyAppSettings;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/28/2020 8:29
 **/
public final class MyHttpsUtils {
    /**
     * @description {@link MyHttpsUtils} should only be initialized in
     * {@link MyHttpsUtils.MyHttpsUtilsHolder#INSTANCE}
     */
    private MyHttpsUtils() {
    }

    /**
     * @description inner static class for hold instance and ensure lazy-load singleton
     */
    private static final class MyHttpsUtilsHolder {
        /**
         * @description {@link MyHttpsUtils.MyHttpsUtilsHolder} should not be initialized
         */
        private MyHttpsUtilsHolder() {
            throw new AssertionError();
        }

        private static final MyHttpsUtils INSTANCE = new MyHttpsUtils();
    }

    /**
     * @description public access point to {@link MyHttpsUtils.MyHttpsUtilsHolder#INSTANCE}
     */
    public static MyHttpsUtils getInstance() {
        return MyHttpsUtils.MyHttpsUtilsHolder.INSTANCE;
    }

    /**
     * @param url     target internet URL string
     * @param request request info formatted by {@link String} properly
     * @return null for error, {@link String} for respond body
     */
    @Nullable
    public String postReturnString(@NonNull final String url, @NonNull final String request) {
        HttpsURLConnection connection = null;

        try {
            connection = buildHttpsConnection(MyAppSettings.HTTPS_URL + url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (connection != null) {
            try {
                connection.setSSLSocketFactory(getMySSLSocketFactory());
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
//                connection.setUseCaches(false);

                connection.connect();
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(request.getBytes());
                outputStream.close();

//                DataOutputStream printWriter = new DataOutputStream(connection.getOutputStream());
//                printWriter.write(request.getBytes());
//                printWriter.flush();
//                printWriter.close();

                int respondCode = connection.getResponseCode();
                Log.d("respondCode", "respondCode=" + respondCode);

                if (connection.getResponseCode() == 200) {
                    InputStream inputStream = connection.getInputStream();
                    ByteArrayOutputStream temp = new ByteArrayOutputStream();

                    int readCursor = 0;

                    byte[] buffer = new byte[1024];

                    while ((readCursor = inputStream.read(buffer)) != -1) {
                        temp.write(buffer, 0, readCursor);
                    }

                    inputStream.close();
                    temp.close();

                    String message = new String(temp.toByteArray());
                    Log.d("Respond", message);

//                    connection.disconnect();

                    Log.d("postSuccess", message);
                    return message;
                }

                connection.disconnect();

                Log.e("PostFail", "fail to post to URL");
                return "PostFail";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @param url target internet URL string
     * @return connection
     * @throws IOException error in connection building
     */
    @NonNull
    private HttpsURLConnection buildHttpsConnection(@NonNull final String url) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();

        //http connection settings
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Charset", "UTF-8");
        return connection;
    }

    //todo:warning! It is dangerous to trust all CRT! in getMySSLSocketFactory()

    /**
     * @return {@link SSLSocketFactory} which trust everything
     */
    private SSLSocketFactory getMySSLSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            }, null);
            return sslContext.getSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            throw new AssertionError();
        }
    }
}
