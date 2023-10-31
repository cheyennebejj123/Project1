/**
 *This ReturningItems Class extends request and is 
 *a type of request that involves wanting to buy items
 *
 * @author Cheyenne Bajani
 * @version 1.0
 * @since 10/29/2023
 */

public class BuyingProducts extends Request{
	private String[] itemsToBuy;
	
	public String[] getItemsToBuy() { 
        return itemsToBuy;
    }

    public void setItemsToBuy(String[] itemsToBuy) {
        this.itemsToBuy = itemsToBuy;
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
	public BuyingProducts(String description, int priority, int difficulty, String[]
	itemsToBuy){
		setItemsToBuy(itemsToBuy);
		setDescription(description);
		setPriority(priority);
		setDifficulty(difficulty);
		setFactor(2);
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
		return getDifficulty() * getFactor() * itemsToBuy.length;
	}
	
}
	
	