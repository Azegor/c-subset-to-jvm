import java.util.ArrayList;
import java.util.List;

import Assembler.AssemblerCmd;
import Assembler.ILOAD;

public class Variable extends Expr
{
	private String mName;

	public Variable(String name)
	{
		mName = name;
	}

	public List<AssemblerCmd> compile(SymbolTable symbolTable)
	{
		List<AssemblerCmd> result = new ArrayList<AssemblerCmd>();
		AssemblerCmd iload = new ILOAD(symbolTable.get(mName));
		result.add(iload);
		return result;
	}

	public Integer stackSize()
	{
		return 1;
	}

	public String toString()
	{
		return "Variable(" + mName.toString() + ")";
	}
}
