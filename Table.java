import java.util.concurrent.ThreadLocalRandom;

public class Table {
	private final int CELL_SIZE = 3;

	private String[][] _table;
	private int _rows;
	private int _columns;

	public Table(int rows, int columns){
		generateTable(rows, columns);
	}

	public void replace(int row, int column, String replacement) throws CellSizeException
	{
		if (replacement.length() != 3) {
			throw new CellSizeException();
		}

		_table[row][column] = replacement;
	}

	public void search(String keyword)
	{
		boolean searchFound = false;

		for (int i = 0; i < _rows; i++)
			for (int j = 0; j < _columns; j++)
			{
				int occurrences = 0;
				int fromIndex = 0;

				while (true)
				{
					fromIndex = _table[i][j].indexOf(keyword, fromIndex) + 1;
					if (fromIndex == 0)
						break;					
					searchFound = true;
					occurrences++;
				}

				if (occurrences > 0)
					System.out.printf("(%d,%d): %d\n", i, j, occurrences);
			}

		if (!searchFound)
			System.out.println("No search found!");
	}

	public void generateTable(int rows, int columns)
	{
		_table = new String[rows][columns];
		_rows = rows;
		_columns = columns;

		for (int i = 0; i < _rows; i++)
			for (int j = 0; j < _columns; j++)
				_table[i][j] = generateRandomAscii(CELL_SIZE);
	}

	private String generateRandomAscii(int length)
	{
		// 32 is space
		// 127 is DEL (excluded)
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++)
			builder.append((char) ThreadLocalRandom.current().nextInt(32, 127));

		return builder.toString();
	}

	public void print()
	{
		System.out.println("----------");
		for (int i = 0; i < _rows; i++)
		{
			for (int j = 0; j < _columns; j++)
			{
				System.out.print(_table[i][j]);
				if (j < _columns - 1)
					System.out.print(" ");
			}
			System.out.println();			
		}
		System.out.println("----------");			
	}
}