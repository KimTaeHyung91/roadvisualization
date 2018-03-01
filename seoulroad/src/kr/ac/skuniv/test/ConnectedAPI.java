package kr.ac.skuniv.test;

import kr.ac.skuniv.openapi.XmlParsingData;
import kr.ac.skuniv.vo.LocationXY;

public class ConnectedAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LocationXY minXY = new LocationXY(127.0232274, 37.57558086);
		LocationXY maxXY = new LocationXY(127.023906, 37.57579182);
		Object avg = new XmlParsingData(minXY,maxXY).getApiData("http://openapi.its.go.kr/api/NTrafficInfo");
		System.out.println(avg);
	}

}
