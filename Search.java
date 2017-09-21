import java.util.Scanner;

public class Search implements Operation
{
	private static final String SEARCH_PROMPT = "Please enter the search keyword: ";

	private Table _table;

	public Search(Table table)
	{
		_table = table;
	}

	public void Execute() 
	{
		Scanner scanner = new Scanner(System.in);

		System.out.print(SEARCH_PROMPT);
		String searchKeyword = scanner.next();
		_table.search(searchKeyword);
	}
}