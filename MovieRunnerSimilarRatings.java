
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerSimilarRatings {
    
    public void printAverageRatings(){
        FourthRatings fourthRatings = new FourthRatings();
        
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of Raters: " + RaterDatabase.size());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());
        
        ArrayList<Rating> rating = fourthRatings.getAverageRatings(35);
        System.out.println("Number of movies rated on average by 35 raters: " + rating.size());
        Collections.sort(rating);
        System.out.println();
        System.out.println("Rating" + "\t" + "Movie Title");
        for(Rating temp : rating){
            System.out.print((Math.round(temp.getValue() * 100.0)/100.0)+" ");
            System.out.println("\t" + MovieDatabase.getTitle(temp.getItem()));
        }
    }
    
    public void printAverageRatingsByYear(){
        FourthRatings fourthRatings = new FourthRatings();
        
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of Raters: " + RaterDatabase.size());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());
        
        Filter myFilter = new YearAfterFilter(2000);
        ArrayList<Rating> rating = fourthRatings.getAverageRatingsByFilter(20,myFilter);
        System.out.println("Number of movies released after year 2000: " + rating.size());
        
        Collections.sort(rating);
        System.out.println();
        System.out.println("RATING" + "\t" + "YEAR" + "\t" + "MOVIE TITLE");
        
        for(Rating temp : rating){
            System.out.print((Math.round(temp.getValue() * 100.0)/100.0)+" ");
            System.out.print("\t" + MovieDatabase.getYear(temp.getItem()) + " ");
            System.out.println("\t" + MovieDatabase.getTitle(temp.getItem()));
        }
    }
    
    public void printSimilarRatings(){
        FourthRatings fourthRatings = new FourthRatings();
        
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of Raters: " + RaterDatabase.size());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());
        
        ArrayList<Rating> rating = fourthRatings.getSimilarRatings("71", 20,5);
        Collections.sort(rating,Collections.reverseOrder());
        System.out.println();
        System.out.println("RATING" + "\t" + "MOVIE TITLE");
        
        for(Rating temp : rating){
            System.out.print((Math.round(temp.getValue() * 100.0)/100.0)+" ");
            System.out.println("\t" + MovieDatabase.getTitle(temp.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenre(){
        FourthRatings fourthRatings = new FourthRatings();
        
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of Raters: " + RaterDatabase.size());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());
        
        Filter myFilter = new GenreFilter("Mystery");
        
        System.out.println("Number of movies falling in genre: Mystery");

        ArrayList<Rating> rating = fourthRatings.getSimilarRatingsByFilter("964", 20, 5, myFilter);
        Collections.sort(rating,Collections.reverseOrder());
        
        System.out.println();
        System.out.println("RATING" + "\t" + "MOVIE TITLE");
        for(Rating temp : rating){
            System.out.print((Math.round(temp.getValue() * 100.0)/100.0)+" ");
            
            System.out.println("\t" + MovieDatabase.getTitle(temp.getItem()));
            System.out.print("\t\t" + "GENRE: " + MovieDatabase.getGenres(temp.getItem()) + " ");
            System.out.println();
        }
        
    }
    
    public void printSimilarRatingsByDirector(){
        FourthRatings fourthRatings = new FourthRatings();
        
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of Raters: " + RaterDatabase.size());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());
        
        Filter myFilter = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        
        System.out.println("Movies from directors: Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> rating = fourthRatings.getSimilarRatingsByFilter("120", 10, 2, myFilter);
        Collections.sort(rating,Collections.reverseOrder());
        System.out.println();
        System.out.println("RATING" + "\t" + "MOVIE TITLE");
        for(Rating temp : rating){
            System.out.print((Math.round(temp.getValue() * 100.0)/100.0)+" ");
            
            System.out.println("\t" + MovieDatabase.getTitle(temp.getItem()));
            System.out.print("\t\t" + "DIRECTOR: " + MovieDatabase.getDirector(temp.getItem()) + " ");
            System.out.println();
            
        }
        
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings fourthRatings = new FourthRatings();
        
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of Raters: " + RaterDatabase.size());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());
        
        Filter one = new GenreFilter("Drama");
        Filter two = new MinutesFilter(80,160);
        
        System.out.println("Number of movies from genre Drama and having duration 80-160mins");

        AllFilters myFilter = new AllFilters();
        myFilter.addFilter(one);
        myFilter.addFilter(two);
        
        
        ArrayList<Rating> rating = fourthRatings.getSimilarRatingsByFilter("168", 10, 3, myFilter);
        Collections.sort(rating,Collections.reverseOrder());
        System.out.println();
       
        for(Rating temp : rating){
            
            
            
            System.out.println("MOVIE TITLE: " +MovieDatabase.getTitle(temp.getItem()) + " ");
            System.out.println("MINUTES" + "\t" + "RATING" + "\t" + "GENRE");
            System.out.print(MovieDatabase.getMinutes(temp.getItem()));
            System.out.print("\t" + (Math.round(temp.getValue() * 100.0)/100.0));
            
            System.out.print("\t" + MovieDatabase.getGenres(temp.getItem()));
            System.out.println();
            System.out.println();
            
        }
        
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings fourthRatings = new FourthRatings();
        
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of Raters: " + RaterDatabase.size());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());
        
        Filter one = new YearAfterFilter(1975);
        Filter two = new MinutesFilter(70,200);
        
        System.out.println("Number of movies released after year 1975 and having duration between 70-200mins");
        AllFilters myFilter = new AllFilters();
        myFilter.addFilter(one);
        myFilter.addFilter(two);
        
        
        ArrayList<Rating> rating = fourthRatings.getSimilarRatingsByFilter("314", 10, 5, myFilter);
        Collections.sort(rating,Collections.reverseOrder());
        System.out.println();
       
        for(Rating temp : rating){
            
            
            
            System.out.println("MOVIE TITLE: " + MovieDatabase.getTitle(temp.getItem()) + " ");
            System.out.println("MINUTES" + "\t" + "YEAR" + "\t" + "RATING");
            System.out.print( MovieDatabase.getMinutes(temp.getItem()));
            System.out.print("\t" + MovieDatabase.getYear(temp.getItem()));
            System.out.print("\t" + (Math.round(temp.getValue() * 100.0)/100.0));
            
            //System.out.print(" " + MovieDatabase.getGenres(temp.getItem()));
            System.out.println();
            System.out.println();
            
        }
        
    }
}
