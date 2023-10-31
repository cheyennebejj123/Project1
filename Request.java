/**
 *The request class is abstract and could be one of the three requests mentioned
 *Clients will have a list of requests that need to be processed. 
 * @author Cheyenne Bajani
 * @version 1.0
 * @since 10/29/2023
 */

public abstract class Request implements Prioritizable { 
	private String description;
	private int priority; 
	private int difficulty;
	private int factor;
	private int startTime;
	private int endTime;
	private Status status; 
	
	public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getFactor() {
        return factor;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
	
	public abstract int calculateProcessingTime();
}
	