import java.util.List;

import Assembler.AssemblerCmd;
import Assembler.POP;

public class ExprStatement extends Statement
{
	private Expr mExpr;

	public ExprStatement(Expr expr)
	{
		mExpr = expr;
	}

	public List<AssemblerCmd> compile(SymbolTable symbolTable)
	{
		List<AssemblerCmd> result = mExpr.compile(symbolTable);
		AssemblerCmd popCmd = new POP();
		result.add(popCmd);
		return result;
	}

	public Integer stackSize()
	{
		return mExpr.stackSize();
	}

	public String toString()
	{
		return "ExprStatement(" + mExpr + ")";
	}
}
