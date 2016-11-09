
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String movieFile,String ratingsFile){
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(movieFile);
        myRaters = firstRatings.loadRaters(ratingsFile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
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
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        for(Movie movie : myMovies){
            
            double avg = 0.0;
            String id = movie.getID();
            
            avg = getAverageByID(id,minimalRaters);
            if(avg != 0.0){
                Rating rate = new Rating(id,avg);
                avgRatings.add(rate);
            }
        }
        
        return avgRatings;
    }
    
    public String getTitle(String id){
        String ans = "ID not found";
        for(Movie movie : myMovies){
            if(movie.getID().equals(id)){
                ans = movie.getTitle();
            }
        }
        return ans;
    }
    
    public String getID(String title){
        String ans = "No Such Title";
        for(Movie movie : myMovies){
            if(movie.getTitle().equals(title)){
                ans = movie.getID();
            }
        }
        return ans;
    }
    
}
