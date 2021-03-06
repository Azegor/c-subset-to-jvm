import java.util.List;

import Assembler.AssemblerCmd;
import Assembler.IADD;

public class Sum extends Expr
{
	private Expr mLhs;
	private Expr mRhs;

	public Sum(Expr lhs, Expr rhs)
	{
		mLhs = lhs;
		mRhs = rhs;
	}

	public List<AssemblerCmd> compile(SymbolTable symbolTable)
	{
		List<AssemblerCmd> result = mLhs.compile(symbolTable);
		result.addAll(mRhs.compile(symbolTable));
		result.add(new IADD());
		return result;
	}

	public Integer stackSize()
	{
		return Math.max(mLhs.stackSize(), mRhs.stackSize() + 1);
	}

	public String toString()
	{
		return "Sum(" + mLhs.toString() + ", " + mRhs.toString() + ")";
	}
}
