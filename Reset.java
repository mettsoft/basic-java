import java.util.Scanner;

public class Reset implements Operation
{
	private static final String ROWS_PROMPT = "Please enter the desired number of rows: ";
	private static final String COLUMNS_PROMPT = "Please enter the desired number of columns: ";

	private Table _table;

	public Reset(Table table)
	{
		_table = table;
	}

	public void Execute() 
	{
		Scanner scanner = new Scanner(System.in);

		System.out.print(ROWS_PROMPT);
		int rows = scanner.nextInt();

		System.out.print(COLUMNS_PROMPT);
		int columns = scanner.nextInt();

		_table.generateTable(rows, columns);
	}
}