package kr.ac.skuniv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.skuniv.dao.NodeLinkDao;
import kr.ac.skuniv.vo.NodeLinkVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/index")
public class MainController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=utf8");

		String selectbox = request.getParameter("selectbox");
		int sigCode = Integer.parseInt(selectbox);

		List<NodeLinkVo> linkVos = new NodeLinkDao().fromSigToNodeLinkData(sigCode);
		
		NodeLinkVo nodeLinkVo = new NodeLinkVo();
		Iterator<NodeLinkVo> iterator = linkVos.iterator();
		JSONObject object = new JSONObject();
		JSONObject object2 = new JSONObject();
		JSONArray array2 = new JSONArray();
		
		PrintWriter out = response.getWriter();
		
		while (iterator.hasNext()) {

			nodeLinkVo = (NodeLinkVo) iterator.next();
			
			object.put("startName", nodeLinkVo.getSt_node_name());
			object.put("startXY", nodeLinkVo.getStartXY());
			object.put("avgSpeed", nodeLinkVo.getAvgSpeed());
			object.put("endName", nodeLinkVo.getEnd_node_name());
			object.put("endXY", nodeLinkVo.getEndXY());

			array2.add(object);

			object2.put("Type", "NodeInfo");


			object2.put("Node", array2);
		
		}
		
		out.println(object2.toString());
		out.flush();
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
