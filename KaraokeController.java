import java.util.Scanner;

public class KaraokeController {
    private KaraokeModel model;
    private KaraokeView view;

    public KaraokeController(KaraokeModel model, KaraokeView view) {
        this.model = model;
        this.view = view;
    }

    public void setKaraokeInfoFromUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter song name: ");
        String songName = scanner.nextLine();
        model.setSongName(songName);

        System.out.print("Enter song length (seconds): ");
        int songLength = Integer.parseInt(scanner.nextLine());
        model.setSongLength(songLength);

        System.out.print("Enter number of singer: ");
        int singerNum = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < singerNum; i++) {
            System.out.print("Enter singer name: ");
            String singerName = scanner.nextLine();
            model.addSinger(singerName);
        }

        scanner.close();
    }

    public void updateView() {
        view.printKaraokeDetails(model.getSongName(), model.getSongLength(), model.getSingerNameList(), model.getScore());   
    }

    public void updateStorage() {
        model.WriteCSV(model.getSongName(), model.getSongLength(), model.getSingerNameList(), model.getScore());
    }
}
