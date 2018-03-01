package kr.ac.skuniv.openapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import kr.ac.skuniv.vo.LocationXY;

public class XmlParsingData implements ConnectApiServer {

	private LocationXY minXY;
	private LocationXY maxXY;
	private final String key = "1517299958187";
	private final String ReqType = "2";

	public XmlParsingData(LocationXY minXY, LocationXY maxXY) {
		// TODO Auto-generated constructor stub
		this.minXY = minXY;
		this.maxXY = maxXY;

	}

	@Override
	public String getApiData(String url) {
		// TODO Auto-generated method stub

		CloseableHttpClient httpClient = HttpClients.createDefault();
		String avgSpeed = null;
		CloseableHttpResponse response = null;
		try {

			URIBuilder builder = new URIBuilder(url);
			builder.addParameter("key", key);
			builder.addParameter("ReqType", ReqType);
			builder.addParameter("MinX", String.valueOf(minXY.getX()));
			builder.addParameter("MaxX", String.valueOf(maxXY.getX()));
			builder.addParameter("MinY", String.valueOf(minXY.getY()));
			builder.addParameter("MaxY", String.valueOf(maxXY.getY()));

			HttpGet httpGet = new HttpGet(builder.build());
			System.out.println("Server address:" + httpGet.getURI());
			
			response = httpClient.execute(httpGet);
			System.out.println("========================응답결과 ==============================================================================");
			HttpEntity entity = response.getEntity();

			System.out.println(response.getStatusLine());
			System.out.println("===============================================================================================================");
			if (entity != null) {

				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = factory.newDocumentBuilder();
				Document doc = documentBuilder.parse(response.getEntity().getContent());
		
				System.out.println("Root Element:" + doc.getDocumentElement().getNodeName());
				NodeList nodeList = doc.getElementsByTagName("data");

				for (int i = 0; i < nodeList.getLength(); i++) {

					Node node = nodeList.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) node;
						avgSpeed = getTagValue("avgspeed", eElement);
						
					}
				}
				httpGet.abort();
			}

			

		} catch (Exception e) {
			// TODO: handle exceptioG

		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		return avgSpeed;

	}

	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}

}