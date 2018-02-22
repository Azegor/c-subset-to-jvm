import java.util.ArrayList;
import java.util.List;

import Assembler.AssemblerCmd;
import Assembler.LDC;

public class MyNumber extends Expr
{
	private Integer mNumber;

	public MyNumber(Integer number)
	{
		mNumber = number;
	}

	public List<AssemblerCmd> compile(SymbolTable symbolTable)
	{
		List<AssemblerCmd> result = new ArrayList<AssemblerCmd>();
		AssemblerCmd ldc = new LDC(mNumber);
		result.add(ldc);
		return result;
	}

	public Integer stackSize()
	{
		return 1;
	}

	public String toString()
	{
		return "MyNumber(" + mNumber.toString() + ")";
	}
}
