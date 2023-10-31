/**
 *The queue class represents a queue in the system
 *
 * @author Cheyenne Bajani
 * @version 1.0
 * @since 10/29/2023
 */
public class Queue {
	private String serverName; 
	private int queueSize;
	private Client clientBeingServed;
	private Request requestInProgress;
	private int processingStartTime;
	private Client[] clientsHistory;
	private Client[] clientsInQueue;
	private int counter;
	private int availableSpots;
	
	public int getAvailableSpots(){
		return availableSpots;
	}
	
	public void setAvailableSpots(int availableSpots){
		this.availableSpots = availableSpots;
	}
	
	public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public Client getClientBeingServed() {
        return clientBeingServed;
    }

    public void setClientBeingServed(Client clientBeingServed) {
        this.clientBeingServed = clientBeingServed;
    }

    public Request getRequestInProgress() {
        return requestInProgress;
    }

    public void setRequestInProgress(Request requestInProgress) {
        this.requestInProgress = requestInProgress;
    }

    public int getProcessingStartTime() {
        return processingStartTime;
    }

    public void setProcessingStartTime(int processingStartTime) {
        this.processingStartTime = processingStartTime;
    }

    public Client[] getClientsHistory() {
        return clientsHistory;
    }

    public void setClientsHistory(Client[] clientsHistory) {
        this.clientsHistory = clientsHistory;
    }

    public Client[] getClientsInQueue() {
        return clientsInQueue;
    }

    public void setClientsInQueue(Client[] clientsInQueue) {
        this.clientsInQueue = clientsInQueue;
    }
	
	/**
	 * Initializes a queue
	 *
	 * @param a String, the server name, 
	 *@param an int, the size of the queue
	 */
	public Queue(String serverName, int queueSize){
		this.clientsInQueue = new Client[queueSize];
		//counter = counter+1;
		setServerName(serverName);
		setQueueSize(queueSize);  //others byekhdo their default values
		/*if (counter == 1){
			Queue[] queues = new Queue[1];  //logic makes sense?
			queues[0] = this;
			QueueSystem.setQueues(queues);
		}else{
			Queue[] queues = new Queue[getQueues().length+1];
			System.arraycopy(queues, 0, getQueues(), 0);
			queues[queues.length-1]= this;
		}
		*/
		
	}
	/**
	 * Add a client to the history of the queue
	 *
	 * @param client A client that needs to be added to history of the queue
	 */
	public void addtoClientsHistory(Client client){
		if (clientsHistory == null){
			Client[] clients = new Client[1];  
			clients[0] = client;
			setClientsHistory(clients);
		}else{
			Client[] clients = new Client[getClientsHistory().length+1];
			System.arraycopy(getClientsHistory(), 0, clients, 0, getClientsHistory().length);
			clients[clients.length-1]= client;
			setClientsHistory(clients);
		}
	}
	
	/**
	 * Makes the queue readable to a human 
	 *
	 * @return a String that contains the information about a queue and the 
	 *clients in the server and queues andtheir ids
	 */
	public String toString(){
		int count = 0;
		String str;
		String result = "";
		String client_id ="";
		String[] ids = new String[queueSize];
		int numberinqueues = 0;
		if (clientBeingServed != null){
			client_id = String.valueOf(clientBeingServed.getId());
		}else{
			client_id="";
		}
		if (clientsInQueue!= null){ 
			for(int j=0; j <queueSize;j++){
				if(clientsInQueue[j]!=null){
					ids[j] = String.valueOf(clientsInQueue[j].getId());
				}else{
					ids[j]="";
				}
			}
		}
		if (QueueSystem.getQueues() !=null){
			for(Queue queue:QueueSystem.getQueues()){
				count = count +1;
				if (queue == this){
					numberinqueues = count;
				}
			}
		}
			
		str = "[Queue:"+numberinqueues+"]["+client_id+"]-----";
		for (String number : ids) {
            result += "[" + number + "]";
        }
		return str+result;
	}
	/**
	 * Makes the queue readable to a human 
	 *@param showID a boolean that determines whether or not we want info about ids or about time remaining
	 * @return a String that contains the information about a queue a
	 */
	public String toString(boolean showID){
		if (showID == true){
			return toString();
		}
		else{
			int count =0;
			String result = "";
			int numberinqueues = 0;
			String processingtimeleft = "";
			String[] processingTimeofClientsInQueue = new String[queueSize];
			if (QueueSystem.getQueues() !=null){
				for(Queue queue:QueueSystem.getQueues()){
					count = count +1; 
					if (queue == this){
						numberinqueues = count;
					}
				}
			}
			if (clientBeingServed != null){
				processingtimeleft = String.valueOf(QueueSystem.getClock() - clientBeingServed.getTotalProcessingTime());
			}else{
				processingtimeleft ="";
			}
			if (clientsInQueue!= null){ 
				for(int j=0; j <queueSize;j++){
					if(clientsInQueue[j]!=null){
						processingTimeofClientsInQueue[j] = String.valueOf(clientsInQueue[j].getTotalProcessingTime());
					}else{
						processingTimeofClientsInQueue[j]="";
					}
				}
			}
			String str = "[Queue:"+numberinqueues+"]["+processingtimeleft+"]-----";
			for (String number : processingTimeofClientsInQueue) {
				result += "[" + number + "]";
			}
			return str+result;	
		}
	}
	
			
			
			




		
		
}
	
		