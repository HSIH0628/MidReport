package tw.hsih.midreport.dao;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.cert.X509Certificate;

public class DownloadDAO {

	public void downloadFile(String url, String destinationFolder, String fileName) throws IOException {
		try {
			// TrustManager
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] chain, String authType) {
				}

				public void checkServerTrusted(X509Certificate[] chain, String authType) {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} };

			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

			// SSL屬性
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

			downloadFileFromUrl(url, destinationFolder, fileName);
			System.out.println("File downloaded successfully!");
		} catch (Exception e) {
			System.out.println("Error downloading file: " + e.getMessage());
		}
	}

	private static void downloadFileFromUrl(String url, String destinationFolder, String fileName) throws IOException {
		URL fileUrl = new URL(url);
		try (InputStream in = fileUrl.openStream()) {
			Path destinationPath = Path.of(destinationFolder, fileName);
			Files.copy(in, destinationPath, StandardCopyOption.REPLACE_EXISTING);
		}
	}
}
