package ntou.wbse;

import java.util.*;

/**
 * 
 */
public class Group {

	private String name;

	/**
	 * 
	 */
	private long id;

	/**
	 * 
	 */
	private List<User> members;

	/**
	 * 
	 */
	private User owner;

	/**
	 * Default constructor
	 * 
	 * @param id2
	 * @param name
	 */
	public Group(String name, long id, User owner) {
		this.id = id;
		this.name = name;
		members = new ArrayList<User>();
		this.owner = owner;
		members.add(owner);
	}

	/**
	 * @param user
	 */
	public void addUser2Group(User user) {
		members.add(user);
	}

	/**
	 * @param id
	 */
	public void userLeave(String id) {
		// TODO implement here
	}

	/**
	 * @param id
	 * @param message
	 */
	public void sendMessageFromUserId(String id, String message) {
		// TODO implement here
	}

	/**
	 * @param id
	 * @param message
	 */
	public void sendMessage2All(String message) {
		for(User user : members){
			user.sendMessage(message);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Group [name=" + name + ", id=" + id + ", members=" + members + ", owner=" + owner + "]";
	}

}