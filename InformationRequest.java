/**
 *This InformationRequest Class extends request and is 
 *a type of request that has to do with information 
 *
 * @author Cheyenne Bajani
 * @version 1.0
 * @since 10/29/2023
 */
public class InformationRequest extends Request{
	private String[] questions;
	
	public String[] getQuestions() { 
        return questions; 
    }

    public void setQuestions(String[] questions) {
        this.questions = questions;
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
	
	public InformationRequest (String description, int priority, int difficulty,
	String[] questions){
		setQuestions(questions);
		setDescription(description);
		setPriority(priority);
		setDifficulty(difficulty);
		setFactor(1);
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
		return getDifficulty() * getFactor()* questions.length;
	}
	}
		
