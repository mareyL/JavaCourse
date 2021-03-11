package opinion;

public interface Item {
	
	public String toString();
	public boolean equals(Object o);
	public void setMean(float mean);
	public float getMean();
	public boolean hasTitle(String title);

}
