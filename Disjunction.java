import java.util.List;

import Assembler.AssemblerCmd;
import Assembler.IOR;

public class Disjunction extends BoolExpr
{
	private BoolExpr mLhs;
	private BoolExpr mRhs;

	public Disjunction(BoolExpr lhs, BoolExpr rhs)
	{
		mLhs = lhs;
		mRhs = rhs;
	}

	public List<AssemblerCmd> compile(SymbolTable symbolTable)
	{
		List<AssemblerCmd> result = mLhs.compile(symbolTable);
		result.addAll(mRhs.compile(symbolTable));
		AssemblerCmd ior = new IOR();
		result.add(ior);
		return result;
	}

	public Integer stackSize()
	{
		return Math.max(mLhs.stackSize(), mRhs.stackSize() + 1);
	}

	public String toString()
	{
		return "Disjunction(" + mLhs.toString() + ", " + mRhs.toString() + ")";
	}
}
