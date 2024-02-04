public class KaraokeMain {
    public static void main(String[] args) {
        KaraokeModel model = new KaraokeModel();
        KaraokeView view = new KaraokeView();
        KaraokeController controller = new KaraokeController(model, view);

        controller.setKaraokeInfoFromUserInput();
        controller.updateView();
        controller.updateStorage();
    }
}
