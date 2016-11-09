
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerAverage {
    
    public void printAverageRatings(){
        SecondRatings secondRatings = new SecondRatings();
        System.out.println("Number of movies: " + secondRatings.getMovieSize());
        System.out.println("Number of Raters: " + secondRatings.getRaterSize());
        //ArrayList<Rating> rating = new ArrayList<Rating>();
        ArrayList<Rating> rating = secondRatings.getAverageRatings(12);
        System.out.println("number of movies with 50 or more ratings: " + rating.size());
        Collections.sort(rating);
        for(Rating temp : rating){
            System.out.print(temp.getValue()+" ");
            System.out.println(secondRatings.getTitle(temp.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings secondRatings = new SecondRatings();
        ArrayList<Rating> rating = secondRatings.getAverageRatings(5);
        String id = secondRatings.getID("Vacation");
        for(Rating temp : rating){
            if(temp.getItem().equals(id)){
                System.out.println(temp.getValue());
            }
        }
    }

}
