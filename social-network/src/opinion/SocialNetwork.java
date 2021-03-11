package opinion;

import java.util.LinkedList;

import exceptions.*;

public class SocialNetwork implements ISocialNetwork {
	private LinkedList<Member> members;
	private LinkedList<Item> items;
	private LinkedList<Review> reviews;
	
	public SocialNetwork() {
		this.members = new LinkedList<Member>();
		this.items = new LinkedList<Item>();
		this.reviews = new LinkedList<Review>();
	}

	@Override
	public int nbMembers() {
		// TODO Auto-generated method stub
		return  members.size();
	}

	@Override
	public int nbBooks() {
		int nb = 0;
		for (int i = 0; i<this.items.size(); i++) {
			if (this.items.get(i).getClass() == Book.class) {
				nb = nb + 1;
			}
		}
		return nb;
	}
	

	@Override
	public void addMember(String login, String password, String description)
			throws BadEntryException, MemberAlreadyExistsException {
		
		Member member;
		
		member = new Member(login, password, description);
		if (login != null) {
			login = login.trim();
		}
		if (password != null) {
			password = password.trim();
		}
		
		if (member.hasLogin(null)) {
			throw new BadEntryException("Pseudo pas instancié");
		}
		
		if (description == null) {
			throw new BadEntryException("Profil pas instancié");
		}
		if (member.checkPassword(null)) {
			throw new BadEntryException("Password pas instancié");
		}
		if (login.isEmpty()||login.length()<4) {
			throw new BadEntryException("Pseudo " + login + " contenant que des blancs ou <4, legnth: " + Integer.toString(login.length()));
		}
		
		if (this.members.contains(member)) {
			throw new MemberAlreadyExistsException();
		}
		
		
		this.members.add(member);
	}

	@Override
	public void addItemBook(String login, String password, String title, String kind, String author, int nbPages)
			throws BadEntryException, NotMemberException, ItemBookAlreadyExistsException {
		Member member;
		
		
		member = new Member(login, password, " ");
		
		if (login != null) {
			login = login.trim();
		}
		if (password != null) {
			password = password.trim();
		}
		
		if (member.hasLogin(null)) {
			throw new BadEntryException("Pseudo pas instancié");
		}
		
		if (member.checkPassword(null)) {
			throw new BadEntryException("Password pas instancié");
		}
		if (password.isEmpty()||password.length()<4) {
			throw new BadEntryException("Passwd " + login + " contenant que des blancs ou <4, legnth: " + Integer.toString(password.length()));
			
		}
		if (login.isEmpty()||login.length()<4) {
			throw new BadEntryException("Pseudo " + login + " contenant que des blancs ou <4, legnth: " + Integer.toString(login.length()));
		}
		if (!this.members.contains(member)) {
			throw new NotMemberException("Le membre n'existe pas");
		}
		if (!members.get(members.indexOf(member)).checkPassword(password)) {
			throw new NotMemberException("Password et Login ne correspondent pas");
		}
		///Book-related
		Book book = new Book(title, author, kind, nbPages);
		
		if (title != null) {
			title = title.trim();
		}
		else {
			throw new BadEntryException("Titre pas instancié");
		}
		if ( title.isEmpty()) {
			throw new BadEntryException("Titre sans caractère");
		}
		if (kind == null) {
			throw new BadEntryException("Genre non instancié");
		}
		if (author == null) {
			throw new BadEntryException("Auteur non instancié");
		}
		if (nbPages<=0) {
			throw new BadEntryException("Nombre de pages invalide");
		}
		
		if (this.items.contains(book)) {
			throw new ItemBookAlreadyExistsException();
		}
		
		this.items.add(book);
	}

	@Override
	public float reviewItemBook(String login, String password, String title, float mark, String comment)
			throws BadEntryException, NotMemberException, NotItemException {
		// TODO Auto-generated method stub
		
		Member member;
		
		
		member = new Member(login, password, " ");
		
		if (login != null) {
			login = login.trim();
		}
		if (password != null) {
			password = password.trim();
		}
		
		if (member.hasLogin(null)) {
			throw new BadEntryException("Pseudo pas instancié");
		}
		
		if (member.checkPassword(null)) {
			throw new BadEntryException("Password pas instancié");
		}
		if (password.isEmpty()||password.length()<4) {
			throw new BadEntryException("Passwd " + login + " contenant que des blancs ou <4, legnth: " + Integer.toString(password.length()));
			
		}
		if (login.isEmpty()||login.length()<4) {
			throw new BadEntryException("Pseudo " + login + " contenant que des blancs ou <4, legnth: " + Integer.toString(login.length()));
		}
		if (!this.members.contains(member)) {
			throw new NotMemberException("Le membre n'existe pas");
		}
		if (!this.members.get(this.members.indexOf(member)).checkPassword(password)) {
			throw new NotMemberException("Password et Login ne correspondent pas");
		}
		
		//Book_related
		
		
		if (title != null) {
			title = title.trim();
		}
		else {
			throw new BadEntryException("Titre pas instancié");
		}
		if ( title.isEmpty()) {
			throw new BadEntryException("Titre sans caractère");
		}
		
		Book book = new Book(title,"author" , "kind", 10);
		
		
		if (!this.items.contains(book)) {
			throw new NotItemException("Ne contient pas ce livre : " +title);
		}
		
		///Review _related
		
		Item book_rev = this.items.get(this.items.indexOf(book));
		Member member_rev = this.members.get(this.members.indexOf(member));

		
		
		Review review = new Review(member_rev,book_rev, mark, comment);
		
		if (comment == null) {
			throw new BadEntryException("Il n'y a pas de commentaire");
		}
		if (mark<0.0 || mark>5.0) {
			throw new BadEntryException("Note non comprise entre 0 & 5");
		}
		///Sweeping duplicates

		if (this.reviews.contains(review)) {
			
			this.reviews.remove(this.reviews.indexOf(review));
		}
				
		this.reviews.add(review);
		
		
		
		
		
		float mean_mark = 0f;
		float multip = 0f;
		for (int i = 0; i < this.reviews.size(); i++) {
			
			if (this.reviews.get(i).isForItem(book_rev)) {
					mean_mark = mean_mark + this.reviews.get(i).getMark();
					multip = multip +1.0f ;	
			}
			
		}
		mean_mark = mean_mark / multip;
		book_rev.setMean(mean_mark);
		return mean_mark;
	}

	@Override
	public LinkedList<String> consultItems(String title) throws BadEntryException {
		// TODO Auto-generated method stub
		LinkedList<Item> liste = new LinkedList<Item>();
		LinkedList<String> liste_string = new LinkedList<String>();
		
		if (title != null) {
			title = title.trim();
		}
		else {
			throw new BadEntryException("Titre pas instancié");
		}
		if ( title.isEmpty()) {
			throw new BadEntryException("Titre sans caractère");
		}
		for (int i = 0; i<this.items.size(); i++) {
			if (this.items.get(i).hasTitle(title)) {
				liste.add(this.items.get(i));
			}
		}
		for (int i = 0; i<liste.size(); i++) {
			liste_string.add(liste.get(i).toString());
		}
		return liste_string;
	}

}
