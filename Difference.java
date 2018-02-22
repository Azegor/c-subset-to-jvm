import java.util.List;

import Assembler.AssemblerCmd;
import Assembler.ISUB;

/**
 * This class represents the difference mLhs - mRhs;
 */
public class Difference extends Expr
{
	private Expr mLhs;
	private Expr mRhs;

	public Difference(Expr lhs, Expr rhs)
	{
		mLhs = lhs;
		mRhs = rhs;
	}

	public List<AssemblerCmd> compile(SymbolTable symbolTable)
	{
		List<AssemblerCmd> result = mLhs.compile(symbolTable);
		result.addAll(mRhs.compile(symbolTable));
		result.add(new ISUB());
		return result;
	}

	public Integer stackSize()
	{
		return Math.max(mLhs.stackSize(), mRhs.stackSize() + 1);
	}

	public String toString()
	{
		return "Difference(" + mLhs.toString() + ", " + mRhs.toString() + ")";
	}
}
