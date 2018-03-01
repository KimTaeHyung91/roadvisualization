package kr.ac.skuniv.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kr.ac.skuniv.dao.NodeLinkDao;
import kr.ac.skuniv.openapi.XmlParsingData;
import kr.ac.skuniv.vo.LocationXY;
import kr.ac.skuniv.vo.NodeLinkVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class JsonTestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<NodeLinkVo> linkVos = new NodeLinkDao().fromSigToNodeLinkData(100);
		Iterator<NodeLinkVo> iterator = linkVos.iterator();
		NodeLinkVo nodeLinkVo = new NodeLinkVo();
		JSONObject object = new JSONObject();
		JSONObject object2 = new JSONObject();
		JSONObject object3 = new JSONObject();
		
		JSONArray array3 = new JSONArray();
		JSONArray array2 = new JSONArray();
		while (iterator.hasNext()) {
			nodeLinkVo = (NodeLinkVo) iterator.next();
			
			JSONArray array = new JSONArray();
			
			JSONObject object4 = new JSONObject();
			object.put("startName", nodeLinkVo.getSt_node_name());
			object.put("startXY", nodeLinkVo.getStartXY());
			object.put("avgSpeed", nodeLinkVo.getAvgSpeed());
			object.put("endName", nodeLinkVo.getEnd_node_name());
			object.put("endXY", nodeLinkVo.getEndXY());
			
			array2.add(object);
			
			
			object2.put("Type", "NodeInfo");
			
			
			object2.put("Node", array2);
			
		}
		
		System.out.println(object2.toString());
		
		
		

	}

}
