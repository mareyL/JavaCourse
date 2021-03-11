package opinion;

public class Review {
	private Member member;
	private Item item;
	private float mark;
	private String description;
	
	public Review(Member member, Item item, float mark, String description) {
		this.member = member;
		this.item = item;
		this.mark = mark;
		this.description = description;
	}
	public boolean isByMember(Member member) {
		if (this.member.equals(member)) {
			return true;
		}
		return false;
	}
	public boolean isForItem(Item item) {
		if (this.item.equals(item)) {
			return true;
		}
		return false;
	}
	public boolean equals(Object o) {
		if (o.getClass() == Review.class && ((Review)o).isByMember(this.member) && ((Review)o).isForItem(this.item)) {
			return true;
		}
		return false;
	}
	public String toString() {
		return "Item: " + this.item + ", Member: " + this.member + ", Mark: " +Float.toString(this.mark) + ", Description: " + this.description;
	}
	public float getMark() {
		return this.mark;
	}

}
