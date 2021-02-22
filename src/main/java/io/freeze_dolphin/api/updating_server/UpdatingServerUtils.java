package io.freeze_dolphin.api.updating_server;

import com.sun.istack.internal.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class UpdatingServerUtils {

    private static String DEFAULT_URL = "https://raw.githubusercontent.com/freeze-dolphin/updating-server/master/content.xml";
    
    public static String setDefaultURL(String newDefaultUrl) {
        DEFAULT_URL = newDefaultUrl;
        return DEFAULT_URL;
    }
    
    public static String getDefaultURL() {
        return DEFAULT_URL;
    }
    
    @Nullable
    public static String getLatestVersion(String contentUrl, String repositoryName) throws IOException, DocumentException {
        SAXReader reader = new SAXReader();
        Document d = reader.read(getVersionFile(contentUrl, repositoryName));
        return d.elementByID("latest").getStringValue();
    }
    
    @Nullable
    public static String getLatestVersion(String repositoryName) throws IOException, DocumentException {
        SAXReader reader = new SAXReader();
        Document d = reader.read(getVersionFile(repositoryName));
        return d.elementByID("latest").getStringValue();
    }

    @Nullable
    public static String getVersionInfo(String contentUrl, String repositoryName, String versionKey) throws IOException, DocumentException {
        SAXReader reader = new SAXReader();
        Document d = reader.read(getVersionFile(contentUrl, repositoryName));

        Element root = d.elementByID("versions");
        for (Iterator<Element> it = root.elementIterator("version"); it.hasNext();) {
            Element rp = it.next();
            if (rp.attribute("key").getStringValue().equals(versionKey)) {
                return rp.getStringValue();
            }
        }
        return null;
    }
    
    @Nullable
    public static String getVersionInfo(String repositoryName, String versionKey) throws IOException, DocumentException {
        SAXReader reader = new SAXReader();
        Document d = reader.read(getVersionFile(repositoryName));

        Element root = d.elementByID("versions");
        for (Iterator<Element> it = root.elementIterator("version"); it.hasNext();) {
            Element rp = it.next();
            if (rp.attribute("key").getStringValue().equals(versionKey)) {
                return rp.getStringValue();
            }
        }
        return null;
    }

    @Nullable
    public static InputStream getVersionFile(String contentUrl, String repositoryName) throws IOException, DocumentException {
        return DownloadUtils.downLoadFromUrl(getVersionFileURL(contentUrl, repositoryName));
    }

    @Nullable
    public static InputStream getVersionFile(String repositoryName) throws IOException, DocumentException {
        return getVersionFile(DEFAULT_URL, repositoryName);
    }

    @Nullable
    public static String getVersionFileURL(String contentUrl, String repositoryName) throws IOException, DocumentException {
        SAXReader reader = new SAXReader();
        Document d = reader.read(getFile(contentUrl));

        Element root = d.elementByID("repositories");
        for (Iterator<Element> it = root.elementIterator("repository"); it.hasNext();) {
            Element rp = it.next();
            if (rp.attribute("name").getStringValue().equals(repositoryName)) {
                return rp.getStringValue();
            }
        }
        return null;
    }

    public static InputStream getFile(String url) throws IOException {
        return DownloadUtils.downLoadFromUrl(url);
    }

    public static InputStream getContentFile() throws IOException {
        return getFile(DEFAULT_URL);
    }

    /**
     * @author David
     * @className DownloadUtils
     * @originalClassName testDown
     * @date 2019/3/29 17:12
     */
    private static class DownloadUtils {

        /**
         * Download from web URL
         *
         * @param urlStr
         * @param fileName
         * @param savePath
         * @throws IOException
         */
        public static InputStream downLoadFromUrl(String urlStr) throws IOException {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // conn.setConnectTimeout(10 * 1000);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:85.0) Gecko/20100101 Firefox/85.0");

            InputStream inputStream = conn.getInputStream();

            if (inputStream != null) {
                inputStream.close();
            }

            // log.log(Level.INFO, "Successfully downloaded: {0}", file.getName());
            return inputStream;

        }

    }

}
