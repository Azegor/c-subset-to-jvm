import java.util.HashMap;
import java.util.Map;

public class SymbolTable
{
	private final Map<String, Integer> map = new HashMap<>();

	public void put(String name, int nr)
	{
		if (map.get(name) != null)
			throw new Error("Variable " + name + " is already defined!");
		map.put(name, nr);
	}

	public int get(String name)
	{
		Integer res = map.get(name);
		if (res == null)
			throw new Error("Variable " + name + " is not defined!");
		return res;
	}
}
