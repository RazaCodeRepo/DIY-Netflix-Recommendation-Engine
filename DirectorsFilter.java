
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    private String directors;
    
    public DirectorsFilter(String direc){
        directors = direc;
        
    }
    
    public boolean satisfies(String id){
        String tempDirectors = MovieDatabase.getDirector(id);
        if(directors.contains(",")){
            
            String[] dir_array = directors.split(",");
            for(int i=0;i<dir_array.length;i++){
                
                int check = 0;
                String temp = dir_array[i];
                if(tempDirectors.indexOf(temp) != -1){
                    check = 1;
                }
                if(check == 1){
                    return true;
                }
            }
            
        }
        else{
            if(tempDirectors.indexOf(directors) != -1){
                return true;
            }
        }
        return false;
    }
}
