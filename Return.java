import java.util.List;

import Assembler.AssemblerCmd;
import Assembler.IRETURN;

public class Return extends Statement
{
	private Expr mExpr;

	public Return(Expr expr)
	{
		mExpr = expr;
	}

	public List<AssemblerCmd> compile(SymbolTable symbolTable)
	{
		List<AssemblerCmd> result = mExpr.compile(symbolTable);
		AssemblerCmd returnCmd = new IRETURN();
		result.add(returnCmd);
		return result;
	}

	public Integer stackSize()
	{
		return mExpr.stackSize();
	}

	public String toString()
	{
		return "Return(" + mExpr.toString() + ")";
	}
}
