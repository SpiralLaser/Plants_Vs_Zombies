import java.util.*;


public class PvZEvent extends EventObject {
	
	private Status status;
	private GridCell cell;
	private String type;
	
	public PvZEvent(Object source, Status s, GridCell cell, String type) { 
		super(source);
		this.status = s;
		this.cell = cell;
		this.type = type;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public int getColumn() {
		return cell.getCol();
	}
	
	public int getRow() {
		return cell.getRow();
	}
	
	public String getType() {
		//System.out.println(type);
		return type;
	}
}
