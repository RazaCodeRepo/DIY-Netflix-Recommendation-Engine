
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        FileResource file = new FileResource(filename);
        CSVParser parser = file.getCSVParser();
        for(CSVRecord record : parser){
            String movieId = record.get("id");
            String movieTitle = record.get("title");
            String movieYear = record.get("year");
            String movieCountry = record.get("country");
            String movieGenre = record.get("genre");
            String movieDirector = record.get("director");
            int movieMinutes = Integer.parseInt(record.get("minutes"));
            String moviePoster = record.get("poster");
            
            Movie movie = new Movie(movieId,movieTitle,movieYear,movieGenre,movieDirector,movieCountry,moviePoster,movieMinutes);
            movieList.add(movie);
        }
        
        return movieList;
    }
    
    public void testLoadMovies(){
        String filename = "ratedmoviesfull.csv";
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        movieList = loadMovies(filename);
        System.out.println("Number of movies: " + movieList.size());
        /*for(Movie temp : movieList){
            System.out.println(temp.toString());
        }*/
        
        
        int countComedy = 0;
        for(Movie temp : movieList){
            String genre = temp.getGenres();
            //System.out.println(genre);
            if(genre.contains("Comedy")){
                countComedy++;
            }
        }
        System.out.println("genre: " + countComedy);
        
        int countTime = 0;
        for(Movie temp : movieList){
            int time = temp.getMinutes();
            if(time > 150){
                countTime++;
            }
        }
        System.out.println("duration: " +countTime);
        
        HashMap<String,Integer> directorsMap = new HashMap<String,Integer>();
        for(Movie temp : movieList){
            String directors = temp.getDirector();
            if(directors.contains(",")){
                String[] directorArray = directors.split(", ");
                //System.out.println("if");
                for(String tempDir : directorArray){
                    if(directorsMap.containsKey(tempDir)){
                         directorsMap.put(tempDir,directorsMap.get(tempDir)+1);
                    }
                    else{
                        directorsMap.put(tempDir,1);
                    }
                    //System.out.println(tempDir);
                }
                //System.out.println("end of if");
            }
            else{
                if(directorsMap.containsKey(directors)){
                         directorsMap.put(directors,directorsMap.get(directors)+1);
                    }
                    else{
                        directorsMap.put(directors,1);
                    }
            }
            
        }
        int max = 0;
        for(int temp : directorsMap.values()){
            if(temp > max){
                max = temp;
            }
        }
        System.out.println("directors: " + max);
        for(String dir : directorsMap.keySet()){
            if( directorsMap.get(dir) == max){
                System.out.println(dir);
            }
        }
        
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> ratingsList = new ArrayList<Rater>();
        ArrayList<String> repeat_id = new ArrayList<String>();
        FileResource file = new FileResource("data/"+filename);
        CSVParser parser = file.getCSVParser();
        for(CSVRecord record : parser){
            String raterId = record.get("rater_id");
            String movieId = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));
            if(repeat_id.contains(raterId)){
                for(Rater rater : ratingsList){
                    if(raterId.equals(rater.getID())){
                        rater.addRating(movieId,rating);
                    }
                } 
            }
            else{
                Rater rater = new EfficientRater(raterId);
                rater.addRating(movieId,rating);
                ratingsList.add(rater);
                repeat_id.add(raterId);
            }
        }
        return ratingsList;
    }
    
    public void testLoadRaters(){
        String filename = "ratings.csv";
        
        ArrayList<Rater> ratingsList = loadRaters(filename);
        
        System.out.println("Total Number of raters: " + ratingsList.size());
        /*for(Rater rater : ratingsList){
            System.out.println("Rater ID: " + rater.getID());
            System.out.println("total number of ratings: " + rater.numRatings());
            ArrayList<String> temp = rater.getItemsRated();
            for(String show : temp){
                System.out.println("rating=" + rater.getRating(show)+"movie id="+show);
            }
        }*/
        String raterId = "193";
        for(Rater rater : ratingsList){
            if(rater.getID().equals(raterId)){
                System.out.println("Number of ratings by rater of id " + raterId + " is: " + rater.getItemsRated().size());
            }
        }
       
        int maxNum = 0;
        for(Rater rater : ratingsList){
            int temp = rater.getItemsRated().size();
            if(temp > maxNum){
                maxNum = temp;
            }
        }
        System.out.println("maximum number of ratings by any raters: " + maxNum);
        int count = 0;
        ArrayList<String> whoRater = new ArrayList<String>();
        for(Rater rater : ratingsList){
            if(rater.getItemsRated().size() == maxNum){
                count++;
                whoRater.add(rater.getID());
            }
        }
        System.out.println("Number of raters with maximum rating: " + count);
        for(String temp : whoRater){
            System.out.println("rater id of raters with maximum number of ratings: " + temp);
        }
        int countRater = 0;
        
        String movieId = "1798709";
        for(Rater rater : ratingsList){
            if(rater.hasRating(movieId)){
                countRater++;
            }
        }
        System.out.println("Number of ratings of movie with id " + movieId + " = " + countRater);
        ArrayList<String> uniqueMovies = new ArrayList<String>();
        for(Rater rater : ratingsList){
            ArrayList<String> ans = rater.getItemsRated();
            for(String temp : ans){
                if(!uniqueMovies.contains(temp)){
                    uniqueMovies.add(temp);
                }
            }
        }
        System.out.println("Number of unique Movies: " + uniqueMovies.size());
    }
}
