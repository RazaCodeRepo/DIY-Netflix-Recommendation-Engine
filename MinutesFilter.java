
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter {
    private int max;
    private int min;
    
    public MinutesFilter(int minMin,int maxMin){
        min = minMin;
        max = maxMin;
    }
    
    public boolean satisfies(String id){
        int dur = MovieDatabase.getMinutes(id);
        if(dur >= min && dur <= max){
            return true;
        }
        return false;
    }
}
