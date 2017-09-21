import java.util.concurrent.ThreadLocalRandom;

public class Table {

	private String[][] dataTable;
	private int numberOfRows;
	private int numberOfColumns;

	public Table(int numberOfRows, int numberOfColumns, int cellSize) {

		generateTable(numberOfRows, numberOfColumns, cellSize);
	}

	public void replace(int rowIndex, int columnIndex, String replacement) throws CellSizeException {

		if (replacement.length() != 3) {
			throw new CellSizeException();
		}

		dataTable[rowIndex][columnIndex] = replacement;
	}

	public void search(String keyword) {

		boolean searchFound = false;

		if (!keyword.equals("")) {		
			for (int i = 0; i < numberOfRows; i++)
				for (int j = 0; j < numberOfColumns; j++)
				{
					int occurrences = 0;
					int fromIndex = 0;

					while (true)
					{
						fromIndex = dataTable[i][j].indexOf(keyword, fromIndex) + 1;
						if (fromIndex == 0)
							break;					
						searchFound = true;
						occurrences++;
					}

					if (occurrences > 0)
						System.out.printf("(%d,%d): %d\n", i, j, occurrences);
				}
		}

		if (!searchFound)
			System.out.println("No search found!");
	}

	public void generateTable(int numberOfRows, int numberOfColumns, int cellSize) {

		dataTable = new String[numberOfRows][numberOfColumns];
		this.numberOfRows = numberOfRows;
		this.numberOfColumns = numberOfColumns;

		for (int i = 0; i < numberOfRows; i++)
			for (int j = 0; j < numberOfColumns; j++)
				dataTable[i][j] = generateRandomAscii(cellSize);
	}

	private String generateRandomAscii(int length) {

		// 32 is space
		// 127 is DEL (excluded)
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++)
			builder.append((char) ThreadLocalRandom.current().nextInt(32, 127));

		return builder.toString();
	}

	public void print() {

		System.out.println("----------");
		for (int i = 0; i < numberOfRows; i++)
		{
			for (int j = 0; j < numberOfColumns; j++)
			{
				System.out.print(dataTable[i][j]);
				if (j < numberOfColumns - 1)
					System.out.print(" ");
			}
			System.out.println();			
		}
		System.out.println("----------");			
	}
}