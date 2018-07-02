package de.hdm.KontaktSharing.client.page;

public class EmptyPage extends CommonPage {
	String title;
	
	public EmptyPage(String title) {
		this.title = title;
	}

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return title;
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub

	}
	


}
