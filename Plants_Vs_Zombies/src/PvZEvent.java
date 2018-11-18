import java.util.*;

/**
 * The event class that is used when model wants to let the GUI know that a change has been made. String type contains 
 * what text the GUI should be updating with.
 * @author Leo Paz
 *
 */
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
		return type;
	}
}
