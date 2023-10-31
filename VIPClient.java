/**
 *The VIP client class represents a client that can join a VIP queue
 *
 * @author Cheyenne Bajani
 * @version 1.0
 * @since 10/29/2023
 */

public class VIPClient extends Client implements Prioritizable{ 
	private int memberSince;
	private int priority;
	
	public int getMemberSince() {
        return memberSince;
    }

	@Override
    public int getPriority() {  
        return priority; 
    }

    public void setMemberSince(int memberSince) {
        this.memberSince = memberSince; 
    }

    public void setPriority(int priority) {
        this.priority = priority; 
    }
	
	/**
	 *initializes VIP Client
	 *
	  * @param firstName   The first name of the client.
 * @param lastName    The last name of the client.
 * @param birthYear   The birth year of the client.
 * @param gender      The gender of the client.
 * @param arrivalTime The arrival time of the client.
 * @param patience    The patience level of the client, represented as an integer.
 * @param requests    An array of Request objects associated with the client.
 * @param memberSince The year the client became a VIP member.
 * @param priority    The priority level of the client
 */
	public VIPClient(String firstName, String lastName, int birthYear, String gender,int arrivalTime, int patience, Request[] requests, int memberSince, int priority){
		super(firstName, lastName, birthYear, gender, arrivalTime, patience, requests);
		setMemberSince(memberSince);
		setPriority(priority);
	}
		
	/**
	 *gets the info about a client  
	 *
	 * @returns a Sttring containing all information about client  
	 */
	public String toString(){
		String newstr = super.toString();
		return newstr + "\n**VIP since : " + memberSince + "\n**Priority : " + priority; 
	}
}
	
	
	
	
