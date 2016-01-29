package com.zhihui.meb.test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import com.zhihui.core.util.MyAlgorithmUtils;
import com.zhihui.meb.api.request.MebAssetPointOwnershipUseRequest;

public class TestMebAssetPointOwnershipUseRequest {

	public static void main(String[] args) {
		String ct = MediaType.APPLICATION_JSON;
		String at = MediaType.APPLICATION_JSON;
		String postData = "";

		try {
			MebAssetPointOwnershipUseRequest t = new MebAssetPointOwnershipUseRequest();
			t.setMethod("meb.asset.point.ownership.use");
			t.setTimestamp(new Date());
			t.setOprtId(1);
			t.setBeginDate(new Date());
			t.setEndDate(new Date());
			t.setExtraInfo("vvv");
			t.setMebId(1L);
			t.setPoint(7);
			t.setRemark("备");
			t.setTarPartnerId(23);
			t.setTarChainId(23);
			t.setTarOrderId(243255L);

			// for XML
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JAXBContext jc = JAXBContext.newInstance(t.getClass());
			jc.createMarshaller().marshal(t, baos);
			baos.flush();
			if (ct.equals(MediaType.APPLICATION_XML))
				postData = baos.toString();

			// for JSON
			ObjectMapper om = new ObjectMapper();
			om.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			if (ct.equals(MediaType.APPLICATION_JSON))
				postData = om.writeValueAsString(t);

			// ...
			String sign = MyAlgorithmUtils.MD5(postData);
			String requstUrl = "http://localhost:8080/zhihui-meb/rest/meb";
			URL url = new URL(requstUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-type", ct);
			connection.setRequestProperty("Accept", at);
			connection.setRequestProperty("Sign", sign);
			connection.setConnectTimeout(30 * 1000);
			connection.setReadTimeout(60 * 1000);
			connection.setUseCaches(false);
			connection.setDoInput(true);

			// send request
			if (postData != null && !postData.equals("")) {
				connection.setDoOutput(true);
				OutputStreamWriter os = new OutputStreamWriter(connection.getOutputStream());
				os.write(postData);
				os.flush();
				os.close();
			}

			// get response
			InputStream inputStream = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null)
				response.append(line);

			inputStream.close();
			connection.disconnect();
			System.out.println(response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}