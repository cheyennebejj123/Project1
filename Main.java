public class Main {
    public static void main(String[] args) {
	Client[] clientsWorld = new Client[] {
    new Client("John", "Doe", 1978, "male", 3, 2),
    new Client("Jane", "Doe", 1990, "female", 1, 3),
    new Client("Mike", "Johnson", 1968, "male", 2, 5),
    new Client("Emily", "Smith", 2010, "female", 3, 2),
    new Client("Chris", "Brown", 1992, "male", 1, 3),
    new Client("Jessica", "White", 2001, "female", 2, 5),
    new Client("Daniel", "Green", 2004, "male", 6, 2),
    new Client("Sarah", "Taylor", 2000, "female", 1, 3),
    new Client("Brian", "Adams", 2000, "male", 3, 4)
	};
	QueueSystem newsystem = new QueueSystem(3, true, false);
	Queue[] queueArray = new Queue[] {
    new Queue("B", 2),
    new Queue("B", 2)
	};
	newsystem.setClientsWorld(clientsWorld);
	newsystem.setQueues(queueArray);
	System.out.println(clientsWorld[0]);
	System.out.println(clientsWorld[0].estimateServiceLevel());
	System.out.println(newsystem.toString());
	newsystem.increaseTime();
	System.out.println(queueArray[0]);
	System.out.println(newsystem.toString());
	System.out.println(queueArray[0]);
	System.out.println(newsystem.toString());
	System.out.println(newsystem.getClock());
	System.out.println("\n\n\n\n");
//	System.out.println(clientsWorld[4].getId());
newsystem.increaseTime();
	Client[] clientsInFirstQueue = queueArray[0].getClientsInQueue();
if (clientsInFirstQueue != null) {
    for (Client client : clientsInFirstQueue) {
        if (client != null) {
            System.out.println(client);
        } else {
            System.out.println("null");
        }
    }
} else {
    System.out.println("The first queue is null or empty.");
}
newsystem.increaseTime();
newsystem.increaseTime();
	Client[] waitingLineRegular = newsystem.getWaitingLineRegular();
if (waitingLineRegular != null) {
    for (Client client : waitingLineRegular) {
        if (client != null) {
            System.out.println(client);
        } else {
            System.out.println("null");
        }
    }
} else {
    System.out.println("The waiting line is null.");
}
	System.out.println(newsystem.returnId());
	
		
    }
}