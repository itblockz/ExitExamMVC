import java.util.List;

public class KaraokeView {
    public void printKaraokeDetails(String songName, int songLength, List<String> singerNameList, int score) {
        System.out.println("-----------------KARAOKE-----------------");
        System.out.println("Song name: " + songName);
        System.out.println("Song length: " + songLength + " seconds");
        for (int i = 0; i < singerNameList.size(); i++) {
            System.out.printf("Singer %d name: %s%n", i+1, singerNameList.get(i));
        }
        System.out.println("Score: " + score);
    }
}
