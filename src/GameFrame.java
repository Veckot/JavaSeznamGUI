import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame{
    private static final String FILE_NAME = "seznam.txt";
    private static final String SEMICOLON = ";";
    private JButton btLast;
    private JButton btNext;
    private JCheckBox isBought;
    private JRadioButton radHigh;
    private JRadioButton radMid;
    private JRadioButton radLow;
    private JTextField tfInfo;
    private JPanel pnMain;
    private JButton btSave;
    private JButton btNew;
    private JTable table1;

    private int actualIndex = 0;
    private GameConfig data = new GameConfig();

    public GameFrame() throws GameException {
        data.loadGames(FILE_NAME, SEMICOLON);
        initComponents();
        btNext.addActionListener(e -> moveIndex(1));
        btLast.addActionListener(e -> moveIndex(-1));
        btSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selection = 0;
                if (radLow.isSelected()){
                    selection = 1;
                }
                if (radMid.isSelected()){
                    selection = 2;
                }
                if (radHigh.isSelected()){
                    selection = 3;
                }
                data.games.set(actualIndex, new Game(tfInfo.getText() ,selection, isBought.isSelected()));
                try {
                    data.saveGames(FILE_NAME, SEMICOLON);
                } catch (GameException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selection = 0;
                if (radLow.isSelected()){
                    selection = 1;
                }
                if (radMid.isSelected()){
                    selection = 2;
                }
                if (radHigh.isSelected()){
                    selection = 3;
                }
                data.games.add(new Game(tfInfo.getText() ,selection, isBought.isSelected()));
                try {
                    data.saveGames(FILE_NAME, SEMICOLON);
                } catch (GameException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        table1.setModel(new TableModel(data.getGames()));
    }

    public void moveIndex(int how) {
        int latest = actualIndex;
        actualIndex += how;
        if (actualIndex >= 0 && actualIndex < data.games.size()){
            reloadData();
        } else {
            actualIndex = latest;
        }
    }

    private void initComponents() {
        setContentPane(pnMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        reloadData();
    }

    private void reloadData() {
        Game actualGame = data.games.get(actualIndex);
        tfInfo.setText(actualGame.getName());
        isBought.setSelected(actualGame.isBought());
        if (actualGame.getMark() == 1) radLow.setSelected(true);
        else if (actualGame.getMark() == 2) radMid.setSelected(true);
        else if (actualGame.getMark() == 3) radHigh.setSelected(true);
    }
}
