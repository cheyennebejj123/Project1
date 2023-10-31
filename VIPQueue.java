/**
 * VIPQueue Class represents a queue that can only accept vip clients 
 *
 * @author Cheyenne Bajani
 * @version 1.0
 * @since 10/29/2023
 */
 
public class VIPQueue extends Queue{ 
	/**
	 * Initializes a VIP queue
	 *
	 * @param a String, the server name, 
	 *@param an int, the size of the queue
	 */
	public VIPQueue (String serverName, int queueSize){
		super(serverName, queueSize);
	}
}