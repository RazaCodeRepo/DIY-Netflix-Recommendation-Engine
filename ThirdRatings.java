
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {
    
    
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsFile){
        FirstRatings firstRatings = new FirstRatings();
        
        myRaters = firstRatings.loadRaters(ratingsFile);
    }
    
   
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters){
        int count = 0;
        int checkRaters = 0;
        double avg = 0.0;
        double sum = 0.0;
        for(Rater rater : myRaters){
            if(rater.hasRating(id)){
                checkRaters++;
                sum += rater.getRating(id);
            }
        }
        if(checkRaters >= minimalRaters){
            avg = sum/checkRaters;
            return avg;
        }
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        for(String movie : movies){
            
            double avg = 0.0;
            //String id = movie.getID();
            
            avg = getAverageByID(movie,minimalRaters);
            if(avg != 0.0){
                Rating rate = new Rating(movie,avg);
                avgRatings.add(rate);
            }
        }
        
        return avgRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> ans = new ArrayList<Rating>();
        for(String movie : movies){
            
                double avg = getAverageByID(movie,minimalRaters);
                if(avg != 0.0){
                    Rating rate = new Rating(movie,avg);
                    ans.add(rate);
                }
            
        }
        return ans;
    }
    
    

}
