import java.util.ArrayList;
import java.util.List;

import Assembler.AssemblerCmd;

public class Program
{
	private List<Function> mFunctionList;

	public Program(List<Function> functionList)
	{
		mFunctionList = functionList;
	}

	public List<AssemblerCmd> compile()
	{
		List<AssemblerCmd> fctList = new ArrayList<AssemblerCmd>();
		int indexMain = mFunctionList.size() - 1;
		Function main = mFunctionList.get(indexMain);
		fctList.addAll(main.compile());
		for (int i = 0; i < indexMain; ++i)
		{
			Function f = mFunctionList.get(i);
			fctList.addAll(f.compile());
		}
		return fctList;
	}

	public String toString()
	{
		return "Program(" + mFunctionList + ")";
	}
}
