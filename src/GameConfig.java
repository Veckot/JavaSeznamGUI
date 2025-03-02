import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameConfig {
    public List<Game> games = new ArrayList<>();

    public void add(Game g) {
        games.add(g);
    }

    public void remove(Game g) { games.remove(g); }

    public List<Game> getGames() {
        return new ArrayList<>(games);
    }

    public void loadGames(String filename, String semicolon) throws GameException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                add(parseGame(line, semicolon));
            }
        } catch (FileNotFoundException exception) {
            throw new GameException("Soubor: " + filename + " nebyl nalezen: " + exception.getLocalizedMessage());
        } catch (GameException e) {
            throw new GameException("Chyba při načítání souboru: " + e.getMessage());
        }
    }

    public void saveGames(String filepath, String semicolon) throws GameException{
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filepath)))){
            for(Game game : games){
                writer.println(game.getName() + semicolon + game.getMark() + semicolon + game.isBought());
            }
        } catch (IOException exception){
            throw new GameException("Faild to write to file: " + filepath + exception.getLocalizedMessage());
        }
    }

    private Game parseGame(String line, String semicolon) throws GameException {
        String[] polozky = line.split(semicolon);
        if (polozky.length != 3) {
            throw new GameException("Chybný počet údajů v řádku: " + line);
        }
        String name = polozky[0].trim();
        int mark = Integer.parseInt(polozky[1].trim());
        boolean isbought = Boolean.parseBoolean(polozky[2].trim());

        return new Game(name, mark, isbought);
    }
}
