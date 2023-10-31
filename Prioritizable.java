/**
 *The Prioritizable interface forces the implementation of methods related to priority
 *requests and vip clients will implement it
 *
 * @author Cheyenne Bajani
 * @version 1.0
 * @since 10/29/2023
 */

public interface Prioritizable {
    void setPriority(int priority); 
    int getPriority(); 
}

