package ir.ActionBar;

public class ActionBarSpinnerNavItem {

	private String title;
	private int icon;
	
	public ActionBarSpinnerNavItem(String title, int icon){
		this.title = title;
		this.icon = icon;
	}
	
	public String getTitle(){
		return this.title;		
	}
	
	public int getIcon(){
		return this.icon;
	}
}
