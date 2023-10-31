/**
*The queueSystem class has clients wanting to join the queues + 
*the clients in the queues. Basically it manages the system of everything 
*related to queues, waitingline, and clients 
*
* @author Cheyenne Bajani
* @version 1.0
* @since 10/29/2023
*/


public class QueueSystem{
	static private int clock;   //static? which methods are static kel chi static 
    static private int totalWaitingTime;
    static private Client[] clientsWorld; 
    static private int totalClientsInSystem;
    static private int waitingLineSize;
    static private Client[] waitingLine;
    static private boolean tvInWaitingArea;
    static private boolean coffeeInWaitingArea;
    static private Queue[] queues;
	static private Client[] waitingLineVip;
	static private Client[] waitingLineRegular; //okk?
	
	public static Client[] getWaitingLineRegular() {
        return waitingLineRegular;
	}

    public static int getClock() {
        return clock;
    }

    public static void setClock(int clock1) {
        clock = clock1;
    }

    public static int getTotalWaitingTime() {
        return totalWaitingTime;
    }

    public static void setTotalWaitingTime(int totalWaitingTime1) {
        totalWaitingTime = totalWaitingTime1;
    }

    public static Client[] getClientsWorld() {
        return clientsWorld;
    }

    public static void setClientsWorld(Client[] clientsWorld1) {
        clientsWorld = clientsWorld1;
    }

    public static int getTotalClientsInSystem() {
        return totalClientsInSystem;
    }

    public static void setTotalClientsInSystem(int totalClientsInSystem1) {
        totalClientsInSystem = totalClientsInSystem1;
    }

    public static int getWaitingLineSize() {
        return waitingLineSize;
    }

    public static void setWaitingLineSize(int waitingLineSize1) {
        waitingLineSize = waitingLineSize1;
    }

    public static Client[] getWaitingLine() {
        return waitingLine;
    }

    public static void setWaitingLine(Client[] waitingLine1) {
        waitingLine = waitingLine1;
    }

    public static boolean isTvInWaitingArea() {
        return tvInWaitingArea;
    }

    public static void setTvInWaitingArea(boolean tvInWaitingArea1) {
        tvInWaitingArea = tvInWaitingArea1;
    }

    public static boolean isCoffeeInWaitingArea() {
        return coffeeInWaitingArea;
    }

    public static void setCoffeeInWaitingArea(boolean coffeeInWaitingArea1) {
        coffeeInWaitingArea = coffeeInWaitingArea1;
    }

    public static Queue[] getQueues() {
        return queues;
    }

    public static void setQueues(Queue[] queues1) {
        queues = queues1;//what about diff clients with same ids?, invalid param?
    }
	
	/**
	 *Initializes QueueSystem
	 *
	 * @param waitingLineSize, an int that determines how big the WL is
	 *@param tvInWaitingArea, a boolean that determines if there's a tv or not
	 *@param coffeeInWaitingArea, a boolean that determines if there's coffee
	 */
	public QueueSystem (int waitingLineSize, boolean tvInWaitingArea,
	boolean coffeeInWaitingArea){
		setTvInWaitingArea(tvInWaitingArea);
		setCoffeeInWaitingArea(coffeeInWaitingArea);
		setClock(0);
		this.waitingLine = new Client[waitingLineSize];
	}
	/**
	 *Increase clock by one and pushes people from waitingline to queues, from queues to history
	 *and other changes necessary 
	 */
	public void increaseTime(){
		setClock(clock+1); 
		boolean condition = false; 
		boolean condition1 = false;
		if(queues!=null && queues.length > 0){
		for(int i=0; i<queues.length; i++){ 
			if (queues[i].getRequestInProgress()!=null && queues[i].getRequestInProgress().calculateProcessingTime()== clock-queues[i].getRequestInProgress().getStartTime()){
				queues[i].getRequestInProgress().setStatus(Status.PROCESSED);
				queues[i].getRequestInProgress().setEndTime(clock);
				condition1 = true;
			}
			if (queues[i].getClientBeingServed()!= null && queues[i].getClientBeingServed().getRequests()[queues[i].getClientBeingServed().getRequests().length-1].getStatus() == Status.PROCESSED){
					condition = true;
			}
			if (condition == true){
				queues[i].getClientBeingServed().setDepartureTime(clock);
				queues[i].getClientBeingServed().setServiceTime(clock - queues[i].getProcessingStartTime());
				totalClientsInSystem = totalClientsInSystem -1;
				queues[i].addtoClientsHistory(queues[i].getClientBeingServed());
				queues[i].setClientBeingServed(null);	
			}else{
				if(condition1 == true){
					for(int j=0; j<queues[i].getClientBeingServed().getRequests().length; j++){
						if (queues[i].getRequestInProgress() == queues[i].getClientBeingServed().getRequests()[j]){
							queues[i].setRequestInProgress(queues[i].getClientBeingServed().getRequests()[j+1]);
							queues[i].getRequestInProgress().setStatus(Status.IN_PROGRESS);
							queues[i].getRequestInProgress().setStartTime(clock);
						}
					}
				}
			}
		}
		}						
		if (queues!=null && queues.length>0){
			for(int i=0; i<queues.length; i++){
				if (queues[i]!=null && queues[i].getClientBeingServed()==null &&queues[i].getClientsInQueue()!=null){
					for (int j=0; j<queues[i].getQueueSize(); j++){
						Client earliest_in_queue = queues[i].getClientsInQueue()[0];
						for (int k =0; k<queues[i].getClientsInQueue().length;k++){
							if (queues[i].getClientsInQueue()[j]!=null && queues[i].getClientsInQueue()[j].getTimeInQueue()<earliest_in_queue.getTimeInQueue() ){
								queues[i].setClientBeingServed(queues[i].getClientsInQueue()[j]);
								earliest_in_queue = queues[i].getClientsInQueue()[j];
							}
						}
					}
					if(queues[i].getClientBeingServed()!=null){
					queues[i].setProcessingStartTime(clock);
					queues[i].getClientBeingServed().setTimeInQueue(clock-(queues[i].getClientBeingServed().getArrivalTime()+queues[i].getClientBeingServed().getWaitingTime()));
					if (queues[i].getClientBeingServed().getRequests()!=null && queues[i].getClientBeingServed().getRequests().length>0){
						queues[i].setRequestInProgress(queues[i].getClientBeingServed().getRequests()[0]);
						queues[i].getRequestInProgress().setStartTime(clock);
						queues[i].getRequestInProgress().setStatus(Status.IN_PROGRESS);
					}
					}
					for (int l=0; l<queues[i].getQueueSize(); l++){
						if (queues[i].getClientsInQueue()[l]!=null && queues[i].getClientsInQueue()[l] == queues[i].getClientBeingServed()){
							Client[] updatedClientsInQueue = queues[i].getClientsInQueue();
							updatedClientsInQueue[l] = null;
							queues[i].setClientsInQueue(updatedClientsInQueue);
						}
					}
				}
			}
		}
						
		if (waitingLine!=null && waitingLineSize>0){
			for(int i=0; i<waitingLineSize; i++){
				waitingLine[i].setPatience(waitingLine[i].getPatience()-1);
				if (waitingLine[i].getPatience()<0){
					totalClientsInSystem = totalClientsInSystem -1;
					waitingLine[i].setDepartureTime(clock);
					waitingLine[i].setWaitingTime(clock - waitingLine[i].getArrivalTime());
					waitingLine[i] = null; 
				}
			}
		}
		if (queues!=null && queues.length>0){
			for(int i=0; i<queues.length; i++){
				if (queues[i]!=null && queues[i].getClientsInQueue()!=null){
					for (int j=0; j<queues[i].getClientsInQueue().length;j++){
						if (queues[i].getClientsInQueue()[j]!=null){
						queues[i].getClientsInQueue()[j].setPatience(queues[i].getClientsInQueue()[j].getPatience()-1);
						if (queues[i].getClientsInQueue()[j].getPatience()<0){
							totalClientsInSystem = totalClientsInSystem -1;
							queues[i].getClientsInQueue()[j].setDepartureTime(clock);
							queues[i].getClientsInQueue()[j].setTimeInQueue(clock - (queues[i].getClientsInQueue()[j].getArrivalTime()+queues[i].getClientsInQueue()[j].getWaitingTime()));
							queues[i].addtoClientsHistory(queues[i].getClientsInQueue()[j]);
							Client[] updatedClientsInQueue = queues[i].getClientsInQueue();
							updatedClientsInQueue[j] = null;
							queues[i].setClientsInQueue(updatedClientsInQueue);
						}
						}
					}
				}
			}
		}
			
		//check waiting line validations
		if (waitingLineSize>0){ 
			waitingLineVip = new VIPClient[waitingLineSize];
			waitingLineRegular = new Client[waitingLineSize];
			int counter1 =0;
			int counter2 =0;
			for (int i=0;i<waitingLineSize;i++){
				if(waitingLine[i]!= null){
					if(waitingLine[i] instanceof VIPClient){
						waitingLineVip[counter1] = (VIPClient)waitingLine[i];
						counter1++;
					} else{
						waitingLineRegular[counter2] = waitingLine[i];
						counter2++;
						}
					}
			}
		}
		
		if (waitingLineVip != null && waitingLineVip.length > 0) {
			for (int i = 0; i < waitingLineVip.length - 1; i++) {
				for (int k = 0; k < waitingLineVip.length - i - 1; k++) {
					if (waitingLineVip[k] != null && waitingLineVip[k + 1] != null) {
						if (waitingLineVip[k].getPriority() < waitingLineVip[k + 1].getPriority()) {
							Client temporary = waitingLineVip[k];
							waitingLineVip[k] = waitingLineVip[k + 1];
							waitingLineVip[k + 1] = temporary;
						} else if (waitingLineVip[k].getPriority() == waitingLineVip[k + 1].getPriority()) {
							if (waitingLineVip[k].getMemberSince() < waitingLineVip[k + 1].getMemberSince()) {
								Client temporary = waitingLineVip[k];
								waitingLineVip[k] = waitingLineVip[k + 1];
								waitingLineVip[k + 1] = temporary;
							} else if (waitingLineVip[k].getMemberSince() == waitingLineVip[k + 1].getMemberSince()) {
								if (waitingLineVip[k].getArrivalTime() < waitingLineVip[k + 1].getArrivalTime()) {
									Client temporary = waitingLineVip[k];
									waitingLineVip[k] = waitingLineVip[k + 1];
									waitingLineVip[k + 1] = temporary;
								}
							}
						}
					}
				}
			}
		}
		
		if (waitingLineRegular != null && waitingLineRegular.length > 0) {
			for (int i = 0; i < waitingLineRegular.length - 1; i++) {
				for (int k = 0; k < waitingLineRegular.length - i - 1; k++) {
					if (waitingLineRegular[k] != null && waitingLineRegular[k + 1] != null) {
						if (waitingLineRegular[k].getArrivalTime() > waitingLineRegular[k + 1].getArrivalTime()) {
							Client temporary = waitingLineRegular[k];
							waitingLineRegular[k] = waitingLineRegular[k + 1];
							waitingLineRegular[k + 1] = temporary;
						} else if (waitingLineRegular[k].getArrivalTime() == waitingLineRegular[k + 1].getArrivalTime()) {
							if (waitingLineRegular[k].getYearOfBirth() < waitingLineRegular[k + 1].getYearOfBirth()) {
								Client temporary = waitingLineRegular[k];
								waitingLineRegular[k] = waitingLineRegular[k + 1];
								waitingLineRegular[k + 1] = temporary;
							} else if (waitingLineRegular[k].getYearOfBirth() == waitingLineRegular[k + 1].getYearOfBirth()) {
								if (waitingLineRegular[k].getTotalProcessingTime() < waitingLineRegular[k + 1].getTotalProcessingTime()) {
									Client temporary = waitingLineRegular[k];
									waitingLineRegular[k] = waitingLineRegular[k + 1];
									waitingLineRegular[k + 1] = temporary;
								}else if(waitingLineRegular[k].getTotalProcessingTime() == waitingLineRegular[k + 1].getTotalProcessingTime()){
									if(waitingLineRegular[k].getId() < waitingLineRegular[k + 1].getId()){
										Client temporary = waitingLineRegular[k];
										waitingLineRegular[k] = waitingLineRegular[k + 1];
										waitingLineRegular[k + 1] = temporary;
									}
								}
							}
						}
					}
				}
			}
		}

		Client client;
		if (waitingLineVip!=null&& waitingLineVip.length>0){ 
			for(int i=0; i <waitingLineVip.length;i++){ 
				if (waitingLineVip[i]!=null && getVIPQueueWithMostAvailableSpots()!=null){
					client = waitingLineVip[i];
					waitingLineVip[i] = null;
					client.setWaitingTime(clock-client.getArrivalTime()); //sah
					for(int j =0;j<getVIPQueueWithMostAvailableSpots().getQueueSize();j++){
						if (getVIPQueueWithMostAvailableSpots().getClientsInQueue()[j]==null){
							Client[] updatedClientsInQueue = getVIPQueueWithMostAvailableSpots().getClientsInQueue();
							updatedClientsInQueue[j] = client;
							getVIPQueueWithMostAvailableSpots().setClientsInQueue(updatedClientsInQueue);
							break;
						}
					}
				}
			}
		}
						                       				
						
		Client client1;
		if (waitingLineRegular!=null&& waitingLineRegular.length>0){ 
			for(int i=0; i <waitingLineRegular.length;i++){ 
				if (waitingLineRegular[i]!=null && getQueueWithMostAvailableSpots()!=null){
					client = waitingLineRegular[i];
					waitingLineRegular[i] = null;
					client.setWaitingTime(clock-client.getArrivalTime()); 
					for(int j =0;j<getQueueWithMostAvailableSpots().getQueueSize();j++){
						if (getQueueWithMostAvailableSpots().getClientsInQueue()[j]==null){
							Client[] updatedClientsInQueue = getQueueWithMostAvailableSpots().getClientsInQueue();
							updatedClientsInQueue[j] = client;
							getQueueWithMostAvailableSpots().setClientsInQueue(updatedClientsInQueue);
							break;
						}
					}
				}
			}
		}
						
			
		if(clientsWorld!=null && clientsWorld.length!=0){	
			for(int i=0; i<clientsWorld.length; i++){    
				if (clientsWorld[i]!= null && clientsWorld[i].getArrivalTime() == clock){
					for(int j=0; j<waitingLine.length; j++){
						if(waitingLine[j]==null){  				
							waitingLine[j] = clientsWorld[i];							
							totalClientsInSystem = totalClientsInSystem +1; 
							if (tvInWaitingArea == true){
								clientsWorld[i].setPatience(clientsWorld[i].getPatience()+20);
							}else if(coffeeInWaitingArea==true && (2023 -clientsWorld[i].getYearOfBirth())>= 18){  
								clientsWorld[i].setPatience(clientsWorld[i].getPatience()+15);
							}
							break;
						}
					}
				}
			}
		}
	}
		//question 1054
		
	/**
	 *Uses the last function to increase clock by a specific number
	 *
	 * @param time, an int that determines by how many units we want to increase the clock
	 */
	public void increaseTime(int time){
		for(int i=1; i<=time; i++){
			increaseTime();
		}
	}
	/**
	 *get all the clients being served 
	 *
	 * @return an array of clients being served
	 */
	public Client[] getClientsBeingServed(){
		Client[] clients = new Client[queues.length]; 
		if(queues!=null &&queues.length!=0){ 
			for(int j=0; j <queues.length;j++){
				clients[j] = queues[j].getClientBeingServed();
			}
		}
		return clients;
	}
	
	
	/**
	 *gets the VIPQueue that has most available spots
	 *
	 * @returns  VIPQueue that has most available spots
	 */		
	public static VIPQueue getVIPQueueWithMostAvailableSpots(){
		int count = 0;
		VIPQueue VIPQueue_most_available_spots = null;
		int variable =0;
		if(queues!=null &&queues.length!=0){ 
			for(int j=0; j <queues.length;j++){
				if (queues[j].getClientsInQueue()==null){
					queues[j].setAvailableSpots(queues[j].getQueueSize());
				} else{
					for (int i=0;i<queues[j].getQueueSize();i++){
						if (queues[j].getClientsInQueue()[i]==null){
							count = count +1;
						}
					}
					queues[j].setAvailableSpots(count);
				}
				if (queues[j] instanceof VIPQueue && queues[j].getAvailableSpots()>variable){
					VIPQueue_most_available_spots = (VIPQueue)queues[j];
					variable = queues[j].getAvailableSpots();
				}
			}
		}
		return VIPQueue_most_available_spots;
	}
		
	/**
	 *gets the Queue that has most available spots
	 *
	 * @returns  Queue that has most available spots
	 */		
	public static Queue getQueueWithMostAvailableSpots(){
		Queue queue_most_available_spots = null;
		int count = 0;
		int variable1 = 0;
		if(queues!=null &&queues.length!=0){
			for(int j=0; j <queues.length;j++){
				if (queues[j].getClientsInQueue()==null){
					queues[j].setAvailableSpots(queues[j].getQueueSize());
				} else{
					for (int i=0;i<queues[j].getQueueSize();i++){
						if (queues[j].getClientsInQueue()[i]==null){
							count = count +1;
						}
					}
					queues[j].setAvailableSpots(count);
				}			
				if (!(queues[j] instanceof VIPQueue) && queues[j].getAvailableSpots()>variable1){
					queue_most_available_spots = queues[j];
					variable1 = queues[j].getAvailableSpots();
				}
			}
		}
		return queue_most_available_spots;
	} 
	/**
	 *gets an ID representation of the system 
	 *
	 * @returns a String, that has info about the ID representation of the system 
	 */	
	public String returnId(){
		String qu="";
		String[] ids = new String[waitingLineSize];
		String result = "";
		if (waitingLine!= null){ 
			for(int j=0; j <waitingLineSize;j++){
				if(waitingLine[j]!=null){
					ids[j] = String.valueOf(waitingLine[j].getId());
				}else{
					ids[j] = "";
				}
			}
		}
		for (String id: ids ) {
			result += "[" + id + "]";
		}
		String str = "[WaitingLine]-" + result +"\n---\n";
		if (queues!=null){
			for(Queue queue:queues){
				if (queue!=null){
					qu += queue.toString()+"\n";
				}
			}
		}
		return str+qu;
	
	/**
	 *gets info about system related to processing time left of clients
	 *
	 * @returns a String enclosing info about processing time left of clients in the system 
	 */	
	}
	public String toString(){
		String qu ="";
		String[] ids = new String[waitingLineSize];
		String result = "";
		if (waitingLine!= null){ 
			for(int j=0; j <waitingLineSize;j++){
				if(waitingLine[j]!=null){
					ids[j] = String.valueOf(waitingLine[j].getTotalProcessingTime());
				}else{
					ids[j]="";
				}
			}
		}
		for (String id: ids ) {
			result += "[" + id + "]";
		}
		String str = "[WaitingLine]-" + result +"\n---\n";
		if (queues!=null){
			for(Queue queue:queues){
				if (queue!=null){
					qu += queue.toString(false)+"\n";
				}
			}
		}
		return str+qu;
	}
	/**
	 *gets the human readable information about system 
	 *@param showID, a boolean that determines whether or not we want the representation in time processing form, or id representation
	 *
	 * @returns  a String that has information about the clients in the system 
	 */	
	
	public String toString(boolean showID){
		if (showID == true){
			return returnId();
		}else{
			return toString();
		}
	}
}
	
	
			
		
			
			
				
				
			
				
				
				
				
			
			
		
		