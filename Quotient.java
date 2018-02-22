import java.util.ArrayList;
import java.util.List;

import Assembler.AssemblerCmd;
import Assembler.IDIV;

public class Quotient extends Expr
{
	private Expr mLhs;
	private Expr mRhs;

	public Quotient(Expr lhs, Expr rhs)
	{
		mLhs = lhs;
		mRhs = rhs;
	}

	public List<AssemblerCmd> compile(SymbolTable symbolTable)
	{
		List<AssemblerCmd> result = new ArrayList<AssemblerCmd>();
		result.addAll(mLhs.compile(symbolTable));
		result.addAll(mRhs.compile(symbolTable));
		result.add(new IDIV());
		return result;
	}

	public Integer stackSize()
	{
		return Math.max(mLhs.stackSize(), mRhs.stackSize() + 1);
	}

	public String toString()
	{
		return "Quotient(" + mLhs.toString() + ", " + mRhs.toString() + ")";
	}
}
