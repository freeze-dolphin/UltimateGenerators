package io.freeze_dolphin.api.updating_server;

import io.freeze_dolphin.ultimate_generators.Loader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class UpdatingServerUtils {

    private static String DEFAULT_URL = "https://cdn.jsdelivr.net/gh/freeze-dolphin/updating-server@master/content.xml";
    private static String UA = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:85.0) Gecko/20100101 Firefox/85.0";
    private static int TIME_OUT = 9000;

    /*
     * This is the calling template
     *  Utils.asyncDelay(() -> {
            if (Boolean.parseBoolean(getProperties().getProperty("enable-update-notification", "true"))) {
                info("Checking Updates...");
                try {
                    String latest = getLatestVersion(Loader.getImplement().getName());
                    String current = Loader.getImplement().getDescription().getVersion();
                    if (Integer.parseInt(latest.replaceAll("\\.", "")) > Integer.parseInt(current.replaceAll("\\.", ""))) {
                        VersionInfo vi = getVersionInfo(Loader.getImplement().getName(), latest);
                        warn("Update detected: v" + latest + " (Current: v" + current + ")" + "\n\t· " + vi.getName() + "\n\t· " + vi.getDescription() + "\n\t· " + vi.getURL());
                    } else {
                        info("You are now in the latest version!");
                    }
                } catch (IOException | KeyManagementException | NoSuchAlgorithmException ex) {
                    ex.printStackTrace();
                    warn("Unable to check updates! Make sure that you have configured your Internet properly and try again by restarting the server!");
                } catch (DocumentException | NumberFormatException | NullPointerException | NoSuchProviderException ex) {
                    ex.printStackTrace();
                    warn("Unable to check updates! Please contact the plugin author to fix the updating-server bug, or you can temporarily disable the update checker by setting 'enable-update-notification' to 'false' in 'config.properties'");
                }
            }
        });
     */
    public static String setDefaultURL(String newDefaultUrl) {
        DEFAULT_URL = newDefaultUrl;
        return DEFAULT_URL;
    }

    public static String getDefaultURL() {
        return DEFAULT_URL;
    }

    public static String setUA(String newUA) {
        UA = newUA;
        return UA;
    }

    public static String getUA() {
        return UA;
    }

    public static int getTimeOut() {
        return TIME_OUT;
    }

    public static int setTimeOut(int timeOut) {
        TIME_OUT = timeOut;
        return TIME_OUT;
    }

    public static void init(String defaultUrl, String userAgent, int timeOut) {
        setDefaultURL(defaultUrl);
        setUA(userAgent);
        setTimeOut(timeOut);
    }

    public static String getLatestVersion(String contentUrl, String repositoryName) throws IOException, DocumentException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        SAXReader reader = new SAXReader();
        Document d = reader.read(getVersionFile(contentUrl, repositoryName));
        Element root = d.getRootElement();
        return root.element("latest").getStringValue();
    }

    public static String getLatestVersion(String repositoryName) throws IOException, DocumentException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        return getLatestVersion(DEFAULT_URL, repositoryName);
    }

    public static VersionInfo getVersionInfo(String contentUrl, String repositoryName, String versionKey) throws IOException, DocumentException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        SAXReader reader = new SAXReader();
        Document d = reader.read(getVersionFile(contentUrl, repositoryName));

        Element root = d.getRootElement();
        for (Iterator<Element> it = root.elementIterator("versions"); it.hasNext();) {
            Element rp = it.next().element("version");
            if (rp.attribute("key").getStringValue().equals(versionKey)) {
                return new VersionInfo(rp.element("name").getStringValue(), rp.element("desc").getStringValue(), rp.element("url").getStringValue());
            }
        }
        return null;
    }

    public static VersionInfo getVersionInfo(String repositoryName, String versionKey) throws IOException, DocumentException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        return getVersionInfo(DEFAULT_URL, repositoryName, versionKey);
    }

    public static class VersionInfo {

        private final String name, desc, url;

        public VersionInfo(String versionName, String versionDescription, String url) {
            this.name = versionName;
            this.desc = versionDescription;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return desc;
        }

        public String getURL() {
            return url;
        }

    }

    public static InputStream getVersionFile(String contentUrl, String repositoryName) throws IOException, DocumentException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        return DownloadUtils.downLoadFromURL(getVersionFileURL(contentUrl, repositoryName));
    }

    public static InputStream getVersionFile(String repositoryName) throws IOException, DocumentException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        return getVersionFile(DEFAULT_URL, repositoryName);
    }

    public static String getVersionFileURL(String contentUrl, String repositoryName) throws IOException, DocumentException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        SAXReader reader = new SAXReader();
        Document d = reader.read(getFile(contentUrl));

        Element root = d.getRootElement();
        for (Iterator<Element> it = root.elementIterator("repositories"); it.hasNext();) {
            Element rp = it.next().element("repository");
            if (rp.attribute("name").getStringValue().equals(repositoryName)) {
                return rp.getStringValue();
            }
        }
        return null;
    }

    public static InputStream getFile(String url) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        return DownloadUtils.downLoadFromURL(url);
    }

    public static InputStream getContentFile() throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        return getFile(DEFAULT_URL);
    }

    private static class DownloadUtils {

        public static InputStream downLoadFromURL(String urlStr) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
            if (urlStr.startsWith("https://")) {
                return downLoadFromHttps(urlStr);
            } else {
                return downLoadFromHttp(urlStr);
            }
        }

        public static InputStream downLoadFromHttp(String urlStr) throws IOException {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setRequestProperty("User-Agent", UA);
            conn.connect();

            InputStream inputStream = conn.getInputStream();

            // log.log(Level.INFO, "Successfully downloaded: {0}", file.getName());
            return inputStream;

        }

        public static InputStream downLoadFromHttps(String urlStr) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(urlStr);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(Integer.parseInt(Loader.getProperties().getProperty("update-check-timeout", "10000")));
            conn.setConnectTimeout(Integer.parseInt(Loader.getProperties().getProperty("update-check-timeout", "10000")));
            conn.setRequestProperty("User-Agent", UA);
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.setSSLSocketFactory(ssf);
            conn.connect();

            InputStream inputStream = conn.getInputStream();

            return inputStream;
        }

        private static class TrustAnyHostnameVerifier implements HostnameVerifier {

            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        }

        private static class TrustAnyTrustManager implements X509TrustManager {

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }

        }

    }

}
