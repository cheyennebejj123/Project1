/**
 *This ReturningItems Class extends request and is 
 *a type of request that involves wanting to return items
*to the store  
 *
 * @author Cheyenne Bajani
 * @version 1.0
 * @since 10/29/2023
 */

public class ReturningItems extends Request{
	private String[] itemsToReturn;
	
	public String[] getitemsToReturn() { 
        return itemsToReturn;
    }

    public void setitemsToReturn(String[] itemsToReturn) {
        this.itemsToReturn = itemsToReturn;
    }
	
	/**
	 *Initializes request 
	 *
	 * @param String description about request
	 *@param int priority of the request
	 *@param int difficulty of the request 
	 *@param String[] requests related to items 
	 *
	 */	
	public ReturningItems (String description, int priority, int difficulty, String[]
	itemsToReturn){
		setitemsToReturn(itemsToReturn);
		setDescription(description);
		setPriority(priority);
		setDifficulty(difficulty);
		setFactor(3);
		setStatus(Status.NEW);
		setStartTime(0);
		setEndTime(0);
	}
		
	/**
	 *gets the time needed to process request 
	 *
	 * @returns int time needed to process the request 
	 */
	public int calculateProcessingTime(){
		return getDifficulty() * getFactor() * itemsToReturn.length;
	}
	
}