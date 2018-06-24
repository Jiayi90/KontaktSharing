package de.hdm.KontaktSharing.shared.report;
import java.io.Serializable;
import java.util.Date;



public class AllKontaktReport extends CompositeReport implements Serializable {

 
  private static final long serialVersionUID = 1L;


	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Object getHeaderData() {
		// TODO Auto-generated method stub
		return null;
	}

	public Paragraph getImprint() {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getCreated() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNumSubReports() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setCreated(Date date) {
		// TODO Auto-generated method stub
		
	}

	public void addRow(Row accountRow) {
		// TODO Auto-generated method stub
		
	}

	public void setHeaderData(CompositeParagraph header) {
		// TODO Auto-generated method stub
		
	}
	
}