
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class FourthRatings {
    
    private double getAverageByID(String id, int minimalRaters){
        ArrayList<Rater> myRaters = new ArrayList<Rater>();
        myRaters = RaterDatabase.getRaters();
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
    
    private double dotProduct(Rater me, Rater r){
        String meID = me.getID();
        String rID = r.getID();
        double sum = 0.0;
        ArrayList<String> meItemsRated = me.getItemsRated();
        ArrayList<String> rItemsRated = r.getItemsRated();
        //Rater weighted = new PlainRater();
        
        for(String meTemp : meItemsRated){
            double meRatingValue = me.getRating(meTemp);
            if(r.hasRating(meTemp)){
               double rRatingValue = r.getRating(meTemp);
               meRatingValue = meRatingValue -5;
               rRatingValue = rRatingValue -5;
               sum += meRatingValue*rRatingValue;
                
            }
        }
        return sum;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters()){
            if(!r.getID().equals(me.getID())){
                double dotproduct = dotProduct(me,r);
                list.add(new Rating(r.getID(),dotproduct));
                
            }
        }
        Collections.sort(list,Collections.reverseOrder());
        
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> similarList = getSimilarities(id);
        ArrayList<Rating> ans = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        int count = 0;
        
        ArrayList<Rating> posSimilarList = new ArrayList<Rating>();
        for(Rating temp : similarList){
            if(temp.getValue() > 0){
                posSimilarList.add(temp);
                count++;
            }
            if(count == numSimilarRaters){
                break;
            }
        }
        //Collections.sort(posSimilarList,Collections.reverseOrder());
        for(String movie : movies){
            int count2 = 0;
            double sum = 0.0;
            double weightedAverage = 0.0;
            for(Rating temp : posSimilarList){
                Rater currRater = RaterDatabase.getRater(temp.getItem());
                ArrayList<String> items = currRater.getItemsRated();
                if(items.contains(movie)){
                   double value =  currRater.getRating(movie);
                  
                   sum += value * temp.getValue();
                   
                   count2++;
                }
                
                
            }
            if(count2 >= minimalRaters){
                   weightedAverage = sum/count2;
                    
            }
            ans.add(new Rating(movie,weightedAverage));
            
        }
        Collections.sort(ans);
        return ans;
        
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> similarList = getSimilarities(id);
        ArrayList<Rating> ans = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        int count = 0;
                ArrayList<Rating> posSimilarList = new ArrayList<Rating>();
        for(Rating temp : similarList){
            if(temp.getValue() > 0){
                posSimilarList.add(temp);
                count++;
            }
            if(count == numSimilarRaters){
                break;
            }
        }
        //Collections.sort(posSimilarList,Collections.reverseOrder());
        for(String movie : movies){
            int count2 = 0;
            double sum = 0.0;
            double weightedAverage = 0.0;
            for(Rating temp : posSimilarList){
                Rater currRater = RaterDatabase.getRater(temp.getItem());
                ArrayList<String> items = currRater.getItemsRated();
                if(items.contains(movie)){
                   double value =  currRater.getRating(movie);
                  
                   sum += value * temp.getValue();
                   
                   count2++;
                }
                
                
            }
            if(count2 >= minimalRaters){
                   weightedAverage = sum/count2;
                    
            }
            ans.add(new Rating(movie,weightedAverage));
            
        }
        Collections.sort(ans);
        return ans;

    } 
    
    
}
