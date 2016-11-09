
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters {
    
    public void printAverageRatings(){
        ThirdRatings thirdRatings = new ThirdRatings();
        
        System.out.println("Number of Raters: " + thirdRatings.getRaterSize());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("Number of movies in the database: " + MovieDatabase.size());
        
        ArrayList<Rating> rating = thirdRatings.getAverageRatings(35);
        System.out.println("number of movies with this much ratings: " + rating.size());
        Collections.sort(rating);
        for(Rating temp : rating){
            System.out.print(temp.getValue()+" ");
            System.out.println(MovieDatabase.getTitle(temp.getItem()));
        }
    }
    
    public void printAverageRatingsByYear(){
        ThirdRatings thirdRatings = new ThirdRatings();
        
        System.out.println("Number of Raters: " + thirdRatings.getRaterSize());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("Number of movies in the database: " + MovieDatabase.size());
        
        Filter myFilter = new YearAfterFilter(2000);
        ArrayList<Rating> rating = thirdRatings.getAverageRatingsByFilter(20,myFilter);
        System.out.println("number of movies with this much ratings: " + rating.size());
        
        Collections.sort(rating);
        for(Rating temp : rating){
            System.out.print(temp.getValue()+" ");
            System.out.print(MovieDatabase.getYear(temp.getItem()) + " ");
            System.out.println(MovieDatabase.getTitle(temp.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre(){
        ThirdRatings thirdRatings = new ThirdRatings();
        
        System.out.println("Number of Raters: " + thirdRatings.getRaterSize());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("Number of movies in the database: " + MovieDatabase.size());
        
        Filter myFilter = new GenreFilter("Comedy");
        ArrayList<Rating> rating = thirdRatings.getAverageRatingsByFilter(20,myFilter);
        System.out.println("number of movies with this much ratings: " + rating.size());
        
        Collections.sort(rating);
        for(Rating temp : rating){
            System.out.print(temp.getValue()+" ");
            
            System.out.println(MovieDatabase.getTitle(temp.getItem()));
            System.out.print(MovieDatabase.getGenres(temp.getItem()) + " ");
            System.out.println();
        }
    }
    
    public void printAverageRatingsByMinutes(){
        ThirdRatings thirdRatings = new ThirdRatings();
        
        System.out.println("Number of Raters: " + thirdRatings.getRaterSize());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("Number of movies in the database: " + MovieDatabase.size());
        
        Filter myFilter = new MinutesFilter(105,135);
        ArrayList<Rating> rating = thirdRatings.getAverageRatingsByFilter(5,myFilter);
        System.out.println("number of movies with this much ratings: " + rating.size());
        
        Collections.sort(rating);
        for(Rating temp : rating){
            System.out.print(temp.getValue()+" ");
            System.out.print("time: " + MovieDatabase.getMinutes(temp.getItem()) + " ");
            System.out.print(MovieDatabase.getTitle(temp.getItem()));
            System.out.println();
            
        }
    }
    
    public void printAverageRatingsByDirectors(){
        ThirdRatings thirdRatings = new ThirdRatings();
        
        System.out.println("Number of Raters: " + thirdRatings.getRaterSize());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("Number of movies in the database: " + MovieDatabase.size());
        
        Filter myFilter = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> rating = thirdRatings.getAverageRatingsByFilter(4,myFilter);
        System.out.println("number of movies with this much ratings: " + rating.size());
        
        Collections.sort(rating);
        for(Rating temp : rating){
            System.out.print(temp.getValue()+" ");
            
            System.out.println(MovieDatabase.getTitle(temp.getItem()));
            System.out.print(MovieDatabase.getDirector(temp.getItem()) + " ");
            System.out.println();
            
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings thirdRatings = new ThirdRatings();
        
        System.out.println("Number of Raters: " + thirdRatings.getRaterSize());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("Number of movies in the database: " + MovieDatabase.size());
        
        Filter one = new YearAfterFilter(1990);
        Filter two = new GenreFilter("Drama");
        
        AllFilters myFilter = new AllFilters();
        myFilter.addFilter(one);
        myFilter.addFilter(two);
        ArrayList<Rating> rating = thirdRatings.getAverageRatingsByFilter(8,myFilter);
        System.out.println("number of movies with this much ratings: " + rating.size());
        
        Collections.sort(rating);
        for(Rating temp : rating){
            System.out.print(temp.getValue()+" ");
            
            System.out.print(MovieDatabase.getYear(temp.getItem())+" ");
            System.out.println(MovieDatabase.getTitle(temp.getItem()));
            System.out.print(" " + MovieDatabase.getGenres(temp.getItem()));
            System.out.println();
            
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRatings thirdRatings = new ThirdRatings();
        
        System.out.println("Number of Raters: " + thirdRatings.getRaterSize());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("Number of movies in the database: " + MovieDatabase.size());
        
        Filter one = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        Filter two = new MinutesFilter(90,180);
        
        AllFilters myFilter = new AllFilters();
        myFilter.addFilter(one);
        myFilter.addFilter(two);
        ArrayList<Rating> rating = thirdRatings.getAverageRatingsByFilter(3,myFilter);
        System.out.println("number of movies with this much ratings: " + rating.size());
        
        Collections.sort(rating);
        for(Rating temp : rating){
            System.out.print(temp.getValue()+" ");
            
            System.out.print("time: " + MovieDatabase.getMinutes(temp.getItem()) + " ");
            System.out.println(MovieDatabase.getTitle(temp.getItem()));
            System.out.print(" " + MovieDatabase.getDirector(temp.getItem()));
            System.out.println();
            
        }
    } 

}
