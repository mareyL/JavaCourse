package opinion;

public class Book implements Item {
	private String title;
	private String author;
	private String kind;
	private int nbPages;
	private float mean;
	
	public Book(String title, String author, String kind, int nbPages) {
		this.title = title;
		this.author = author;
		this.kind = kind;
		this.nbPages = nbPages;
		this.mean= 0f;
	}
	public boolean hasAuthor(String author) {
		return this.author.equals(author);
	}
	public boolean hasTitle(String title) {
		return this.title.toLowerCase().equals(title.toLowerCase());
	}
	
	public boolean equals(Object o) {
		if (o.getClass()==Book.class && ((Book)o).hasTitle(this.title)) {
			return true;
		}
		return false;
	}
	public String toString() {
		return "Title: " + this.title + ", Author: " + this.author + ", Kind: " +this.kind + ", nbPages: " + Integer.toString(this.nbPages);
	}
	
	public float getMean() {
		return this.mean;
	}
	public void setMean(float mean) {
		this.mean = mean;
	}
	
	

}


