import java.util.List;

import Assembler.AssemblerCmd;
import Assembler.ISTORE;

public class Assign extends Statement
{
	private final String mVar;
	private final Expr mExpr;

	public Assign(String var, Expr expr)
	{
		mVar = var;
		mExpr = expr;
	}

	@Override
	public List<AssemblerCmd> compile(SymbolTable symbolTable)
	{
		List<AssemblerCmd> result = mExpr.compile(symbolTable);
		AssemblerCmd storeCmd = new ISTORE(symbolTable.get(mVar));
		result.add(storeCmd);
		return result;
	}

	@Override
	public Integer stackSize()
	{
		return mExpr.stackSize();
	}

	@Override
	public String toString()
	{
		return "Assign(" + mVar + ", " + mExpr + ")";
	}
}
