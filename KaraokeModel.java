import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class KaraokeModel {
    private String songName;
    private int songLength; // seconds
    private List<String> singerNameList;

    public KaraokeModel() {
        singerNameList = new ArrayList<>();
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public int getSongLength() {
        return songLength;
    }

    public void setSongLength(int songLength) {
        this.songLength = songLength;
    }

    public List<String> getSingerNameList() {
        return singerNameList;
    }

    public void addSinger(String singerName) {
        if (singerNameList.size() < 3)
            singerNameList.add(singerName);
    }

    public int getScore() {
        // 1 singers
        if (singerNameList.size() == 1) {
            int sumPrevLength = 0;
            try {
                sumPrevLength = Files.lines(Paths.get("Karaoke.csv"))
                    .mapToInt(line -> Integer.parseInt(line.split(",")[1]))
                    .sum();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sumPrevLength % 100;
        }
        // 2 singers
        if (singerNameList.size() == 2) {
            int sumSingerLength = singerNameList.stream()
                .mapToInt(String::length)
                .sum();
            return (songLength * sumSingerLength) % 100;
        }
        // 3 singers
        int sumPrevSingersLength = 0;
        try {
            sumPrevSingersLength = Files.lines(Paths.get("Karaoke.csv"))
                .mapToInt(line -> {
                    String[] tokens = line.split(",");
                    return tokens[2].length() +
                        tokens[3].length() +
                        tokens[4].length();
                })
                .sum();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sumSingerLength = singerNameList.stream()
            .mapToInt(String::length)
            .sum();
        return (sumPrevSingersLength * sumSingerLength) % 100;
    }

    public void WriteCSV(String songName, int songLength, List<String> singerNameList, int score) {
        Path path = Paths.get("Karaoke.csv");
        try (FileWriter fileWriter = new FileWriter(path.getFileName().toString(), true);
            PrintWriter output = new PrintWriter(fileWriter)) {
            output.print(songName + "," + songLength);
            for (int i = 0; i < 3; i++) {
                if (i < singerNameList.size())
                    output.print("," + singerNameList.get(i));
                else
                    output.print(",");
            }
            output.println("," + score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
