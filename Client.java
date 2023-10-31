/**
 *The client class represents a client that have their information stored
 *They will have requests to process. 
 *
 * @author Cheyenne Bajani
 * @version 1.0
 * @since 10/29/2023
 */

public class Client{  
	private int id;     
	private String firstName;
	private String lastName;
	private int yearOfBirth;
	private Request[] requests;
	private int waitingTime;
	private int timeInQueue;
	private int arrivalTime;
	private int serviceTime;
	private int departureTime;
	private int patience;
	private Gender gender; 
	private static int counter = 0;
	
	public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public Request[] getRequests() {
        return requests;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getTimeInQueue() {
        return timeInQueue;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public int getPatience() {
        return patience;
    }

    public Gender getGender() {  
        return gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setRequests(Request[] requests) {
        this.requests = requests;
    }

    public void setWaitingTime(int waitingTime) {
		if (waitingTime>=0){ 
			this.waitingTime = waitingTime;
		}
    }

    public void setTimeInQueue(int timeInQueue) {
		if (timeInQueue>=0){
			this.timeInQueue = timeInQueue;
		}
    }

    public void setArrivalTime(int arrivalTime) { 
		if (arrivalTime>1){ 
			this.arrivalTime = arrivalTime;
		}else {
			this.arrivalTime = 1+QueueSystem.getClock();
    }}

    public void setServiceTime(int serviceTime) { 
        this.serviceTime = serviceTime;
    }

    public void setDepartureTime(int departureTime) { 
		if (departureTime == 0 || departureTime>= this.arrivalTime+this.waitingTime+this.timeInQueue){
			this.departureTime = departureTime;
		}
    }

    public void setPatience(int patience) {
        this.patience = patience;
    }

    public void setGender(String gender) {
         if ("male".equalsIgnoreCase(gender)) {
            this.gender = Gender.MALE;
        } else if ("female".equalsIgnoreCase(gender)) {
            this.gender = Gender.FEMALE;
    }
	}
	
	/**
	 *initializes a Client
	 *
	  * @param firstName   The first name of the client.
 * @param lastName    The last name of the client.
 * @param birthYear   The birth year of the client.
 * @param gender      The gender of the client.
 * @param arrivalTime The arrival time of the client.
 * @param patience    The patience level of the client, represented as an integer.
 * @param requests    An array of Request objects associated with the client.
 */
	public Client(String firstName, String lastName, int yearOfBirth, String gender, int arrivalTime, int patience,Request[] requests){ 
		counter = counter +1;
		setId(counter);
		setFirstName(firstName);
		setLastName(lastName);
		setYearOfBirth(yearOfBirth);
		setGender(gender);
		setArrivalTime(arrivalTime);  
		setPatience(patience);
	//	setRequests(requests);
		setWaitingTime(0);
		setTimeInQueue(0);
		setServiceTime(0);
		setDepartureTime(0);	
		/*if (counter == 1){
			Client[] clientArray = new Client[1];  //logic makes sense?
			clientArray[0] = this;
			QueueSystem.setClientsWorld(clientArray);
		}else{
			Client[] clientArray = new Client[getClientsWorld().length+1];
			System.arraycopy(clientArray, 0, getClientsWorld(), 0);
			clientArray[clientArray.length-1]= this;
		*/
		}
		/**
	 *initializes a Client
	 *
	  * @param firstName   The first name of the client.
 * @param lastName    The last name of the client.
 * @param birthYear   The birth year of the client.
 * @param gender      The gender of the client.
 * @param patience    The patience level of the client, represented as an integer.
 * @param requests    An array of Request objects associated with the client.
 */
	public Client (String firstName, String lastName, int yearOfBirth, String gender,
	int patience, Request[] requests){
		counter = counter +1;
		setId(counter);
		setFirstName(firstName);
		setLastName(lastName);
		setYearOfBirth(yearOfBirth);
		setGender(gender);
		setPatience(patience);
		setRequests(requests);
		setWaitingTime(0);
		setTimeInQueue(0);
		setServiceTime(0);
		setDepartureTime(0);	
		setArrivalTime(1+QueueSystem.getClock());
		/*if (counter == 1){
			Client[] clientArray = new Client[1];  //logic makes sense?
			clientArray[0] = this;
			QueueSystem.setClientsWorld(clientArray);
		}else{
			Client[] clientArray = new Client[getClientsWorld().length+1];
			System.arraycopy(clientArray, 0, getClientsWorld(), 0);
			clientArray[clientArray.length-1]= this;
		}*/
	}
	/**
	*initializes a Client
	*
	* @param firstName   The first name of the client.
	 * @param lastName    The last name of the client.
	 * @param birthYear   The birth year of the client.
	 * @param gender      The gender of the client.
	 * @param arrivalTime The arrival time of the client.
	 * @param patience    The patience level of the client, represented as an integer.
	 */
	public Client (String firstName, String lastName, int yearOfBirth, String gender,
	int patience){
		counter = counter +1;
		setId(counter);
		setFirstName(firstName);
		setLastName(lastName);
		setYearOfBirth(yearOfBirth);
		setGender(gender); //is this right?
		setPatience(patience);
		setWaitingTime(0);
		setTimeInQueue(0);
		setServiceTime(0);
		setDepartureTime(0);
		setArrivalTime(1+QueueSystem.getClock());
}
		/*if (counter == 1){
			Client[] clientArray = new Client[1];  //logic makes sense?
			clientArray[0] = this;
			QueueSystem.setClientsWorld(clientArray);
		}else{
			Client[] clientArray = new Client[getClientsWorld().length+1];
			System.arraycopy(clientArray, 0, getClientsWorld(), 0);
			clientArray[clientArray.length-1]= this;
		}*/

	/**
	*gets the level of service 
	*
	 *@return an int representing level of the service 
	 */
	public int estimateServiceLevel(){
		int result = 0;
		int returned_value = 0;
		if (departureTime == 0) {  
			return -1;
		}
		if (waitingTime > 4){
			result = result +1;
		}if (waitingTime > 6){
			result = result +1;
		}if (waitingTime > 8){
			result = result +1;
		}if (waitingTime > 10){
			result = result +1;
		}if (waitingTime > 12){
			result = result +1;
		}if (timeInQueue > 4){
			result = result +1;
		}if (timeInQueue > 6){
			result = result +1;
		}if (timeInQueue > 8){
			result = result +1;
		}if (timeInQueue > 10){
			result = result +1;
		}if (timeInQueue > 12){
			result = result +1;
		}
		
		returned_value = 10 - result;
		return returned_value;
	}
		
	/**
	 *gets the info about a client  
	 *
	 * @returns a Sttring containing all information about client  
	 */
	public String toString(){ 
		String name =""; 
		if(QueueSystem.getQueues()!=null){
		for(int i=0; i<QueueSystem.getQueues().length; i++){ 
			if(QueueSystem.getQueues()[i].getClientsHistory()!=null && QueueSystem.getQueues()[i].getClientsHistory().length>0){
				for (int j = 0; j<QueueSystem.getQueues()[i].getClientsHistory().length; j++){
				if ((QueueSystem.getQueues()[i].getClientsHistory()[j].getFirstName() == this.firstName)&&(QueueSystem.getQueues()[i].getClientsHistory()[j].getLastName() == this.lastName)){
					name = QueueSystem.getQueues()[i].getServerName();
				}
			}
		}
		}
		}
		String str;
		str = "Client: " + lastName + ", " + firstName + "\n**Arrival Time : " + arrivalTime + "\n**Waiting Time : " + waitingTime+ "\n**Time in Queue : " + timeInQueue + "\n**Service Time : " + serviceTime + "\n**Departure Time : " + departureTime+ "\n**Served by server : " + name +"\n**Service Level : " + this.estimateServiceLevel(); 
		return str;
	}
	/**
	 *gets processingTime of all requests of client 
	 *
	 * @returns an int representing all time needed to process all requests of the client 
	 */
	public int getTotalProcessingTime(){
		int total = 0;
		if (requests!=null){
			for (int i=0;i<requests.length;i++){
				if (requests[i]!=null){
					total = total + requests[i].calculateProcessingTime();
				}
			}
		}
		return total;
	}
	
	public int getPriority() {
		return 0;
	}
  
	public int getMemberSince(){
	  return 0;
  }
  
}

	

	
	
	
	
	
	
	