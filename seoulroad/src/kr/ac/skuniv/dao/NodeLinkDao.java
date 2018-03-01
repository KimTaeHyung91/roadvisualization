package kr.ac.skuniv.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.geometry.VPos;
import kr.ac.skuniv.vo.NodeLinkVo;
import kr.ac.skuniv.openapi.XmlParsingData;
import kr.ac.skuniv.vo.LocationXY;

public class NodeLinkDao extends MysqlResource {

	private List<NodeLinkVo> voList = null;
	private String apiUrl = "http://openapi.its.go.kr/api/NTrafficInfo";

	public List<NodeLinkVo> fromSigToNodeLinkData(int sigcode) {
		String first_query = "select kn.X,kn.Y,kn.NODE_NAME,kn1.X,kn1.Y,kn1.NODE_NAME,F_NODE "
				+ "from korea_node_info kn,korea_node_info kn1,korea_link_info ki "
				+ "where (kn.NODE_ID=ki.F_NODE and kn1.NODE_ID=ki.T_NODE and kn.STNL_REG=?) order by F_NODE asc";
		
		NodeLinkVo nodeLinkVo = null;
		LocationXY stLatLng = null;
		LocationXY edLatLng = null;
		try {
			voList = new ArrayList<>();
			getMysqlConnection(first_query);
			ps.setInt(1, sigcode);
			rs = ps.executeQuery();
			/* and kn.STNL_REG=? and kn1.STNL_REG=? */

			while (rs.next()) {
				nodeLinkVo = new NodeLinkVo();
				stLatLng = new LocationXY(rs.getDouble("kn.Y"), rs.getDouble("kn.X"));
				edLatLng = new LocationXY(rs.getDouble("kn1.Y"), rs.getDouble("kn1.X"));
				
				nodeLinkVo.setAvgSpeed(new XmlParsingData(new LocationXY(rs.getDouble("kn.X"), rs.getDouble("kn.Y")), new LocationXY(rs.getDouble("kn1.X"),rs.getDouble("kn1.Y"))).getApiData(apiUrl));
				nodeLinkVo.setStartXY(stLatLng);
				nodeLinkVo.setEndXY(edLatLng);
				nodeLinkVo.setSt_node_name(rs.getString("kn.NODE_NAME"));
				nodeLinkVo.setEnd_node_name(rs.getString("kn1.NODE_NAME"));
				voList.add(nodeLinkVo);

			}

		} catch (Exception e) {
			// TODO: handle exception
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				ps.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

		return voList;
	}

}

