package kr.ac.skuniv.vo;

public class NodeLinkVo {

	private String st_node_name;
	private String end_node_name;
	private LocationXY startXY;
	private LocationXY endXY;
	private long link_id;
	private String road_name;
	private String avgSpeed;

	public String getAvgSpeed() {
		return avgSpeed;
	}

	public void setAvgSpeed(String avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	public String getSt_node_name() {
		return st_node_name;
	}

	public void setSt_node_name(String st_node_name) {
		this.st_node_name = st_node_name;
	}

	public String getEnd_node_name() {
		return end_node_name;
	}

	public void setEnd_node_name(String end_node_name) {
		this.end_node_name = end_node_name;
	}

	public LocationXY getStartXY() {
		return startXY;
	}

	public void setStartXY(LocationXY startXY) {
		this.startXY = startXY;
	}

	public LocationXY getEndXY() {
		return endXY;
	}

	public void setEndXY(LocationXY endXY) {
		this.endXY = endXY;
	}

	public long getLink_id() {
		return link_id;
	}

	public void setLink_id(long link_id) {
		this.link_id = link_id;
	}

	public String getRoad_name() {
		return road_name;
	}

	public void setRoad_name(String road_name) {
		this.road_name = road_name;
	}

}
