import java.util.Scanner;

public class Edit implements Operation
{
	private static final String ROW_PROMPT = "Please enter the row index: ";
	private static final String COLUMN_PROMPT = "Please enter the column index: ";
	private static final String REPLACEMENT_PROMPT = "Please enter the replacement string (must be 3 characters long): ";

	private Table _table;

	public Edit(Table table)
	{
		_table = table;
	}

	public void Execute() 
	{
		Scanner scanner = new Scanner(System.in);

		System.out.print(ROW_PROMPT);
		int row = scanner.nextInt();

		System.out.print(COLUMN_PROMPT);
		int column = scanner.nextInt();

		System.out.print(REPLACEMENT_PROMPT);
		String replacement = scanner.next();

		try 
		{
			_table.replace(row, column, replacement);				
		}
		catch(ArrayIndexOutOfBoundsException exception)
		{
			System.out.println("ERROR: Invalid cell number!");
		}
		catch(CellSizeException exception)
		{
			System.out.println("ERROR: The replacement text must only be 3 characters long!");
		}
	}
}