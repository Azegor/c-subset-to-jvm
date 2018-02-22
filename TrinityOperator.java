import java.util.List;

import Assembler.AssemblerCmd;
import Assembler.GOTO;
import Assembler.IFEQ;
import Assembler.LABEL;

public class TrinityOperator extends Expr
{
	private final BoolExpr mExpr;
	private final Expr mThen;
	private final Expr mElse;

	public TrinityOperator(BoolExpr expr, Expr thenStmnt, Expr elseStmnt)
	{
		mExpr = expr;
		mThen = thenStmnt;
		mElse = elseStmnt;
	}

	@Override
	public List<AssemblerCmd> compile(SymbolTable symbolTable)
	{
		List<AssemblerCmd> result = mExpr.compile(symbolTable);
		LABEL elseLabel = new LABEL();
		LABEL nextLabel = new LABEL();
		AssemblerCmd ifeq = new IFEQ(elseLabel.getLabel());
		AssemblerCmd gotoNext = new GOTO(nextLabel.getLabel());
		result.add(ifeq);
		result.addAll(mThen.compile(symbolTable));
		result.add(gotoNext);
		result.add(elseLabel);
		result.addAll(mElse.compile(symbolTable));
		result.add(nextLabel);
		return result;
	}

	@Override
	public Integer stackSize()
	{
		return Math.max(mExpr.stackSize(),
				Math.max(mThen.stackSize(), mElse.stackSize()));
	}

	@Override
	public String toString()
	{
		return "TritityOperator(" + mExpr.toString() + ", " + mThen.toString()
				+ ", " + mElse.toString() + ")";
	}
}
