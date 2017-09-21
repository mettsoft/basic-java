import java.util.Scanner;

public class Main 
{
	private static final String HEADER = "----------\nExercise 1 - Basic Java\nBy Emmett Young";
	private static final String OPERATION_PROMPT = "Please choose what operation you want to perform:\n1.Search\n2.Edit\n3.Reset\n4.Exit\nOperation: ";

	private static final int DEFAULT_SIZE = 3;

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		Table table = new Table(DEFAULT_SIZE, DEFAULT_SIZE);
		Operation[] operations = new Operation[] 
			{
				new Search(table),
				new Edit(table),
				new Reset(table),
				new Exit()
			};

		System.out.println(HEADER);

		while (true)
		{
			table.print();
			System.out.print(OPERATION_PROMPT);
			int operation_index = scanner.nextInt() - 1;

			try 
			{
				operations[operation_index].Execute();
			}
			catch(ArrayIndexOutOfBoundsException exception)
			{
				System.out.println("ERROR: Invalid operation number!");
			}
		}
	}
}