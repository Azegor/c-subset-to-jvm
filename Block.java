import java.util.ArrayList;
import java.util.List;

import Assembler.AssemblerCmd;

public class Block extends Statement
{
	private List<Statement> mStatementList;

	public Block(List<Statement> statementList)
	{
		mStatementList = statementList;
	}

	public List<AssemblerCmd> compile(SymbolTable symbolTable)
	{
		List<AssemblerCmd> result = new ArrayList<AssemblerCmd>();
		for (Statement stmnt : mStatementList)
		{
			result.addAll(stmnt.compile(symbolTable));
		}
		return result;
	}

	public Integer stackSize()
	{
		Integer biggest = 0;
		for (Statement stmnt : mStatementList)
		{
			biggest = Math.max(biggest, stmnt.stackSize());
		}
		return biggest;
	}

	public String toString()
	{
		return "Block(" + mStatementList + ")";
	}
}
