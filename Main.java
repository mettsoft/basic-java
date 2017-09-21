import java.util.Scanner;

public class Main {

	private static final int DEFAULT_ROW_SIZE = 3;
	private static final int DEFAULT_COLUMN_SIZE = 3;
	private static final int DEFAULT_CELL_SIZE = 3;

	private static final String HEADER = "----------\nExercise 1 - Basic Java\nBy Emmett Young";
	private static final String OPERATION_PROMPT = "Please choose what operation you want to perform:\n1.Search\n2.Edit\n3.Reset\n4.Exit\nOperation: ";
	
	private class SearchPrompt {
		public static final String KEYWORD = "Please enter the search keyword: ";
	} 

	private class EditPrompt {
		public static final String ROW_INDEX = "Please enter the row index: ";
		public static final String COLUMN_INDEX = "Please enter the column index: ";
		public static final String REPLACEMENT_TEXT = "Please enter the replacement string (must be 3 characters long): ";
	}

	private class ResetPrompt {
		private static final String NUMBER_OF_ROWS = "Please enter the desired number of rows: ";
		private static final String NUMBER_OF_COLUMNS = "Please enter the desired number of columns: ";		
	}

	private class Errors {
		private static final String INVALID_CELL_NUMBER = "ERROR: Invalid cell number!";
		private static final String INVALID_TEXT_LENGTH = "ERROR: The text must only be 3 characters long!";
		private static final String INVALID_OPERATION_NUMBER = "ERROR: Invalid operation number!";
		private static final String INVALID_INPUT = "ERROR: Invalid input!";
		private static final String INVALID_TABLE_SIZE = "ERROR: Invalid table size!";
	}

	private enum Operation {
		SEARCH,
		EDIT,
		RESET,
		EXIT
	};

	public static int getIntegerInput(Scanner scanner) {
		return Integer.valueOf(scanner.nextLine());
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Table table = new Table(DEFAULT_ROW_SIZE, DEFAULT_COLUMN_SIZE, DEFAULT_CELL_SIZE);

		System.out.println(HEADER);

		while (true) {

			try {

				table.print();
				System.out.print(OPERATION_PROMPT);
	
				int operationIndex = getIntegerInput(scanner) - 1;
				Operation selectedOperation = Operation.values()[operationIndex];				

				switch (selectedOperation) {
					case SEARCH: 
						System.out.print(SearchPrompt.KEYWORD);
						String searchKeyword = scanner.nextLine();
						table.search(searchKeyword);
						break;

					case EDIT: 			
						System.out.print(EditPrompt.ROW_INDEX);
						int row = getIntegerInput(scanner);

						System.out.print(EditPrompt.COLUMN_INDEX);
						int column = getIntegerInput(scanner);

						System.out.print(EditPrompt.REPLACEMENT_TEXT);
						String replacement = scanner.nextLine();

						try {
							table.replace(row, column, replacement);				
						}
						catch(ArrayIndexOutOfBoundsException exception)
						{
							System.out.println(Errors.INVALID_CELL_NUMBER);
						}
						catch(CellSizeException exception)
						{
							System.out.println(Errors.INVALID_TEXT_LENGTH);
						}
						break;

					case RESET:
						System.out.print(ResetPrompt.NUMBER_OF_ROWS);
						int numberOfRows = getIntegerInput(scanner);

						System.out.print(ResetPrompt.NUMBER_OF_COLUMNS);
						int numberOfColumns = getIntegerInput(scanner);

						if (numberOfRows <= 0 || numberOfColumns <= 0)
							throw new TableSizeException();

						table.generateTable(numberOfRows, numberOfColumns, DEFAULT_CELL_SIZE);
						break;

					case EXIT: 
						System.exit(0);
				}
			}
			catch (ArrayIndexOutOfBoundsException exception) {
				System.out.println(Errors.INVALID_OPERATION_NUMBER);
			}
			catch (NumberFormatException exception) {
				System.out.println(Errors.INVALID_INPUT);
			}
			catch (TableSizeException exception) {
				System.out.println(Errors.INVALID_TABLE_SIZE);
			}
		}
	}
}