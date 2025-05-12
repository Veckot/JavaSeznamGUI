import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModel extends AbstractTableModel {
    private final String[] columnNames = {"Name", "Mark", "Is Bought"};
    private final List<Game> games;

    public TableModel(List<Game> games) {
        this.games = games;
    }

    @Override
    public int getRowCount() {
        return games.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Game game = games.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return game.getName();
            case 1:
                return game.getMark();
            case 2:
                return game.isBought();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0; // Only "Mark" and "Is Bought" are editable
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Game game = games.get(rowIndex);
        switch (columnIndex) {
            case 1:
                game.setMark((Integer) aValue);
                break;
            case 2:
                game.setBought((Boolean) aValue);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}