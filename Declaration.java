import java.util.ArrayList;
import java.util.List;

import Assembler.AssemblerCmd;
import Assembler.ISTORE;

public class Declaration extends Statement
{
	private final String mVar;
	private final Expr mInit;

	public Declaration(String var)
	{
		mVar = var;
		mInit = null;
	}

	public Declaration(String var, Expr init)
	{
		mVar = var;
		mInit = init;
	}

	public String getVar()
	{
		return mVar;
	}

	@Override
	public String toString()
	{
		return "Declaration(" + mVar.toString() + ")";
	}

	@Override
	public List<AssemblerCmd> compile(SymbolTable symbolTable)
	{
		List<AssemblerCmd> result = new ArrayList<>();
		if (mInit != null)
		{
			result.addAll(mInit.compile(symbolTable));
			result.add(new ISTORE(symbolTable.get(mVar)));
		}
		else
		{
			result.addAll(new MyNumber(0).compile(symbolTable));
			result.add(new ISTORE(symbolTable.get(mVar)));
		}
		return result;
	}

	@Override
	public Integer stackSize()
	{
		return 1;
	}
}
